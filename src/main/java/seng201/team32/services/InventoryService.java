package seng201.team32.services;

import seng201.team32.exceptions.*;
import seng201.team32.models.Player;
import seng201.team32.models.PlayerInventory;
import seng201.team32.models.consumables.Consumable;
import seng201.team32.models.towers.Tower;
import seng201.team32.models.upgrades.Upgrade;

import java.util.ArrayList;
import java.util.List;

/**
 * Inventory Service
 * <p>
 *     This service is used so that the gui does not directly act with the inventory
 * </p>
 */
public class InventoryService {

    /**
     * The Player's Inventory
     * <p>
     *     This inventory stores all the items that the player currently possesses.
     *     This is final, meaning it cannot be changed.
     * </p>
     */
    private final PlayerInventory inventory;

    /**
     * Inventory Service Constructor
     * <p>
     *     This constructor accepts a player and sets {@code this.inventory} to the {@code player.getInventory()}.
     * </p>
     * @param player The player that this will get constructed with.
     */
    public InventoryService(Player player){
        this.inventory = player.getInventory();
    }

    /**
     * Inventory Service Constructor
     * <p>
     *     This constructor accepts an inventory and assigns {@code this.inventory} to the {@code inventory}.
     * </p>
     * @param inventory The inventory that the inventory service will use.
     */
    public InventoryService(PlayerInventory inventory){
        this.inventory = inventory;
    }

    /**
     * Add Coins Method
     * <p>
     *     This method will add coins to the inventory using {@code this.inventory.addCoins()}.
     *     The value that will be added is the integer {@code amount}
     * </p>
     * @param amount the amount that will be added to the inventory.
     * @throws NegativeAdditionException is thrown if the amount trying to be added is negative.
     */
    public void addCoins(int amount) throws NegativeAdditionException {
        this.inventory.addCoins(amount);
    }

    /**
     * Add Points Method
     * <p>
     *     This method will add points to the inventory using {@code this.inventory.addPoints()}.
     * </p>
     * @param amount is the amount of points that will be added to the inventory.
     * @throws NegativeAdditionException is thrown if the amount trying to be added is negative.
     */
    public void addPoints(int amount) throws NegativeAdditionException {
        this.inventory.addPoints(amount);
    }

    /**
     * Get Points Method
     * <p>
     *     This method will get the number of points in inventory using {@code this.inventory.getPoints()}
     * </p>
     * @return the value of the points in the players inventory.
     */
    public int getPoints(){
        return this.inventory.getPoints();
    }

    /**
     * Get Coins Method
     * <p>
     *     This method will get the number of coins in inventory using {@code this.inventory.getCoins()}
     * </p>
     * @return the value of the coins in the players inventory
     */
    public int getCoins(){
        return this.inventory.getCoins();
    }

    /**
     * Get Active Towers Method
     * <p>
     *     This method will get the active towers in the players inventory
     *     using {@code this.inventory.getActiveTowers()}
     * </p>
     * @return the active towers in the players inventory as an ArrayList
     */
    public ArrayList<Tower> getActiveTowers(){
        return this.inventory.getActiveTowers();
    }

    /**
     * Get Stockpiled Towers Method
     * <p>
     *     This method will get the stockpiled towers in the players inventory
     *     using {@code this.inventory.getStockpiledTowers()}
     * </p>
     * @return the stockpiled towers in the players inventory as an ArrayList
     */
    public List<Tower> getStockpiledTowers(){
        return this.inventory.getStockpiledTowers();
    }

    /**
     * Get Upgrades Method
     * <p>
     *     This method will get the upgrades in the players inventory
     *     using {@code this.inventory.getUpgrades()}
     * </p>
     * @return the upgrades in the players inventory as an ArrayList
     */
    public ArrayList<Upgrade> getUpgrades(){
        return this.inventory.getUpgrades();
    }

    /**
     * Get Consumables method
     * <p>
     *     This method will get the consumables in the players inventory
     *     using {@code this.inventory.getConsumables()}
     * </p>
     * @return the consumables in the players inventory as an ArrayList
     */
    public ArrayList<Consumable> getConsumables(){
        return this.inventory.getConsumables();
    }

    /**
     * Remove Active Tower Method
     * <p>
     *     This method will remove the tower from the inventory using
     *     {@code this.inventory.removeActiveTower()}
     * </p>
     * @param tower is the tower that will be removed from the inventory
     */
    public void removeActiveTower(Tower tower){
        this.inventory.removeActiveTower(tower);
    }

    /**
     * Remove Stock Piled Tower Method
     * <p>
     *     This method will remove the tower from the inventory using
     *     {@code this.inventory.removeStockpiledTower()}
     * </p>
     * @param tower is the tower that will be removed from the inventory
     */
    public void removeStockpiledTower(Tower tower){
        this.inventory.removeStockpiledTower(tower);
    }

    /**
     * Add active Tower Method
     * <p>
     *     This method will add the tower to the inventory using
     *     {@code this.inventory.addActiveTower()}
     * </p>
     * @param tower is the tower that will be added to the inventory
     * @throws TowerInventoryFullException is thrown if the inventory is full
     */
    public void addActiveTower(Tower tower) throws TowerInventoryFullException {
        this.inventory.addActiveTower(tower);
    }

