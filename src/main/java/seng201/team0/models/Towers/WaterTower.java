package seng201.team0.models.Towers;


import seng201.team0.models.Resources.Resource;
import seng201.team0.models.Resources.Water;

public class WaterTower extends Tower{
    private static final Resource Water = new Water();
    public static String towerImagePath = "src/main/resources/images/towers/water_tower.png";
    public static String brokenImagePath = "src/main/resources/images/towers/water_tower_broken.png";
    public WaterTower(int inputReloadSpeed, int inputCost) {
        super(Water, inputReloadSpeed, "Water Tower", "Resource: " + Water.getResourceType(), inputCost, towerImagePath, brokenImagePath);
    }
}
