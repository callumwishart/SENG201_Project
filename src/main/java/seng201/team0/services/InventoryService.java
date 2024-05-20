package seng201.team0.services;

import seng201.team0.exceptions.*;
import seng201.team0.models.Player;
import seng201.team0.models.PlayerInventory;
import seng201.team0.models.consumables.Consumable;
import seng201.team0.models.towers.Tower;
import seng201.team0.models.upgrades.Upgrade;

import java.util.ArrayList;
import java.util.List;

public class InventoryService {

    private final PlayerInventory inventory;

    public InventoryService(Player player){
        this.inventory = player.getInventory();
    }

    public InventoryService(PlayerInventory inventory){
        this.inventory = inventory;
    }

    public void addCoins(int amount) throws NegativeAdditionException {
        this.inventory.addCoins(amount);
    }

    public void addPoints(int amount) throws NegativeAdditionException {
        this.inventory.addPoints(amount);
    }

    public int getPoints(){
        return this.inventory.getPoints();
    }

    public int getCoins(){
        return this.inventory.getCoins();
    }

    public ArrayList<Tower> getActiveTowers(){
        return this.inventory.getActiveTowers();
    }

    public List<Tower> getStockpiledTowers(){
        return this.inventory.getStockpiledTowers();
    }

    public ArrayList<Upgrade> getUpgrades(){
        return this.inventory.getUpgrades();
    }

    public ArrayList<Consumable> getConsumables(){
        return this.inventory.getConsumables();
    }

    public void removeActiveTower(Tower tower){
        this.inventory.removeActiveTower(tower);
    }

    public void removeStockpiledTower(Tower tower){
        this.inventory.removeStockpiledTower(tower);
    }

    public void addActiveTower(Tower tower) throws TowerInventoryFullException {
        this.inventory.addActiveTower(tower);
    }

    public void addStockpiledTower(Tower tower) throws TowerInventoryFullException {
        this.inventory.addStockpiledTower(tower);
    }

    public void swapTowers(Tower activeTower, Tower stockpiledTower) throws TowerInventoryFullException {
        this.removeActiveTower(activeTower);
        this.removeStockpiledTower(stockpiledTower);
        this.addActiveTower(stockpiledTower);
        this.addStockpiledTower(activeTower);
    }

    public void swapTowers(Tower tower, boolean active) throws TowerInventoryFullException {
        if (active){
            this.removeActiveTower(tower);
            this.addStockpiledTower(tower);
        }
        else{
            this.removeStockpiledTower(tower);
            this.addActiveTower(tower);
        }
    }

    public void applyUpgrade(Tower tower, Upgrade upgrade) throws UpgradeException {
        upgrade.applyUpgrade(tower, this.getPoints());
    }

    public void sellTower(Tower tower) throws NegativeAdditionException, TowerNotFoundException {
        TransactionService.sell(tower, this); // handles adding coins to inventory
        this.removeTower(tower);
    }

    /**
     * Checks for tower in both Active and Stockpiled lists,
     * if it is not found then throws TowerNotFoundException.
     * If it is found then delete out of the respective list.
     */
    private void removeTower(Tower tower) throws TowerNotFoundException {
        if (this.getActiveTowers().contains(tower)){
            removeActiveTower(tower);
        } else if (this.getStockpiledTowers().contains(tower)) {
            removeStockpiledTower(tower);
        }
        else {
            throw new TowerNotFoundException();
        }
    }

    /**
     * Attempts to purchase the repair cost, then changes broken attribute
     * of given tower.
     */
    public void repairTower(Tower tower) throws Exception {
        TransactionService.purchase(tower.getRepairCost(), this);
        tower.setToFixed();
    }

    public void removeCoins(int amount) throws Exception{
        if (amount <= this.getCoins()){
            this.inventory.removeCoins(amount);
        }
        else {
            throw new Exception(String.format("Removing %d will result in negative coin balance", amount));
        }
    }

    public void addUpgrade(Upgrade upgrade) {
        this.inventory.addUpgrade(upgrade);
    }

    public void addConsumable(Consumable consumable) {
        this.inventory.addConsumable(consumable);
    }

    public void removeConsumables() {
        this.inventory.removeConsumables();
    }
}
