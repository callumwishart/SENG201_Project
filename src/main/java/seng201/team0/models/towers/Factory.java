package seng201.team0.models.towers;

import seng201.team0.models.resources.Goods;
import seng201.team0.models.resources.Resource;

/**
 * Factor Tower type which generates Goods
 */
public class Factory extends Tower{
    private static final Resource Goods = new Goods();
    public static String towerImagePath = "src/main/resources/images/towers/factory_tower.png";
    public static String brokenImagePath = "src/main/resources/images/towers/factory_tower_broken.png";

    /**
     * Factor Constructor when params passed
     * @param inputReloadSpeed initial reload speed of tower
     * @param inputCost initial cost of tower
     */
    public Factory(int inputReloadSpeed, int inputCost) {
        super(Goods, inputReloadSpeed, "Factory", "Resource: " + Goods.getResourceType(), inputCost, towerImagePath, brokenImagePath);
    }

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
