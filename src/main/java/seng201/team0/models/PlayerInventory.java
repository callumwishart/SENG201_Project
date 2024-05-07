package seng201.team0.models;

import java.util.ArrayList;

public class PlayerInventory {
    int coins;
    ArrayList<Tower> activeTowers = new ArrayList<>();
    ArrayList<Tower> stockpiledTowers = new ArrayList<>();
    ArrayList<Upgrade> upgrades = new ArrayList<>();
    ArrayList<Booster> boosters = new ArrayList<>();

    public PlayerInventory() {
        coins = 0;
    }
}
