package seng201.team0.models;

import seng201.team0.models.Upgrade.Upgrade;

import java.util.ArrayList;

public class PlayerInventory {
    int coins;
    int points;
    static ArrayList<Tower> activeTowers = new ArrayList<>();
    ArrayList<Tower> stockpiledTowers = new ArrayList<>();
    ArrayList<Upgrade> upgrades = new ArrayList<>();
    ArrayList<Booster> boosters = new ArrayList<>();

    public PlayerInventory() {
        coins = 0;
    }
    public void addActiveTower(Tower tower) throws Exception {
        if ((activeTowers.size()) < 5) {
            activeTowers.add(tower);
        } else {
            throw new Exception("Error: Already 5 towers are active");
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
    public void addBooster (Booster booster) {
        boosters.add(booster);
    }
    public void repair (Tower tower) {
        // Need to implement
    }

    public static ArrayList<Tower> getActiveTowers() {
        return activeTowers;
    }
}
