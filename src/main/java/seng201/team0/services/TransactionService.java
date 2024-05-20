package seng201.team0.services;

import seng201.team0.exceptions.NegativeAdditionException;
import seng201.team0.exceptions.PurchaseException;
import seng201.team0.models.Purchasable;
import seng201.team0.models.towers.Sellable;
import seng201.team0.models.towers.Tower;

public class TransactionService {

    public static void purchase(Purchasable purchasable, InventoryService inventoryService) throws Exception {
        int cost = purchasable.getCost();
        if (cost <= inventoryService.getCoins()){
            inventoryService.removeCoins(cost);
        }
        else {
            throw new PurchaseException("Cannot purchase");
        }
    }

    /**
     *
     */
    public static void purchase(int cost, InventoryService inventoryService) throws Exception {
        if (cost <= inventoryService.getCoins()){
            inventoryService.removeCoins(cost);
        }
        else {
            throw new PurchaseException("Cannot purchase");
        }
    }

    public static void sell(Sellable sellable, InventoryService inventoryService) throws NegativeAdditionException {
        int sellCost = sellable.getSellCost();
        inventoryService.addCoins(sellCost);
    }
}
