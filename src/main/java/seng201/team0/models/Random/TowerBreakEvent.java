package seng201.team0.models.Random;

import seng201.team0.models.Tower;

import java.util.ArrayList;
import java.util.Random;

import static seng201.team0.models.PlayerInventory.getActiveTowers;

public class TowerBreakEvent extends RandomEvent{
    public TowerBreakEvent(String inputName, String inputDescription) {
        super("Random Tower Breakage", "Breaks a random Tower");
    }
    public void applyEvent() {
        ArrayList<Tower> activeTowers = getActiveTowers();
        Random rand = new Random();
        int rand_int = rand.nextInt(activeTowers.size());
        activeTowers.get(rand_int).setToBroken();
    }
}
