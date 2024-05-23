package seng201.team0.models.randomevents;

import seng201.team0.exceptions.TowerNotFoundException;
import seng201.team0.models.towers.Tower;
import seng201.team0.services.InventoryService;

import java.util.*;

/**
 * Tower break event, this will break the tower that has been used the most
 */
public class TowerBreakEvent extends RandomEvent{
    /**
     * Constructor of tower break event which will set the name of event and description of event
     */
    public TowerBreakEvent() {
        super("Tower Breakage", "Oh No! Looks like one of your towers has broken. Have a look in your inventory to see what's happened!");
    }
    /**
     * Applies the tower debuff event
     * <p>
     *     First this sets the tower affected using {@code setTowerAffected()} then it gets the tower affected
     *     using {@code getTowerAffected()}. It then breaks the tower using
     *     {@code tower.setToBroken()}
     * </p>
     * @param inventoryService is the current instance of the inventory service
     */
    @Override
    public void apply(InventoryService inventoryService) throws TowerNotFoundException {
        setTowerAffected(inventoryService);
        Tower tower = getTowerAffected();
        tower.setToBroken();
    }

    /**
     * Sets the tower affected
     * <p>
     *     Filters the towers by the amount they have been used and filters out any that are already broken
     * </p>
     * @param inventoryService the current instance of the inventory service
     * @throws TowerNotFoundException if the tower cannot be found
     */
    public void setTowerAffected(InventoryService inventoryService) throws TowerNotFoundException {
        ArrayList<Tower> towers = inventoryService.getActiveTowers();
        Optional<Tower> maxTower = towers.stream()
                .filter(tower -> !tower.isBroken())
                .max(Comparator.comparingInt(Tower::getUsed));
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
