package seng201.team32.models.gameplay;

import javafx.application.Platform;
import seng201.team32.exceptions.FullCartException;
import seng201.team32.exceptions.NegativeAdditionException;
import seng201.team32.exceptions.TowerNotFoundException;
import seng201.team32.models.resources.Resource;
import seng201.team32.models.towers.Tower;

import java.util.ArrayList;

/**
 * The GameRunner class holds all the logic to link with the GUI
 * or testing observer, extract data from the Round class, apply consumables for the round,
 * and then run the game in real-time, updating the observer everytime the gameplay state changes.
 * The GameRunner is able to run on a separate thread as to not interrupt javaFX processes and freeze the game window.
 */
public class GameRunner implements Runnable{

    private boolean javaFx;
    private boolean gameSuccess;
    GameObserver observer;
    ArrayList<Cart> carts;
    Round round;
    ArrayList<Tower> towers;

    /**
     * The GameRunner constructor that takes an instance of the Round class
     * which is used to get carts and other information about the current round, and
     * a GameObserver observer class that will observe the changes as the game runs.
     * @param round the Round class set with properties of the round
     * @param observer the observer that will update the GUI/process the game state every second
     * @param javaFx a boolean specifying whether to trigger observer functions using the javaFX thread, or to trigger on a normal thread for testing without a javaFX GUI
     */
    public GameRunner(Round round, GameObserver observer, boolean javaFx){
        this.javaFx = javaFx;
        this.observer = observer;
        this.carts = round.getCarts();
        this.round = round;
        this.towers = this.round.getTowers();
        this.gameSuccess = false;
        this.round.applyConsumables();
    }

