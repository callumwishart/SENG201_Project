package seng201.team0.models;

import java.util.Random;

import static seng201.team0.models.PlayerInventory.activeTowers;

public class TowerBreakEvent extends RandomEvent{
    public TowerBreakEvent(String inputName, String inputDescription) {
        super("Random Tower Breakage", "Breaks a random Tower");
    }
    public void applyEvent() {
        Random rand = new Random();
        int rand_int = rand.nextInt(activeTowers.size());
        activeTowers.get(rand_int).setToBroken();
    }
}
