package seng201.team0.models.towers;


import seng201.team0.models.resources.Resource;
import seng201.team0.models.resources.Water;

/**
 * Water tower which has a resource of Water
 */
public class WaterTower extends Tower{
    private static final Resource Water = new Water();
    public static String towerImagePath = "src/main/resources/images/towers/water_tower.png";
    public static String brokenImagePath = "src/main/resources/images/towers/water_tower_broken.png";

    /**
     * Water Tower Constructor when params passed.
     * @param inputReloadSpeed initial reload speed
     * @param inputCost initial cost
     */
    public WaterTower(int inputReloadSpeed, int inputCost) {
        super(Water, inputReloadSpeed, "Water Tower", "Resource: " + Water.getResourceType(), inputCost, towerImagePath, brokenImagePath);
    }

    /**
     * Water tower constructor when no params passed. Will initialise tower with a reload speed of 5
     * and an input cost of 150.
     */
    public WaterTower() {
        super(Water, 2, "Water Tower", "Resource: " + Water.getResourceType(), 200, towerImagePath, brokenImagePath);
    }
}
