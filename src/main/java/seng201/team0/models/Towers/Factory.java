package seng201.team0.models.Towers;

import seng201.team0.models.Resources.Goods;
import seng201.team0.models.Resources.Resource;

public class Factory extends Tower{
    private static final Resource Goods = new Goods();
    public static String towerImagePath = "";
    public static String brokenImagePath = "";
    public Factory(int inputReloadSpeed, double inputCost) {
        super(Goods, inputReloadSpeed, Goods.getResourceType(), "Resource: " + Goods.getResourceType(), inputCost);
    }
}
