package seng201.team0.models.Towers;

import seng201.team0.models.Resources.Food;
import seng201.team0.models.Resources.Resource;

public class Farm extends Tower{
    private static final Resource Food = new Food();
    public static String towerImagePath = "";
    public static String brokenImagePath = "";
    public Farm(int inputReloadSpeed, double inputCost) {
        super(Food, inputReloadSpeed, Food.getResourceType(), "Resource: " + Food.getResourceType(), inputCost);
    }
}
