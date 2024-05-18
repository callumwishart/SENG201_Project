package seng201.team0.models.towers;

import seng201.team0.models.resources.Resource;
import seng201.team0.models.resources.Metal;

public class Mine extends Tower {
    private static final Resource Metal = new Metal();
    public static String towerImagePath = "src/main/resources/images/towers/mine_tower.png";
    public static String brokenImagePath = "src/main/resources/images/towers/mine_tower_broken.png";
    public Mine(int inputReloadSpeed, int inputCost) {
        super(Metal, inputReloadSpeed, "Mine", "Resource: " + Metal.getResourceType(), inputCost, towerImagePath, brokenImagePath);
    }
}
