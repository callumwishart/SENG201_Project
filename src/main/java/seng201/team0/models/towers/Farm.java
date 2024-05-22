package seng201.team0.models.towers;

import seng201.team0.models.resources.Food;
import seng201.team0.models.resources.Resource;

/**
 * Farm tower, this has a resource type of food
 */
public class Farm extends Tower{
    private static final Resource Food = new Food();
    public static String towerImagePath = "src/main/resources/images/towers/farm_tower.png";
    public static String brokenImagePath = "src/main/resources/images/towers/farm_tower_broken.png";

    /**
     * Farm constructor with params
     * @param inputReloadSpeed initial reloading speed of the tower
     * @param inputCost initial cost of the tower
     */
    public Farm(int inputReloadSpeed, int inputCost) {
        super(Food, inputReloadSpeed, "Farm", "Resource: " + Food.getResourceType(), inputCost, towerImagePath, brokenImagePath);
    }

    /**
     * Farm constructor when no params passed. Initialises it with a reload speed of 5 and a cost of 100.
     */
    public Farm() {
        super(Food, 5, "Farm", "Resource: " + Food.getResourceType(), 100, towerImagePath, brokenImagePath);
    }
}
