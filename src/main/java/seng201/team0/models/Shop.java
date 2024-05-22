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

/**
 * Shop class to handle everything in the shop
 */
public class Shop {
    /**
     * ArrayList to store towers in shop
     */
    ArrayList<Tower> towers = new ArrayList<>();
    /**
     * ArrayList to store upgrades in shop
     */
    ArrayList<Upgrade> upgrades = new ArrayList<>();
    /**
     * ArrayList to store consumables in shop
     */
    ArrayList<Consumable> consumables = new ArrayList<>();

    /**
     * Constructor of shop to create all the available items
     */
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

    /**
     * Get Towers Method
     * <p>
     *     This method returns the towers
     * </p>
     * @return towers
     */
    public ArrayList<Tower> getTowers() {
        return towers;
    }

    /**
     * Get Consumables Method
     * <p>
     *     This method returns the consumables
     * </p>
     * @return consumables ArrayList
     */
    public ArrayList<Consumable> getConsumables() {
        return consumables;
    }

    /**
     * Get Upgrade Method
     * <p>
     *     This method return the upgrades
     * </p>
     * @return upgrades ArrayList
     */
    public ArrayList<Upgrade> getUpgrades() {
        return upgrades;
    }

}
