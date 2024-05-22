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
     * Farm constructor when no params passed. Initialises it with a reload speed of 5 and a cost of 100.
     */
    public Farm() {
        super(Food, 3, "Farm", "Resource: " + Food.getResourceType(), 300, towerImagePath, brokenImagePath);
    }
}
