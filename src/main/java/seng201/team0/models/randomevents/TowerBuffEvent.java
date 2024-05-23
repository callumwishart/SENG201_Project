package seng201.team0.models.randomevents;

import seng201.team0.models.towers.Tower;
import seng201.team0.services.InventoryService;

import java.util.ArrayList;
import java.util.Random;

public class TowerBuffEvent extends RandomEvent{

    public TowerBuffEvent() {
        super("Random Tower Buff!", "A random tower in your inventory has been buffed to add one more resource to it's loading capacity! Check out your Active towers to see what's happened!");
    }

    @Override
    public void apply(InventoryService inventoryService) {
        setTowerAffected(inventoryService);
        Tower tower = getTowerAffected();
        tower.increaseResourceAmount(1); // increases the number of resources the tower can unload at once, making it more effective for filling carts
    }

    public void setTowerAffected(InventoryService inventoryService) {
        Random random = new Random();
        ArrayList<Tower> towers = inventoryService.getActiveTowers();
        int randomTowerIndex = random.nextInt(towers.size());
        Tower tower = towers.get(randomTowerIndex);
        this.towerAffected = tower;
    }
}
