package seng201.team0.models.towers;

import seng201.team0.models.resources.Resource;
import seng201.team0.models.resources.Wood;

public class Sawmill extends Tower{
    private static final Resource Wood = new Wood();
    public static String towerImagePath = "src/main/resources/images/towers/sawmill_tower.png";
    public static String brokenImagePath = "src/main/resources/images/towers/sawmill_tower_broken.png";
    public Sawmill(int inputReloadSpeed, int inputCost) {
        super(Wood, inputReloadSpeed, "Sawmill", "Resource: " + Wood.getResourceType(), inputCost, towerImagePath, brokenImagePath);
    }
}
