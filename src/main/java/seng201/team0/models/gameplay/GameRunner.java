package seng201.team0.models.gameplay;

import javafx.application.Platform;
import seng201.team0.exceptions.FullCartException;
import seng201.team0.exceptions.NegativeAdditionException;
import seng201.team0.exceptions.TowerNotFoundException;
import seng201.team0.models.resources.Resource;
import seng201.team0.models.towers.Tower;

import java.util.ArrayList;

public class GameRunner implements Runnable{

    private boolean javaFx;
    private boolean gameSuccess;
    GameObserver observer;
    ArrayList<Cart> carts;
    Round round;
    ArrayList<Tower> towers;

    public GameRunner(Round round, GameObserver observer, boolean javaFx){
        this.javaFx = javaFx;
        this.observer = observer;
        this.carts = round.getCarts();
        this.round = round;
        this.towers = (ArrayList<Tower>) this.round.getTowers();
        this.gameSuccess = false;
    }

    @Override
    public void run() {
        boolean finished = false;
        int roundTimeElapsed = 0; // may be useful for testing?

        while (!finished){
            try {
                Thread.sleep(1000); // sleep 1 second
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            roundTimeElapsed += 1;
            for (Cart cart : carts){
                if(cart.getDistance() < round.getTrackLength()){
                    cart.incrementDistance();
                    if (cart.getDistance() >= round.getTrackLength()){
                        cart.setFinished(true);
                    }
                }
            }
            for (Tower tower : round.getTowers()){
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
        boolean gameSuccess = true; // create gameSuccess value
        if (round.hasShield()){
            for (Cart cart : this.carts){
                if (!cart.isFull()){
                    if (round.hasShield()){
                        round.setShield(false); // will do this once to simulate the shield being used
                    }
                    else{
                        gameSuccess = false;
                        break;
                    }
                }
            }
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

    private int earnCoins() {
        int totalCoins = 0;
        for (Cart cart : carts){
            for (Resource resource : cart.getCargo()){
                totalCoins += resource.getResourceCoinValue();
            }
        }
        return totalCoins;
    }

    private int earnPoints() {
        return 200;
    }

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

    public ArrayList<Cart> getCarts() {
        return this.carts;
    }

    public ArrayList<Tower> getTowers() {
        return this.towers;
    }

    public int getTrackDistance() {
        return round.getTrackLength();
    }

    public boolean getGameSuccess() {
        return this.gameSuccess;
    }

}
