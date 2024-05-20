package seng201.team0.models;

import seng201.team0.models.consumables.Consumable;
import seng201.team0.models.consumables.Shield;
import seng201.team0.models.consumables.SlowCartBooster;
import seng201.team0.models.consumables.TowerSpeedBooster;
import seng201.team0.models.towers.*;
import seng201.team0.models.upgrades.CapacityUpgrade;
import seng201.team0.models.upgrades.MoneyUpgrade;
import seng201.team0.models.upgrades.SpeedUpgrade;
import seng201.team0.models.upgrades.Upgrade;

import java.util.ArrayList;

public class Shop {
    ArrayList<Tower> towers = new ArrayList<>();
    ArrayList<Upgrade> upgrades = new ArrayList<>();
    ArrayList<Consumable> consumables = new ArrayList<>();


    public Shop(){
        // initialise all available towers for the game
        this.towers.add(new Factory());
        this.towers.add(new Farm());
        this.towers.add(new WaterTower());
        this.towers.add(new Mine());
        this.towers.add(new Sawmill());
        // initialise available upgrades
        this.upgrades.add(new MoneyUpgrade());
        this.upgrades.add(new SpeedUpgrade());
        this.upgrades.add(new CapacityUpgrade());
        // initialise available consumables
        this.consumables.add(new SlowCartBooster());
        this.consumables.add(new TowerSpeedBooster());
        this.consumables.add(new Shield());
    }

    public ArrayList<Tower> getTowers() {
        return towers;
    }

    public ArrayList<Consumable> getConsumables() {
        return consumables;
    }

    public ArrayList<Upgrade> getUpgrades() {
        return upgrades;
    }

}