    /**
     * Add Stockpiled Tower Method
     * <p>
     *     This method will add the tower to the inventory using
     *     {@code this.inventory.addStockPiledTower()}
     * </p>
     * @param tower is the tower that will be added to the inventory
     * @throws TowerInventoryFullException is thrown is the inventory is full
     */
    public void addStockpiledTower(Tower tower) throws TowerInventoryFullException {
        this.inventory.addStockpiledTower(tower);
    }

    /**
     * Swap Towers Method
     * <p>
     *     This method will swap the towers using {@code this.removeActiveTower()}, then
     *     {@code removeStockpiledTower()}, then {@code addActiveTower()} then,
     *     {@code addStockpiledTower()}.
     * </p>
     * @param activeTower is the active tower that will get swapped
     * @param stockpiledTower is the stockpiled tower that will get swapped
     * @throws TowerInventoryFullException is thrown if the inventory is full
     */
    public void swapTowers(Tower activeTower, Tower stockpiledTower) throws TowerInventoryFullException {
        this.removeActiveTower(activeTower);
        this.removeStockpiledTower(stockpiledTower);
        this.addActiveTower(stockpiledTower);
        this.addStockpiledTower(activeTower);
    }

    /**
     * Swap Towers Method
     * <p>
     *     This method will swap a singular tower between activeTowers and stockpiledTower
     *     depending on which is given. This is done using the methods {@code this.removeActiveTower()},
     *     {@code removeStockpiledTower()}, {@code addActiveTower()},
     *     {@code addStockpiledTower()}.
     * </p>
     * @param tower is the tower that this will get applied to
     * @param active is to determine if the tower this is being applied to is in activeTowers or stockpiledTowers
     * @throws TowerInventoryFullException is thrown if the inventory is full
     */
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

    /**
     * Apply Upgrade Method
     * <p>
     *     This method first applies the upgrade using {@code upgrade.apply()}, then removes the
     *     upgrade from the inventory using {@code removeUpgrade()}
     * </p>
     * @param tower is the tower the upgrade is being applied to
     * @param upgrade is the upgrade that is being applied
     * @throws UpgradeException is thrown if the upgrade not able to applied if the player does not have enough points
     * @throws UpgradeMaxException is thrown if the tower has reached its max number of upgrades
     */
    public void applyUpgrade(Tower tower, Upgrade upgrade) throws UpgradeException, UpgradeMaxException {
        upgrade.apply(tower, this.getPoints());
        removeUpgrade(upgrade); // consumes from the inventory
    }

    /**
     * Remove Upgrade Method
     * <p>
     *     This method is used to remove the upgrade from the inventory using
     *     {@code this.inventory.removeUpgrade()}
     * </p>
     * @param upgrade is the upgrade that is being removed
     */
    public void removeUpgrade(Upgrade upgrade){
        this.inventory.removeUpgrade(upgrade);
    }

    /**
     * Sell Tower Method
     * <p>
     *     This method is used to sell a tower using {@code TransationService.sell()}, then uses
     *     {@code this.removeTower()} to remove the tower.
     * </p>
     * @param tower is the tower that will be sold
     * @throws NegativeAdditionException is thrown if a negative number is used when trying to addCoins
     * @throws TowerNotFoundException is thrown if the tower has not been found
     */
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

    /**
     * Remove Coins Method
     * <p>
     *     This method removes the desired amount of coins from the inventory by first
     *     checking if the player has that many coins where if not will throw an exception and if so
     *     will use {@code this.inventory.removeCoins(amount)} to remove the coins.
     * </p>
     * @param amount is the amount of coins that is being removed
     * @throws Exception is thrown is the player does not have that many coins
     */
    public void removeCoins(int amount) throws Exception{
        if (amount <= this.getCoins()){
            this.inventory.removeCoins(amount);
        }
        else {
            throw new Exception(String.format("Removing %d will result in negative coin balance", amount));
        }
    }

    /**
     * Reset Coins and Points Method
     * <p>
     *     This method will reset the coins and points to 0 using
     *     {@code this.inventory.resetCoinsAndPoints()}
     * </p>
     */
    public void resetCoinsAndPoints() {
        this.inventory.resetCoinsAndPoints();
    }

    /**
     * Add Upgrade Method
     * <p>
     *     This method will add an upgrade to the inventory using
     *     {@code this.inventory.addUpgrade()}
     * </p>
     * @param upgrade is the upgrade that will be added.
     */
    public void addUpgrade(Upgrade upgrade) {
        this.inventory.addUpgrade(upgrade);
    }

    /**
     * Add Consumable Method
     * <p>
     *     This method will add a consumable to the inventory using
     *     {@code this.inventory.addConsumable()}
     * </p>
     * @param consumable is the consumable to be added to the inventory
     */
    public void addConsumable(Consumable consumable) {
        this.inventory.addConsumable(consumable);
    }

    /**
     * Remove Consumables Method
     * <p>
     *     This method will remove all consumables from the inventory using
     *     {@code this.removeConsumables()}
     * </p>
     */
    public void removeConsumables() {
        this.inventory.removeConsumables();
    }

    /**
     * Check Upgrade Space Method
     * <p>
     *     This method will check if there is enough space in the inventory to add an upgrade
     *     if there is not then {@code UpgradesFullException} will be thrown.
     * </p>
     * @throws UpgradesFullException is thrown is there is no space for the upgrade.
     */
    public void checkUpgradeSpace() throws UpgradesFullException{
        if (this.inventory.getUpgrades().size() >= 3){
            throw new UpgradesFullException();
        }
    }
}
