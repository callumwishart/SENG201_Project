package seng201.team0.models.randomevents;

import seng201.team0.models.towers.Tower;
import seng201.team0.services.InventoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TowerBuffEvent extends RandomEvent{

    public TowerBuffEvent() {
        super("Random Tower Buff!", "A random tower in your inventory has been buffed! Check out your Active towers to see what's happened!");
    }

    @Override
    public void apply(InventoryService inventoryService) {
        Random random = new Random();
        ArrayList<Tower> towers = inventoryService.getActiveTowers();
        int randomTowerIndex = random.nextInt(towers.size());
        Tower tower = towers.get(randomTowerIndex);
        tower.increaseResourceAmount(1); // increases the number of resources the tower can unload at once, making it more effective for filling carts
    }
}
