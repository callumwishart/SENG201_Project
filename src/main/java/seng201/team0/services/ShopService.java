package seng201.team0.services;

import seng201.team0.models.Consumables.Consumable;
import seng201.team0.models.Player;
import seng201.team0.models.PlayerInventory;
import seng201.team0.models.Shop;
import seng201.team0.models.Towers.Tower;

import java.util.ArrayList;

public class ShopService {
    Player player;
    Shop shop;
    public ShopService (Shop inputShop) {
        shop = inputShop;
    }
    public void purchaseTower(Tower tower) throws Exception {
        player.getInventory().addActiveTower(tower);
    }
    public void purchaseBooster(Consumable consumable) throws Exception {
        if (player.getInventory().getCoins() >= consumable.getCost()) {
            player.getInventory().addBooster(consumable);
        }
    }
    public ArrayList<Tower> getTowers() {
        return PlayerInventory.getActiveTowers();
    }

}
