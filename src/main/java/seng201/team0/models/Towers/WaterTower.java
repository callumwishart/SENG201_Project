package seng201.team0.models.Towers;


import seng201.team0.models.Resources.Resource;
import seng201.team0.models.Resources.Water;

public class WaterTower extends Tower{
    private static final Resource Water = new Water();
    public static String towerImagePath = "";
    public static String brokenImagePath = "";
    public WaterTower(int inputReloadSpeed, double inputCost) {
        super(Water, inputReloadSpeed, Water.getResourceType(), "Resource: " + Water.getResourceType(), inputCost);
    }
}
