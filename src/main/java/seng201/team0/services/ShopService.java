package seng201.team0.services;

import seng201.team0.exceptions.ActiveConsumableException;
import seng201.team0.exceptions.PurchaseException;
import seng201.team0.exceptions.TowerInventoryFullException;
import seng201.team0.exceptions.UpgradesFullException;
import seng201.team0.models.Shop;
import seng201.team0.models.consumables.Consumable;
import seng201.team0.models.towers.Tower;
import seng201.team0.models.upgrades.Upgrade;

public class ShopService {

    InventoryService inventoryService;
    Shop shop;

    public ShopService (Shop shop, InventoryService inventoryService) {
        this.shop = shop;
        this.inventoryService = inventoryService;
    }

    /**
     * Purchases tower and places in the first available slot,
     * checks in activeTowers, then in stockpiledTowers, if all are full then
     * throws TowerInventoryFullException. Throws PurchaseException if player
     * doesn't have enough coins.
     */
    public void purchaseTower(Tower tower) throws TowerInventoryFullException, PurchaseException {
        try {
            TransactionService.purchase(tower, inventoryService);
            inventoryService.addActiveTower(tower);
        } catch (TowerInventoryFullException e1) {
            try {
                TransactionService.purchase(tower, inventoryService);
                inventoryService.addStockpiledTower(tower);
            } catch (TowerInventoryFullException e2) {
                throw new TowerInventoryFullException("You have no more available tower slots");
            }
        }
    }

    public void purchaseUpgrade(Upgrade upgrade) throws PurchaseException, UpgradesFullException {
        inventoryService.checkUpgradeSpace();
        TransactionService.purchase(upgrade.getCost(), inventoryService);
        inventoryService.addUpgrade(upgrade);
    }

    public void purchaseConsumable(Consumable consumable) throws ActiveConsumableException, PurchaseException {
        for (Consumable activeConsumable : this.inventoryService.getConsumables()){
            if (activeConsumable.getClass() == consumable.getClass()) {
                throw new ActiveConsumableException("There is already an active consumable of this type in your inventory!");
            }
        }
        TransactionService.purchase(consumable.getCost(), this.inventoryService);
        this.inventoryService.addConsumable(consumable);
    }

}
