package seng201.team0.models.Towers;

import seng201.team0.models.Resources.Resource;
import seng201.team0.models.Resources.Wood;

public class Sawmill extends Tower{
    private static final Resource Wood = new Wood();
    public static String towerImagePath = "resources/images/towers/sawmill_tower.png";
    public static String brokenImagePath = "resources/images/towers/sawmill_tower_broken.png";
    public Sawmill(int inputReloadSpeed, double inputCost) {
        super(Wood, inputReloadSpeed, Wood.getResourceType(), "Resource: " + Wood.getResourceType(), inputCost);
    }
}
