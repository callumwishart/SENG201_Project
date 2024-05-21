package seng201.team0.models.randomevents;

import seng201.team0.exceptions.TowerNotFoundException;
import seng201.team0.models.towers.Tower;
import seng201.team0.services.InventoryService;

public abstract class RandomEvent {
    protected Tower towerAffected;
    private String name;
    private String description;

    public RandomEvent (String inputName, String inputDescription) {
        name = inputName;
        description = inputDescription;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Tower getTowerAffected(){
        return this.towerAffected;
    }

    public abstract void apply(InventoryService inventoryService) throws TowerNotFoundException;

    public abstract void setTowerAffected(InventoryService inventoryService) throws TowerNotFoundException;


}
