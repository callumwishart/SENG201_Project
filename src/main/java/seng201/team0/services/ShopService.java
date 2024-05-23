package seng201.team0.services;

import seng201.team0.exceptions.ActiveConsumableException;
import seng201.team0.exceptions.PurchaseException;
import seng201.team0.exceptions.TowerInventoryFullException;
import seng201.team0.exceptions.UpgradesFullException;
import seng201.team0.models.Difficulty;
import seng201.team0.models.Shop;
import seng201.team0.models.consumables.Consumable;
import seng201.team0.models.towers.Tower;
import seng201.team0.models.upgrades.Upgrade;

/**
 * Shop Service class
 * <p>
 *     This class is used so that the gui does not directly interact with the shop
 * </p>
 */
public class ShopService {
    /**
     * Inventory service holds the inventory service so that it can make changes in the inventory
     */
    InventoryService inventoryService;
    /**
     * shop holds the instance of shop that this ShopService should manage
     */
    Shop shop;
    private Difficulty difficulty;

    /**
     * Shop Service Constructor
     * <p>
     *     Assigns {@code shop} to {@code this.shop} and {@code inventoryService} to {@code this.inventoryService}
     * </p>
     * @param shop is the shop that this service will manage
     * @param inventoryService is the inventoryService that this shop service can interact with
     */
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
            TransactionService.purchase((int) (tower.getCost() * this.difficulty.getCostMultiplier()), inventoryService);
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

    /**
     * Purchase Upgrade Method
     * <p>
     *     This method first checks if there is space for an upgrade using {@code inventoryService.checkUpgradeSpace()}
     *     then purchases the upgrade using {@code TransactionService.purchase()}, then adds the upgrade to the
     *     inventory using {@code inventoryService.addUpgrade()}
     * </p>
     * @param upgrade is the upgrade that is being purchased
     * @throws PurchaseException is thrown when the item cannot be purchased
     * @throws UpgradesFullException is thrown when the inventory is full
     */
    public void purchaseUpgrade(Upgrade upgrade) throws PurchaseException, UpgradesFullException {
        inventoryService.checkUpgradeSpace();
        TransactionService.purchase((int) (upgrade.getCost() * this.difficulty.getCostMultiplier()), inventoryService);
        inventoryService.addUpgrade(upgrade);
    }

    /**
     * Purchase Consumable Method
     * <p>
     *     This method first checks if this consumable is already present and throws an
     *     {@code ActiveConsumerException} if so, otherwise it purchases the consumable using
     *     {@code TransactionService.purchase()}. It then adds the consumable to the inventory using
     *     {@code this.inventoryService.addConsumable()}
     * </p>
     * @param consumable is the consumable that is being purchased
     * @throws ActiveConsumableException is thrown if the consumable is already active
     * @throws PurchaseException is thrown if the item cannot be purchased
     */
    public void purchaseConsumable(Consumable consumable) throws ActiveConsumableException, PurchaseException {
        for (Consumable activeConsumable : this.inventoryService.getConsumables()){
            if (activeConsumable.getClass() == consumable.getClass()) {
                throw new ActiveConsumableException("There is already an active consumable of this type in your inventory!");
            }
        }
        TransactionService.purchase((int) (consumable.getCost() * this.difficulty.getCostMultiplier()), this.inventoryService);
        this.inventoryService.addConsumable(consumable);
    }

    public void setDifficulty(Difficulty difficulty){
        this.difficulty = difficulty;
    }

}