    /**
     * The run function handles the real-time gameplay logic.
     * First, the carts specified in the Round class are checked to determine whether they have reached the end of the track and are updated accordingly,
     * then for each tower that is in the Round (fetched from the players activeTower list) and is not broken, it checks whether it is reloading or not and then acts accordingly. For each non-reloading, non-broken tower
     * It checks through the carts which are sorted by speed and determines whether they need to be filled, then fills them either until they are full or until it has unloaded it's ResourceAmount.
     * There is then a check to determine if all carts are finished or if they are all filled, which would trigger the end of the round.
     * The observer is then notified via {@code observer.observe()} using either javaFX's {@code Platform.runLater()} which ensures updates to the GUI are executed via the javaFX thread, or via a regular method call.
     * If the round is not finished, the process then sleeps for one second and then repeats until the game finishing criteria is met.
     * When the round end event is triggered then all tower's {@code Used} attributes are incremented to show that they have been used for a round, this is then used if a random event is triggered to determine what tower should break first.
     * Then the game success value is determined to be true or false, and the respective {@code win()} or {@code lose()} function is executed by the observer to continue with the flow of the game.
     * If the round is won then the number of coins earned from resources in carts is calculated, along with the number of points the player shall receive for finishing a round. This is passed over to the observer to continue with the game flow.
     */
    @Override
    public void run() {
        boolean finished = false;

        while (!finished){
            try {
                Thread.sleep(1000); // sleep 1 second
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for (Cart cart : carts){
                if(cart.getDistance() < round.getTrackLength()){
                    cart.incrementDistance();
                    if (cart.getDistance() >= round.getTrackLength()){
                        cart.setFinished(true);
                    }
                }
            }
            for (Tower tower : round.getTowers()){
                if (!tower.isBroken()){
                    if (tower.getReloadTimeElapsed() % tower.getReloadSpeed() == 0){
                        tower.setReloading(false);
                    }
                    else{
                        tower.incrementReloadTimeElapsed();
                    }
                    for (Cart cart : carts){
                        if (!tower.isReloading() && !cart.isFull() && !cart.isFinished()){
                            if (cart.isUniversal() || tower.getResource().equals(cart.getResource())){
                                for (int i = 0; i < tower.getResourceAmount(); i++){
                                    try {
                                        cart.fillCart(tower.getResource().clone());
                                    } catch (FullCartException e) {
                                        tower.setReloading(true);
                                        tower.incrementReloadTimeElapsed();
                                        break; // Cart has been filled
                                    } catch (CloneNotSupportedException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                                tower.setReloading(true);
                                tower.incrementReloadTimeElapsed(); // tower has unloaded, start reload
                            }
                        }
                    }
                }
            }
            // check if all carts have finished or if they are all filled
            finished = (this.cartsFinished() || this.cartsFull());

            if (this.javaFx){
                // Ensure observer updates are run on the JavaFX Application Thread
                Platform.runLater(() -> this.observer.observe(this)); // passes the observer the GameRunner instance for inspection
            }
            else {
                this.observer.observe(this); // for testing on the backend without javafx
            }
        }
        // increment towers used variable by one, indicating it has been used for a round
        for (Tower tower : towers){
            tower.incrementUsed();
        }
        // Determine win or loss
        boolean gameSuccess; // create gameSuccess value
        if (round.hasShield()){
            gameSuccess = true;
        }
        else{
            gameSuccess = this.cartsFull();
        }

        this.gameSuccess = gameSuccess;

        if (gameSuccess) {
            int coins = earnCoins();
            int points = earnPoints();
            if (this.javaFx) {
                Platform.runLater(() -> {
                    try {
                        this.observer.win(coins, points);
                    } catch (NegativeAdditionException | TowerNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
            else {
                try {
                    this.observer.win(coins, points); // for backend testing without javafx
                } catch (NegativeAdditionException | TowerNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        else {
            if (this.javaFx) {
                Platform.runLater(() -> {
                    try {
                        this.observer.lose();
                    } catch (TowerNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
            else {
                try {
                    this.observer.lose();
                } catch (TowerNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    /**
     * Calculates the number of coins earned from resources that have been loaded by towers during the {@code run()} process.
     * @return the number of coins earned from resources that are in carts
     */
    private int earnCoins() {
        int totalCoins = 0;
        for (Cart cart : carts){
            for (Resource resource : cart.getCargo()){
                if (resource != null){ // only add coins if there are resources in cart
                    totalCoins += resource.getResourceCoinValue();
                }
            }
        }
        return totalCoins;
    }

    /**
     * Returns the number of points that is earned from completing the round
     * @return number of points earned from the round
     */
    private int earnPoints() {
        return 200;
    }

    /**
     * Loops through and checks whether each cart in the round has finished going the distance of the track.
     * @return boolean determining whether all carts are finished
     */
    public boolean cartsFinished() {
        boolean finished = true;
        for (Cart cart : this.carts) {
            if (!cart.isFinished()) {
                finished = false; // at least one cart isn't finished
                break;
            }
        }
        return finished;
    }

    /**
     * Loops through and checks whether each cart in the round is full.
     * @return boolean determining if all carts are full
     */
    public boolean cartsFull(){
        boolean cartsFull = true;
        for (Cart cart : this.carts) {
            if (!cart.isFull()) {
                cartsFull = false; // at least one cart isn't full
                break;
            }
        }
        return cartsFull;
    }

    /**
     * Method for getting the carts in the current game state, used by the observers when updating GUI or other functions
     * @return active carts in the round being run
     */
    public ArrayList<Cart> getCarts() {
        return this.carts;
    }

    /**
     * Method for getting the active towers in the current game state, used by the observers when updating GUI or other functions
     * @return active towers in the round being run
     */
    public ArrayList<Tower> getTowers() {
        return this.towers;
    }

    /**
     * Method for getting the track distance used for the current round being played, used by observers for updating cart distance status etc
     * @return track distance of the round
     */
    public int getTrackDistance() {
        return round.getTrackLength();
    }

    /**
     * Gets the gameSuccess boolean value
     * @return boolean indicating whether the round succeeded or not
     */
    public boolean getGameSuccess() {
        return this.gameSuccess;
    }

}
