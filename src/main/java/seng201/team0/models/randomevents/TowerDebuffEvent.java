package seng201.team0.models.randomevents;

import seng201.team0.models.towers.Tower;
import seng201.team0.services.InventoryService;

import java.util.ArrayList;
import java.util.Random;

public class TowerDebuffEvent extends RandomEvent{

    public TowerDebuffEvent() {
        super("Random Tower Debuff!", "A random tower in your inventory has been debuffed! It's reload time has increased by 1! Check out your Active towers to see what's happened!");
    }

    @Override
    public void apply(InventoryService inventoryService) {
        setTowerAffected(inventoryService);
        Tower tower = getTowerAffected();
        tower.increaseReloadSpeed(); // increases time to reload for the tower, making it a longer wait before it can be useful in a game
    }

    public void setTowerAffected(InventoryService inventoryService) {
        Random random = new Random();
        ArrayList<Tower> towers = inventoryService.getActiveTowers();
        int randomTowerIndex = random.nextInt(towers.size());
        Tower tower = towers.get(randomTowerIndex);
        this.towerAffected = tower;
    }
}
