package seng201.team0.models.Towers;

import seng201.team0.models.Resources.Resource;
import seng201.team0.models.Resources.Wood;

public class Sawmill extends Tower{
    private static final Resource Wood = new Wood();
    public static String towerImagePath = "src/main/resources/images/towers/sawmill_tower.png";
    public static String brokenImagePath = "src/main/resources/images/towers/sawmill_tower_broken.png";
    public Sawmill(int inputReloadSpeed, int inputCost) {
        super(Wood, inputReloadSpeed, "Sawmill", "Resource: " + Wood.getResourceType(), inputCost, towerImagePath, brokenImagePath);
    }
}
