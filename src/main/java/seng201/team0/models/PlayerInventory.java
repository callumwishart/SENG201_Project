package seng201.team0.models;

import seng201.team0.exceptions.NegativeAdditionException;
import seng201.team0.exceptions.TowerInventoryFullException;
import seng201.team0.models.consumables.Consumable;
import seng201.team0.models.towers.Tower;
import seng201.team0.models.upgrades.Upgrade;

import java.awt.color.ICC_ColorSpace;
import java.util.ArrayList;
import java.util.List;

public class PlayerInventory {
    int coins;
    int points;
    ArrayList<Tower> activeTowers = new ArrayList<>();
    ArrayList<Tower> stockpiledTowers = new ArrayList<>();
    ArrayList<Upgrade> upgrades = new ArrayList<>();
    ArrayList<Consumable> consumables = new ArrayList<>();

    public PlayerInventory() {
        this.coins = 0;
        this.points = 0;
    }

    public void addActiveTower(Tower tower) throws TowerInventoryFullException {
        if ((this.activeTowers.size()) < 5) {
            this.activeTowers.add(tower);
        } else {
            throw new TowerInventoryFullException("Error: Already 5 towers are active");
        }
    }

    public void addStockpiledTower(Tower tower) throws TowerInventoryFullException {
        //Check if max num of stock piled towers is 5
        if (this.stockpiledTowers.size() < 5) {
            this.stockpiledTowers.add(tower);
        } else {
            throw new TowerInventoryFullException("Error: You already have 5 stock piled towers;");
        }
    }

    public void addUpgrade(Upgrade upgrade) {
        upgrades.add(upgrade);
    }

    public void addConsumable (Consumable consumable) {
        consumables.add(consumable);
    }

    public void repair(Tower tower) throws Exception {
        int amount = tower.getRepairCost();
        if (getCoins() >= amount) {
            tower.setToFixed();
            useCoins(amount);
        }
    }

    public void addCoins(int amount) throws NegativeAdditionException {
        if (amount >= 0) {
            coins += amount;
        } else {
            throw new NegativeAdditionException();
        }
    }

    public void addPoints(int amount) throws NegativeAdditionException {
        if (amount > 0) {
            points += amount;
        } else {
            throw new NegativeAdditionException("The amount you are tyring to add is negative");
        }
    }

    public void useCoins(int amount) throws Exception {
        if (coins >= amount) {
            coins -= amount;
        } else {
            throw new Exception("You do not have enough money");
        }
    }

    public int getCoins () {
        return coins;
    }

    public ArrayList<Tower> getActiveTowers() {
        return activeTowers;
    }

    public List<Tower> getStockpiledTowers(){
        return this.stockpiledTowers;
    }

    public void setActiveTowers(ArrayList<Tower> towers) {
        activeTowers = towers;
    }

    public ArrayList<Consumable> getConsumables() {
        return this.consumables;
    }

    public int getPoints() {
        return this.points;
    }

    public ArrayList<Upgrade> getUpgrades() {
        return this.upgrades;
    }

    public void removeUpgrade(Upgrade upgrade){
        this.upgrades.remove(upgrade);
    }

    public void removeActiveTower(Tower tower) {
        this.activeTowers.remove(tower);
    }

    public void removeStockpiledTower(Tower tower) {
        this.stockpiledTowers.remove(tower);
    }

    public void removeCoins(int amount) {
        this.coins -= amount;
    }

    public void removeConsumables() {
        this.consumables = new ArrayList<>();
    }
}
