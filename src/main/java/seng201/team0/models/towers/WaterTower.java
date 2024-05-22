package seng201.team0.models.towers;


import seng201.team0.models.resources.Resource;
import seng201.team0.models.resources.Water;

public class WaterTower extends Tower{
    private static final Resource Water = new Water();
    public static String towerImagePath = "src/main/resources/images/towers/water_tower.png";
    public static String brokenImagePath = "src/main/resources/images/towers/water_tower_broken.png";
    public WaterTower(int inputReloadSpeed, int inputCost) {
        super(Water, inputReloadSpeed, "Water Tower", "Resource: " + Water.getResourceType(), inputCost, towerImagePath, brokenImagePath);
    }
    public WaterTower() {
        super(Water, 5, "Water Tower", "Resource: " + Water.getResourceType(), 150, towerImagePath, brokenImagePath);
    }
}
