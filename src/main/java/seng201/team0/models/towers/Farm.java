package seng201.team0.models.towers;

import seng201.team0.models.resources.Food;
import seng201.team0.models.resources.Resource;

public class Farm extends Tower{
    private static final Resource Food = new Food();
    public static String towerImagePath = "src/main/resources/images/towers/farm_tower.png";
    public static String brokenImagePath = "src/main/resources/images/towers/farm_tower_broken.png";
    public Farm(int inputReloadSpeed, int inputCost) {
        super(Food, inputReloadSpeed, "Farm", "Resource: " + Food.getResourceType(), inputCost, towerImagePath, brokenImagePath);
    }
    public Farm() {
        super(Food, 5, "Farm", "Resource: " + Food.getResourceType(), 1, towerImagePath, brokenImagePath);
    }
}
