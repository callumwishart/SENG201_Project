package seng201.team0.models.Towers;

import seng201.team0.models.Resources.Resource;
import seng201.team0.models.Resources.Metal;

public class Mine extends Tower {
    private static final Resource Metal = new Metal();
    public static String towerImagePath = "resources/images/towers/mine_tower.png";
    public static String brokenImagePath = "resources/images/towers/mine_tower_broken.png";
    public Mine(int inputReloadSpeed, int inputCost) {
        super(Metal, inputReloadSpeed, Metal.getResourceType(), "Resource: " + Metal.getResourceType(), inputCost, towerImagePath, brokenImagePath);
    }
}
