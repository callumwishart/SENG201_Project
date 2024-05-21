package seng201.team0.models.randomevents;

import seng201.team0.models.towers.Tower;
import seng201.team0.services.InventoryService;

import java.util.*;



public class TowerBreakEvent extends RandomEvent{
    public TowerBreakEvent() {
        super("Random Tower Breakage", "Breaks a random Tower");
    }
    public void applyEvent() {
        // List<Tower> activeTowers = getActiveTowers();
        // activeTowers.sort(Comparator.comparingInt(Tower::getUsed));
        // activeTowers.get(-1).setToBroken();
    }


}
