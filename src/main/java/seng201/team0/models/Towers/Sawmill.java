package seng201.team0.models.Towers;

import seng201.team0.models.Resources.Resource;
import seng201.team0.models.Resources.Wood;

public class Sawmill extends Tower{
    private static final Resource Wood = new Wood();
    public static String towerImagePath = "";
    public static String brokenImagePath = "";
    public Sawmill(int inputReloadSpeed, double inputCost) {
        super(Wood, inputReloadSpeed, Wood.getResourceType(), "Resource: " + Wood.getResourceType(), inputCost);
    }
}
