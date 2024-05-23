package seng201.team32.models.randomevents;

import seng201.team32.models.towers.Tower;
import seng201.team32.services.InventoryService;

import java.util.ArrayList;
import java.util.Random;

/**
 * Tower Debuff event, this will increase the reload speed of the tower it affects
 */
public class TowerDebuffEvent extends RandomEvent{
    /**
     * Tower Debuff constructor which initialises the name and description
     */
    public TowerDebuffEvent() {
        super("Random Tower Debuff!", "A random tower in your inventory has been debuffed! It's reload time has increased by 1! Check out your Active towers to see what's happened!");
    }

    /**
     * Applies the tower debuff event
     * <p>
     *     First this sets the tower affected using {@code setTowerAffected()} then it gets the tower affected
     *     using {@code getTowerAffected()}. It then increases the reload speed of the tower using
     *     {@code tower.increaseReloadSpeed}
     * </p>
     * @param inventoryService is the current instance of the inventory service
     */
    @Override
    public void apply(InventoryService inventoryService) {
        setTowerAffected(inventoryService);
        Tower tower = getTowerAffected();
        tower.increaseReloadSpeed(); // increases time to reload for the tower, making it a longer wait before it can be useful in a game
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
