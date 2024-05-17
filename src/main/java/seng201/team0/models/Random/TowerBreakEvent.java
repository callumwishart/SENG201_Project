package seng201.team0.models.Random;

import seng201.team0.models.Towers.Tower;

import java.util.*;

import static seng201.team0.models.PlayerInventory.getActiveTowers;

public class TowerBreakEvent extends RandomEvent{
    public TowerBreakEvent(String inputName, String inputDescription) {
        super("Random Tower Breakage", "Breaks a random Tower");
    }
    public void applyEvent() {
        List<Tower> activeTowers = getActiveTowers();
        activeTowers.sort(Comparator.comparingInt(Tower::getUsed));
        activeTowers.get(-1).setToBroken();
    }
}
