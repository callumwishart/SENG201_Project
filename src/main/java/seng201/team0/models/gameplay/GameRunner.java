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
        this.observer =  observer;
        this.carts = round.getCarts();
        this.round = round;
    }


    public static boolean run() throws InterruptedException {
        boolean finished = false;
        int roundTimeElapsed = 0;

        while (!finished){
            Thread.sleep(1000); // sleep 1 second
            roundTimeElapsed += 1;
            for (Cart cart : carts){
                if(cart.getDistance() < round.trackDistance){
                    cart.incrementDistance();
                    if (cart.getDistance() >= round.trackDistance){
                        cart.setFinished(true);
                    }
                }
            }
            for (Tower tower : round.towers){
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
                        for (int i = 0; i < tower.getResourceAmount(); i++){
                            try {
                                cart.fillCart(new Resource(tower.getResourceType()));
                            } catch (FullCartException e) {
                                tower.setReloading(true);
                                tower.incrementReloadTimeElapsed();
                                break; // Cart has been filled
                            }
                        }
                    }
                }
            }
            // check if all carts have finished
            finished = true;
            for (Cart cart : carts){
                if (!cart.isFinished()){
                    finished = false; // at least one cart isn't finished
                    break; // continue with gameplay
                }
            }
            this.observer.observe(this); // passes the observer the GameRunner instance for inspection
        }

        boolean gameSuccess = true;
        // Determine win or loss
        for (Cart cart : carts){
            if (!cart.isFull()){
                gameSuccess = false;
                break;
            }
        }

        return gameSuccess;

    }


}
