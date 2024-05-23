package seng201.team0.services;

import seng201.team0.exceptions.NegativeAdditionException;
import seng201.team0.exceptions.PurchaseException;
import seng201.team0.models.Purchasable;
import seng201.team0.models.towers.Sellable;
import seng201.team0.models.towers.Tower;

/**
 * TransactionService handles all transactions
 */
public class TransactionService {
    /**
     * Purchase Method
     * <p>
     *     This purchase method is used if the cost is not passed and removes the coins from the inventory using
     *     {@code inventoryService.removeCoins()}
     * </p>
     * @param purchasable is the item that is purchasable
     * @param inventoryService is the inventory service used to remove coins
     * @throws PurchaseException is thrown if the user does not have enough money
     */
    public static void purchase(Purchasable purchasable, InventoryService inventoryService) throws PurchaseException {
        int cost = purchasable.getCost();
        try{
            inventoryService.removeCoins(cost);
        } catch (Exception e){
            throw new PurchaseException("Cannot purchase");
        }
    }

    /**
     * Purchase Method
     * <p>
     *     This method is used if the cost is know. It tries to remove the coins and if the user does not
     *     have enough coins then it will throw a {@code PurchaseException} otherwise it will remove
     *     coins from the inventory using {@code inventoryService.removeCoins()}
     * </p>
     * @param cost is the amount trying to be removed from inventory
     * @param inventoryService is the inventoryService this will be applied to
     * @throws PurchaseException is thrown if the user does not have enough coins
     */
    public static void purchase(int cost, InventoryService inventoryService) throws PurchaseException {
        try{
            inventoryService.removeCoins(cost);
        } catch (Exception e){
            throw new PurchaseException("Cannot purchase");
        }
    }

    /**
     * Sell Method
     * <p>
     *     This method is used to sell an item, it first gets the price of the item using
     *     {@code sellable.getSellCost()}, then adds coins to the inventory using
     *     {@code inventoryService.addCoins()}
     * </p>
     * @param sellable is the item that is being sold
     * @param inventoryService is the inventory service this will be applied to
     * @throws NegativeAdditionException is thrown if the sellCost is negative
     */
    public static void sell(Sellable sellable, InventoryService inventoryService) throws NegativeAdditionException {
        int sellCost = sellable.getSellCost();
        inventoryService.addCoins(sellCost);
    }
}
