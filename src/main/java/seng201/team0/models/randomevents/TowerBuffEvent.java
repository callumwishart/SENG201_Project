package seng201.team0.models.randomevents;

import seng201.team0.models.towers.Tower;
import seng201.team0.services.InventoryService;

import java.util.ArrayList;
import java.util.Random;

/**
 * Tower buff event, this will increase the amount of resources that the tower can hold
 */
public class TowerBuffEvent extends RandomEvent{
    /**
     * Constructor of the TowerBuffEvent which sets the name and description
     */
    public TowerBuffEvent() {
        super("Random Tower Buff!", "A random tower in your inventory has been buffed to add one more resource to it's loading capacity! Check out your Active towers to see what's happened!");
    }

    /**
     * Applies the tower debuff event
     * <p>
     *     First this sets the tower affected using {@code setTowerAffected()} then it gets the tower affected
     *     using {@code getTowerAffected()}. It then increases the amount of resource the tower can hold  using
     *     {@code tower.increaseResourceAmount(1)}
     * </p>
     * @param inventoryService is the current instance of the inventory service
     */
    @Override
    public void apply(InventoryService inventoryService) {
        setTowerAffected(inventoryService);
        Tower tower = getTowerAffected();
        tower.increaseResourceAmount(1); // increases the number of resources the tower can unload at once, making it more effective for filling carts
    }
    /**
     * Sets the tower affected
     * <p>
     *     This chooses the tower to be affected by generating a random integer and getting the tower related to that index
     * </p>
     * @param inventoryService is the current instance of the inventoryService
     */
    public void setTowerAffected(InventoryService inventoryService) {
        Random random = new Random();
        ArrayList<Tower> towers = inventoryService.getActiveTowers();
        int randomTowerIndex = random.nextInt(towers.size());
        this.towerAffected = towers.get(randomTowerIndex);
    }
}
