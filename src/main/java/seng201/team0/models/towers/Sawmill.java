package seng201.team0.models.towers;

import seng201.team0.models.resources.Resource;
import seng201.team0.models.resources.Wood;

/**
 * Sawmill Type tower, this generates wood.
 */
public class Sawmill extends Tower{
    private static final Resource Wood = new Wood();
    public static String towerImagePath = "src/main/resources/images/towers/sawmill_tower.png";
    public static String brokenImagePath = "src/main/resources/images/towers/sawmill_tower_broken.png";

    /**
     * Sawmill constructor when no params passed.
     * <p>
     *     Sets initial reload speed to 5 and initial cost to 80.
     * </p>
     */
    public Sawmill() {
        super(Wood, 3, "Sawmill", "Resource: " + Wood.getResourceType(), 350, towerImagePath, brokenImagePath);
    }
}
