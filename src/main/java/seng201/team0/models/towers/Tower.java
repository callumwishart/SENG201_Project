package seng201.team0.models.towers;

import seng201.team0.models.Item;
import seng201.team0.models.resources.Resource;
import seng201.team0.models.upgrades.Upgrade;
import seng201.team0.models.gameplay.Round.*;
import java.io.*;
import java.io.Serializable;
import java.util.ArrayList;

import static java.lang.Math.round;

/**
 * Tower Class
 * <p>
 *     Houses all of the methods of tower
 * </p>
 */
public class Tower extends Item implements Sellable {
    int resourceAmount;
    int reloadSpeed;
    boolean isReloading;
    int reloadTimeElapsed;
    Resource resourceType;
    Boolean isBroken;
    int level;
    String imagePath;
    String fixedImagePath;
    String brokenImagePath;
    int used;
    ArrayList<Upgrade> upgrades = new ArrayList<>();

    /**
     * Initialises the tower and sets the resourceAmount to 1;
     */
    public Tower (Resource inputResourceType, int inputReloadSpeed, String inputName, String inputDescription, int inputCost, String initialImagePath, String brokenImage) {
        super(inputName, inputDescription, inputCost);
        resourceAmount = 1;
        isBroken = false;
        resourceType = inputResourceType;
        reloadSpeed = inputReloadSpeed;
        fixedImagePath = initialImagePath;
        imagePath = initialImagePath;
        brokenImagePath = brokenImage;
    }

    /**
     * @hidden
     */
    public int getResourceAmount() {
        return resourceAmount;
    }

    /**
     * @hidden
     */
    public void increaseResourceAmount(int amount) {
        resourceAmount += amount;
    }

    /**
     * @hidden
     */
    public Resource getResource() {
        return resourceType;
    }

    /**
     * Increases the tower level by 1
     */
    public void levelUp (){
        level += 1;
    }

    /**
     * @hidden
     */
    public ArrayList<Upgrade> getUpgrades() {
        return upgrades;
    }

    /**
     * Increases the reload speed by 1
     */
    public void increaseReloadSpeed() {
        reloadSpeed += 1;
    }

    /**
     * Decreases the reload speed by 1
     */
    public void decreaseReloadSpeed() {
        reloadSpeed -= 1;
    }

    /**
     * @hidden
     */
    public boolean isReloading() {
        return isReloading;
    }

    /** Sets the tower to be in a reloading state
     * <p>
     *     If the input is false it will reset the reload time
     * </p>
     * @param bool value of what the tower isReloading should be set to
     */
    public void setReloading(boolean bool){
        this.isReloading = bool;
        if (!bool){
            reloadTimeElapsed = 0; // reset reload time
        }
    }

    /**
     * @hidden
     */
    public int getReloadTimeElapsed() {
        return reloadTimeElapsed;
    }

    /**
     * @hidden
     */
    public void incrementReloadTimeElapsed(){
        reloadTimeElapsed += 1;
    }

    /**
     * The upgrade point limit is determined by the level of the tower multiplied by 200
     */
    public int getUpgradePointLimit() {
        return level * 400;
    }

    /**
     * Sets the tower to broken and updates imagePath
     */
    public void setToBroken () {
        isBroken = true;
        imagePath = brokenImagePath;
    }

    /**
     * Sets the tower to fixed and updates the image path
     */
    public void setToFixed() {
        isBroken = false;
        imagePath = fixedImagePath;
    }

    /**
     * @hidden
     */
    public int getUsed() {
        return used;
    }

    /**
     * @hidden
     */
    public int getRepairCost() {
        return (int) (this.getCost() / 2);
    }

    /**
     * @hidden
     */
    public Boolean isBroken() {
        return isBroken;
    }

    /**
     * @hidden
     */
    public int getReloadSpeed() {
        return reloadSpeed;
    }

    /**
     * @hidden
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * @hidden
     */
    public int getLevel() {
        return level;
    }

    /**
     * Gets the Sell cost of the tower
     * <p>
     *     If the tower is broken then the sell cost is 0 otherwise it is half of the cost of it
     * </p>
     */
    public int getSellCost() {
        if (!this.isBroken()) {
            return (int) (this.getCost() / 2);
        } else {
            return 0;
        }
    }

    /**
     * Add upgrade to tower
     * <p>
     *     Adds the upgrade to the tower and also levels up the tower.
     * </p>
     */
    public void addUpgrade(Upgrade upgrade){
        this.upgrades.add(upgrade);
        this.levelUp();
    }

    /**
     * @hidden
     */
    public void incrementUsed() {
        this.used += 1;
    }
}
