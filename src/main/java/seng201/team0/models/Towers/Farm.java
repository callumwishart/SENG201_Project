package seng201.team0.models.Towers;

import seng201.team0.models.Resources.Food;
import seng201.team0.models.Resources.Resource;

public class Farm extends Tower{
    private static final Resource Food = new Food();
    public static String towerImagePath = "resources/images/towers/farm_tower.png";
    public static String brokenImagePath = "resources/images/towers/farm_tower_broken.png";
    public Farm(int inputReloadSpeed, double inputCost) {
        super(Food, inputReloadSpeed, Food.getResourceType(), "Resource: " + Food.getResourceType(), inputCost, towerImagePath, brokenImagePath);
    }
}
