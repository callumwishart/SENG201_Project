package seng201.team0.models;

import seng201.team0.exceptions.NegativeAdditionException;
import seng201.team0.exceptions.TowerInventoryFullException;
import seng201.team0.models.Consumables.Consumable;
import seng201.team0.models.Towers.Tower;
import seng201.team0.models.Upgrades.Upgrade;

import java.util.ArrayList;
import java.util.List;

public class PlayerInventory {
    int coins;
    int points;
    static List<Tower> activeTowers;
    ArrayList<Tower> stockpiledTowers = new ArrayList<>();
    ArrayList<Upgrade> upgrades = new ArrayList<>();
    ArrayList<Consumable> consumables = new ArrayList<>();

    public PlayerInventory() {
        coins = 0;
    }
    public void addActiveTower(Tower tower) throws Exception {
        if ((activeTowers.size()) < 5) {
            activeTowers.add(tower);
        } else {
            throw new TowerInventoryFullException("Error: Already 5 towers are active");
        }
    }
    public void stockPileTower (Tower tower) throws Exception {
        //Check if max num of stock piled towers is 5
        if (stockpiledTowers.size() < 5) {
            stockpiledTowers.add(tower);
        } else {
            throw new Exception("Error: You already have 5 stock piled towers;");
        }
    }
    public void addUpgrade(Upgrade upgrade) {
        upgrades.add(upgrade);
    }
    public void addConsumable (Consumable consumable) {
        consumables.add(consumable);
    }
    public void repair (Tower tower) throws Exception {
        int amount = tower.getRepairCost();
        if (getCoins() >= amount) {
            tower.setToFixed();
            useCoins(amount);
        }

    }
    public void addCoins(int amount) throws NegativeAdditionException {
        if (amount > 0) {
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

    public static List<Tower> getActiveTowers() {
        return activeTowers;
    }
    public void setActiveTowers(List<Tower> towers) {
        activeTowers = towers;
    }

}
