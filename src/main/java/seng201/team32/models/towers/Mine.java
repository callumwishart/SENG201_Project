package seng201.team32.models.towers;

import seng201.team32.models.resources.Resource;
import seng201.team32.models.resources.Metal;

/**
 * Mine Tower
 * <p>
 *     This tower is a mine and has the resource metal.
 * </p>
 */
public class Mine extends Tower {
    /**
     * Creates resource for tower to use
     */
    private static final Resource Metal = new Metal();
    /**
     * image path for working tower
     */
    public static String towerImagePath = "/images/towers/mine_tower.png";
    /**
     * image path for broken tower
     */
    public static String brokenImagePath = "/images/towers/mine_tower_broken.png";

    /**
     * Mine Constructor when no params are passed
     * This will initialise the tower with a reload speed of 5 and a cost of 300
     */
    public Mine() {
        super(Metal, 4, "Mine", "Resource: " + Metal.getResourceType(), 400, towerImagePath, brokenImagePath);
    }
}
