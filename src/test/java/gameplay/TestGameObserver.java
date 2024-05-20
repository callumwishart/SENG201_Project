package gameplay;

import seng201.team0.models.gameplay.Cart;
import seng201.team0.models.gameplay.GameObserver;
import seng201.team0.models.gameplay.GameRunner;
import seng201.team0.models.towers.Tower;

import java.util.ArrayList;

public class TestGameObserver implements GameObserver {

    private ArrayList<Cart> carts;
    private ArrayList<Tower> towers;
    private int trackDistance;

    @Override
    public void observe(GameRunner gameRunner) {
        this.carts = gameRunner.getCarts();
        this.towers = gameRunner.getTowers();
        this.trackDistance = gameRunner.getTrackDistance();
        printCartInfo();
        printTowerInfo();
    }

    public void printCartInfo(){
        for (Cart cart: this.carts){
            System.out.println(String.format("Cart Name: %s",cart.getName()));
            System.out.println(String.format("Cart Speed: %s",cart.getSpeed()));
            System.out.println(String.format("Cart size: %d",cart.getSize()));
            System.out.println(String.format("Cart slots filled: %d",cart.getCargoSlotsFilled()));
            System.out.println(String.format("Cart Distance: %d",cart.getDistance()));
            System.out.println(String.format("Is cart finished?: %s",cart.isFinished()));
            System.out.println("======================================");
            System.out.println(String.format("Track distance to go: %d",this.trackDistance - cart.getDistance()));
            System.out.println("======================================");
        }
    }

    public void printTowerInfo(){
        for (Tower tower: this.towers){
            System.out.println("*******************************************");
            System.out.println(String.format("Tower Name: %s",tower.getName()));
            System.out.println(String.format("Tower Reload Speed: %d",tower.getReloadSpeed()));
            System.out.println(String.format("Tower resource amount: %d",tower.getResourceAmount()));
            System.out.println(String.format("Tower isReloading?: %s",tower.isReloading()));
            System.out.println(String.format("Reload time elapsed: %d",tower.getReloadTimeElapsed()));
            System.out.println("*******************************************");
        }
    }

}
