package seng201.team32.models.towers;

import seng201.team32.models.resources.Goods;
import seng201.team32.models.resources.Resource;

/**
 * Factor Tower type which generates Goods
 */
public class Factory extends Tower{
    /**
     * Creates new resource for the tower to use
     */
    private static final Resource Goods = new Goods();
    /**
     * image path for working tower
     */
    public static String towerImagePath = "/images/towers/factory_tower.png";
    /**
     * image path for broken tower
     */
    public static String brokenImagePath = "/images/towers/factory_tower_broken.png";

    /**
     * Factory Constructor when no params passed
     * <p>
     *    Initialises the inputReloadSpeed to 5 and the cost to 500.
     * </p>
     */
    public Factory() {
        super(Goods, 5, "Factory", "Resource: " + Goods.getResourceType(), 500, towerImagePath, brokenImagePath);
    }
}
