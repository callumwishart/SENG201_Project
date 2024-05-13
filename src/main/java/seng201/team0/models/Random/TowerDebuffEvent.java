package seng201.team0.models.Random;

import seng201.team0.models.Tower;

import java.util.ArrayList;
import java.util.Random;

import static seng201.team0.models.PlayerInventory.getActiveTowers;

public class TowerDebuffEvent extends RandomEvent{
    public TowerDebuffEvent() {
        super("Tower Debuff!", "Decreases one of your towers reload speed  by a random amount!");
    }
    public void applyEvent() {
        ArrayList<Tower> activeTowers = getActiveTowers();
        Random rand = new Random();
        int randomTower = rand.nextInt(activeTowers.size());
        int randomAmount = rand.nextInt(10);
        activeTowers.get(randomTower).increaseReloadSpeed(-1 * randomAmount);
    }
}
