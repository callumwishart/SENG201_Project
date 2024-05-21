package seng201.team0.models.randomevents;

import seng201.team0.exceptions.TowerNotFoundException;
import seng201.team0.models.towers.Tower;
import seng201.team0.services.InventoryService;

import java.util.*;

public class TowerBreakEvent extends RandomEvent{

    public TowerBreakEvent() {
        super("Tower Breakage", "Oh No! Looks like one of your towers has broken. Have a look in your inventory to see what's happened!");
    }

    @Override
    public void apply(InventoryService inventoryService) throws TowerNotFoundException {
        setTowerAffected(inventoryService);
        Tower tower = getTowerAffected();
        tower.setToBroken();
    }

    public void setTowerAffected(InventoryService inventoryService) throws TowerNotFoundException {
        ArrayList<Tower> towers = inventoryService.getActiveTowers();
        Optional<Tower> maxTower = towers.stream().max(Comparator.comparingInt(Tower::getUsed));
        Tower tower = null;
        if (maxTower.isPresent()) {
            tower = maxTower.get();
        }
        else {
            throw new TowerNotFoundException();
        }
        this.towerAffected = tower;
    }
}
