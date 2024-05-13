package seng201.team0.models.Towers;

import seng201.team0.models.Resources.Resource;
import seng201.team0.models.Resources.Metal;

public class Mine extends Tower {
    private static final Resource Metal = new Metal();
    public static String towerImagePath = "";
    public static String brokenImagePath = "";
    public Mine(int inputReloadSpeed, double inputCost) {
        super(Metal, inputReloadSpeed, Metal.getResourceType(), "Resource: " + Metal.getResourceType(), inputCost);
    }
}
