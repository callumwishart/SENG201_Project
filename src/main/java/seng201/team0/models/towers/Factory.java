package seng201.team0.models.towers;

import seng201.team0.models.resources.Goods;
import seng201.team0.models.resources.Resource;

public class Factory extends Tower{
    private static final Resource Goods = new Goods();
    public static String towerImagePath = "src/main/resources/images/towers/factory_tower.png";
    public static String brokenImagePath = "src/main/resources/images/towers/factory_tower_broken.png";
    public Factory(int inputReloadSpeed, int inputCost) {
        super(Goods, inputReloadSpeed, "Factory", "Resource: " + Goods.getResourceType(), inputCost, towerImagePath, brokenImagePath);
    }
    public Factory() {
        super(Goods, 5, "Factory", "Resource: " + Goods.getResourceType(), 500, towerImagePath, brokenImagePath);
    }
}
