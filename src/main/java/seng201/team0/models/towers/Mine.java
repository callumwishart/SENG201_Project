package seng201.team0.models.towers;

import seng201.team0.models.resources.Resource;
import seng201.team0.models.resources.Metal;

/**
 * Mine Tower
 * <p>
 *     This tower is a mine and has the resource metal.
 * </p>
 */
public class Mine extends Tower {
    private static final Resource Metal = new Metal();
    public static String towerImagePath = "src/main/resources/images/towers/mine_tower.png";
    public static String brokenImagePath = "src/main/resources/images/towers/mine_tower_broken.png";

    /**
     * Mine Constructor when no params are passed
     * This will initialise the tower with a reload speed of 5 and a cost of 300
     */
    public Mine() {
        super(Metal, 5, "Mine", "Resource: " + Metal.getResourceType(), 300, towerImagePath, brokenImagePath);
    }
}
