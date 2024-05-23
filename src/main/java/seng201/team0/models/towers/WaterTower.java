package seng201.team0.models.towers;


import seng201.team0.models.resources.Resource;
import seng201.team0.models.resources.Water;

/**
 * Water tower which has a resource of Water
 */
public class WaterTower extends Tower{
    /**
     * Creates new resource for tower to use
     */
    private static final Resource Water = new Water();
    /**
     * image path for working tower
     */
    public static String towerImagePath = "src/main/resources/images/towers/water_tower.png";
    /**
     * image path for broken tower
     */
    public static String brokenImagePath = "src/main/resources/images/towers/water_tower_broken.png";

    /**
     * Water tower constructor when no params passed. Will initialise tower with a reload speed of 5
     * and an input cost of 150.
     */
    public WaterTower() {
        super(Water, 5, "Water Tower", "Resource: " + Water.getResourceType(), 150, towerImagePath, brokenImagePath);
    }
}
