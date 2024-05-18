package seng201.team0.models.Towers;

import seng201.team0.models.Resources.Goods;
import seng201.team0.models.Resources.Resource;

public class Factory extends Tower{
    private static final Resource Goods = new Goods();
    public static String towerImagePath = "src/main/resources/images/towers/factory_tower.png";
    public static String brokenImagePath = "src/main/resources/images/towers/factory_tower_broken.png";
    public Factory(int inputReloadSpeed, int inputCost) {
        super(Goods, inputReloadSpeed, "Factory", "Resource: " + Goods.getResourceType(), inputCost, towerImagePath, brokenImagePath);
    }
}
