package seng201.team32.models.randomevents;

import seng201.team32.exceptions.TowerNotFoundException;
import seng201.team32.models.towers.Tower;
import seng201.team32.services.InventoryService;

/**
 * Random Event class to cause a random event to happen
 */
public abstract class RandomEvent {
    /**
     * tower that will have the random event applied
     */
    protected Tower towerAffected;
    /**
     * name of the random event
     */
    private final String name;
    /**
     * description of the random event
     */
    private final String description;

    /**
     * Random event constructor sets the name and description
     * @param inputName name of the random event
     * @param inputDescription description of the event
     */
    public RandomEvent (String inputName, String inputDescription) {
        name = inputName;
        description = inputDescription;
    }

    /**
     * Get name of the random event
     * @return the name of the event
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of the random event
     * @return the description of the event as a String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the tower affected by the random event
     * @return a tower that will be affected by the random event
     */
    public Tower getTowerAffected(){
        return this.towerAffected;
    }

    /**
     * Apply event method
     * @param inventoryService the current instance of the inventoryService
     * @throws TowerNotFoundException is thrown if the tower cannot be found
     */
    public abstract void apply(InventoryService inventoryService) throws TowerNotFoundException;

    public abstract void setTowerAffected(InventoryService inventoryService) throws TowerNotFoundException;


}
