package seng201.team32.models;

import seng201.team32.exceptions.NegativeAdditionException;
import seng201.team32.exceptions.TowerInventoryFullException;
import seng201.team32.models.consumables.Consumable;
import seng201.team32.models.towers.Tower;
import seng201.team32.models.upgrades.Upgrade;

import java.util.ArrayList;
import java.util.List;

/**
 * Player inventory manage what the player keeps
 */
public class PlayerInventory {
    /**
     * Class attribute to store the number of coins the player has
     */
    int coins;
    /**
     * Class attribute to store the number of points the player has
     */
    int points;
    /**
     * Class attribute to store the active towers
     */
    ArrayList<Tower> activeTowers = new ArrayList<>();
    /**
     * Class attribute to store the stockpiled towers
     */
    ArrayList<Tower> stockpiledTowers = new ArrayList<>();
    /**
     * Class attributes to store the upgrades the player has
     */
    ArrayList<Upgrade> upgrades = new ArrayList<>();
    /**
     *
     */
    ArrayList<Consumable> consumables = new ArrayList<>();

    /**
     * Constructor of the player inventory which sets {@code coins} to 0 and {@code points} to 0.
     */
    public PlayerInventory() {
        this.coins = 0;
        this.points = 0;
    }

    /**
     * Adds a tower to active towers
     * <p>
     *     Checks if there is space in active tower list to add the tower by checking the size of {@code activeTowers}
     *     This then adds the tower otherwise it throws a {@code TowerInventoryFullException}
     * </p>
     * @param tower is the tower that should be added to the inventory
     * @throws TowerInventoryFullException is thrown if the tower inventory is full
     */
    public void addActiveTower(Tower tower) throws TowerInventoryFullException {
        if ((this.activeTowers.size()) < 5) {
            this.activeTowers.add(tower);
        } else {
            throw new TowerInventoryFullException("Error: Already 5 towers are active");
        }
    }

    /**
     * Adds a tower to stockpiled towers
     * <p>
     *     Checks if there is room for the tower by checking the size of {@code stockpiledTowers} which throws
     *     {@code TowerInventoryFullException} if there is other room otherwise it is added to the inventory.
     * </p>
     * @param tower is the tower being added to stockpiledTowers
     * @throws TowerInventoryFullException is thrown if the inventory is full
     */
    public void addStockpiledTower(Tower tower) throws TowerInventoryFullException {
        //Check if max num of stock piled towers is 5
        if (this.stockpiledTowers.size() < 5) {
            this.stockpiledTowers.add(tower);
        } else {
            throw new TowerInventoryFullException("Error: You already have 5 stock piled towers;");
        }
    }

    /**
     * Add upgrade to inventory
     * <p>
     *     Takes an upgrade and adds it to the inventory with no checks
     * </p>
     * @param upgrade is the upgrade that is being added to inventory
     */
    public void addUpgrade(Upgrade upgrade) {
        upgrades.add(upgrade);
    }

    /**
     * Adds a consumable to inventory
     * <p>
     *     Takes a consumable and adds it to the list with no checks
     * </p>
     * @param consumable is the consumable being added
     */
    public void addConsumable (Consumable consumable) {
        consumables.add(consumable);
    }

    /**
     * Add coins to the inventory
     * <p>
     *     First checks if the amount being added is positive and throws a {@code NegativeAdditionException} if it is not
     *     other wise it adds the amount to the coins.
     * </p>
     * @param amount is the amount being added to the player inventory
     * @throws NegativeAdditionException is thrown if the amount trying to be added is negative
     */
    public void addCoins(int amount) throws NegativeAdditionException {
        if (amount >= 0) {
            coins += amount;
        } else {
            throw new NegativeAdditionException();
        }
    }

    /**
     * Add points to the inventory
     * <p>
     *     First checks if the amount being added is negative and throws a {@code NegativeAdditionException} if it is
     *     not and otherwise it adds the amount to the points
     * </p>
     * @param amount is the amount being added to the  inventory
     * @throws NegativeAdditionException is thrown if the amount trying to be added is negative
     */
    public void addPoints(int amount) throws NegativeAdditionException {
        if (amount > 0) {
            points += amount;
        } else {
            throw new NegativeAdditionException("The amount you are tyring to add is negative");
        }
    }

    /**
     * Resets the coins and points to zero.
     * <p>
     *     This method simply resets the coins and points to zero
     * </p>
     */
    public void resetCoinsAndPoints() {
        this.coins = 0;
        this.points =0;
    }

    /**
     * Gets the number of coins in the inventory
     * @return the number of coins in inventory as an integer
     */
    public int getCoins () {
        return coins;
    }

    /**
     * Gets the active towers in inventory
     * @return the active towers as an ArrayList
     */
    public ArrayList<Tower> getActiveTowers() {
        return activeTowers;
    }

    /**
     * Gets the stockpiled towers
     * @return the stockpiled towers as an ArrayList
     */
    public List<Tower> getStockpiledTowers(){
        return this.stockpiledTowers;
    }

    /**
     * Sets the active towers to a list
     * @param towers is an ArrayList of towers that active towers will be set to
     */
    public void setActiveTowers(ArrayList<Tower> towers) {
        activeTowers = towers;
    }

    /**
     * Gets the consumables in the inventory
     * @return the consumables in the inventory as an ArrayList
     */
    public ArrayList<Consumable> getConsumables() {
        return this.consumables;
    }

    /**
     * Get the number of points
     * @return the number of points as an Integer
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * Gets the upgrades in inventory
     * @return the upgrades in inventory as an ArrayList
     */
    public ArrayList<Upgrade> getUpgrades() {
        return this.upgrades;
    }

    /**
     * Remove an upgrades use {@code remove()}
     * @param upgrade is the upgrade that you are trying to remove
     */
    public void removeUpgrade(Upgrade upgrade){
        this.upgrades.remove(upgrade);
    }

    /**
     * Removes an active tower used {@code remove()}
     * @param tower is the tower you are removing
     */
    public void removeActiveTower(Tower tower) {
        this.activeTowers.remove(tower);
    }

    /**
     * Removes a stockpiled tower using {@code remove()}
     * @param tower is the tower you are tyring to remove
     */
    public void removeStockpiledTower(Tower tower) {
        this.stockpiledTowers.remove(tower);
    }

    /**
     * Removes coins from the players inventory
     * @param amount is the amount you are tyring to remove
     */
    public void removeCoins(int amount) {
        this.coins -= amount;
    }

    /**
     * Removes all consumables from the players inventory
     * <p>
     *     Removes all consumables from the players inventory by creating a new ArrayList and
     *     setting {@code this.consumables} to it
     * </p>
     */
    public void removeConsumables() {
        this.consumables = new ArrayList<>();
    }
}
