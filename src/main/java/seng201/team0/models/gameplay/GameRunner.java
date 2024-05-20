package seng201.team0.models.gameplay;

import seng201.team0.exceptions.FullCartException;
import seng201.team0.models.resources.Resource;
import seng201.team0.models.towers.Tower;

import java.util.ArrayList;

public class GameRunner{

    GameObserver observer;
    ArrayList<Cart> carts;
    Round round;

    GameRunner(Round round, GameObserver observer){
        this.observer = observer;
        this.carts = round.getCarts();
        this.round = round;
    }


    public boolean run() throws InterruptedException {
        boolean finished = false;
        int roundTimeElapsed = 0; // may be useful for testing?

        while (!finished){
            Thread.sleep(1000); // sleep 1 second
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
                    if (tower.isReloading() || cart.isFull() || cart.isFinished()){
                        break;
                    }
                    else{
                        if (cart.isUniversal() || tower.getResource().getResourceType().equals(cart.getResourceType())){
                            for (int i = 0; i < tower.getResourceAmount(); i++){
                                try {
                                    cart.fillCart(new Resource(tower.getResource()));
                                } catch (FullCartException e) {
                                    tower.setReloading(true);
                                    tower.incrementReloadTimeElapsed();
                                    break; // Cart has been filled
                                }
                            }
                        }
                    }
                }
            }
            // check if all carts have finished or if they are all filled
            finished = (this.cartsFinished() || this.cartsFull());

            this.observer.observe(this); // passes the observer the GameRunner instance for inspection
        }

        // Determine win or loss
        boolean gameSuccess = this.cartsFull();

        return gameSuccess;

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

}
