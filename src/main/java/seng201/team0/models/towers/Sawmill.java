package seng201.team0.models.towers;

import seng201.team0.models.resources.Resource;
import seng201.team0.models.resources.Wood;

/**
 * Sawmill Type tower, this generates wood.
 */
public class Sawmill extends Tower{
    /**
     * Creates new resource for tower to use
     */
    private static final Resource Wood = new Wood();
    /**
     * image path for working tower
     */
    public static String towerImagePath = "src/main/resources/images/towers/sawmill_tower.png";
    /**
     * image path for broken tower
     */
    public static String brokenImagePath = "src/main/resources/images/towers/sawmill_tower_broken.png";

    /**
     * Sawmill constructor when no params passed.
     * <p>
     *     Sets initial reload speed to 5 and initial cost to 80.
     * </p>
     */
    public Sawmill() {
        super(Wood, 5, "Sawmill", "Resource: " + Wood.getResourceType(), 80, towerImagePath, brokenImagePath);
    }
}
