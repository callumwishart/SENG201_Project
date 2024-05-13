package seng201.team0.models.Random;

import seng201.team0.models.Tower;

import java.util.ArrayList;
import java.util.Random;

import static seng201.team0.models.PlayerInventory.getActiveTowers;

public class TowerBuffEvent extends RandomEvent{
    public TowerBuffEvent(String inputName, String inputDescription) {
        super("Buff Tower", "Buffs Your Tower");
    }
    public void applyEvent() {
        ArrayList<Tower> activeTowers = getActiveTowers();
        Random rand = new Random();
        int randomTower = rand.nextInt(activeTowers.size());
        int randomAmount = rand.nextInt(10);
        activeTowers.get(randomTower).increaseReloadSpeed(randomAmount);
    }
}
