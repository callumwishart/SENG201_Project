package seng201.team0.models.Towers;

import seng201.team0.models.Resources.Goods;
import seng201.team0.models.Resources.Resource;

public class Factory extends Tower{
    private static final Resource Goods = new Goods();
    public static String towerImagePath = "resources/images/towers/factory_tower.png";
    public static String brokenImagePath = "resources/images/towers/factory_tower_broken.png";
    public Factory(int inputReloadSpeed, int inputCost) {
        super(Goods, inputReloadSpeed, Goods.getResourceType(), "Resource: " + Goods.getResourceType(), inputCost, towerImagePath, brokenImagePath);
    }
}
