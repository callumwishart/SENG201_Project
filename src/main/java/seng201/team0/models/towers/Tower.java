package seng201.team0.models.towers;

import seng201.team0.models.Item;
import seng201.team0.models.resources.Resource;
import seng201.team0.models.upgrades.Upgrade;

import java.util.ArrayList;

/**
 * Tower Class
 * <p>
 *     Houses all of the methods of tower
 * </p>
 */
public class Tower extends Item implements Sellable {
    /**
     * Integer to hold the amount of resources
     */
    int resourceAmount;
    /**
     * Integer to hold the reload speed of the towers
     */
    int reloadSpeed;
    /**
     * boolean that determines if the tower is reloading or not
     */
    boolean isReloading;
    /**
     * integer to store how long the tower has been reloading for
     */
    int reloadTimeElapsed;
    /**
     * Resource to store the towers resource type
     */
    Resource resourceType;
    /**
     * boolean that store if the tower is broken or not
     */
    boolean isBroken;
    /**
     * integer that holds the level of the tower
     */
    int level;
    /**
     * String which holds the current image path of the tower
     */
    String imagePath;
    /**
     * String which holds the working image path of the tower
     */
    String fixedImagePath;
    /**
     * String which holds the broken image path of the tower
     */
    String brokenImagePath;
    /**
     * integer which holds how many times the tower has been used
     */
    int used;
    /**
     * List of the towers upgrades that have been applied
     */
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
     * Gets the amount of resource that can be stored
     * @return an integer of how much of the resource the tower can hold
     */
    public int getResourceAmount() {
        return resourceAmount;
    }

    /**
     * Increase the amount of resources that this tower can hold
     * @param amount is the amount that resourceAmount will be increase by
     */
    public void increaseResourceAmount(int amount) {
        resourceAmount += amount;
    }

    /**
     * Gets the resource that the tower uses
     * @return the type of the resource that the tower generates in the form of a {@code Resource}
     */
    public Resource getResource() {
        return resourceType;
    }

    /**
     * Levels up the tower by one
     */
    public void levelUp (){
        level += 1;
    }

    /**
     * Gets the current upgrades that the tower has in place
     * @return an ArrayList of upgrades that the tower has
     */
    public ArrayList<Upgrade> getUpgrades() {
        return upgrades;
    }

    /**
     * Increases the reload speed by one
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
     * Gets if the tower is reloading or not
     * @return boolean that tells us if the tower is reloading or not
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
     * Gets how long the tower has been reloading for
     * @return an integer of how long the tower has been reloading for
     */
    public int getReloadTimeElapsed() {
        return reloadTimeElapsed;
    }

    /**
     * Increments the reload time by one
     */
    public void incrementReloadTimeElapsed(){
        reloadTimeElapsed += 1;
    }

    /**
     * Gets the upgrade point limit
     * <p>
     *     It calculates the by multiplying {@code this.level} by 400
     * </p>
     * @return an integer of the upgradePointLimit
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
     * Gets an integer of how many times the tower has been used
     * @return integer of how many times the tower has been used
     */
    public int getUsed() {
        return used;
    }

    /**
     * @hidden
     */
    public int getRepairCost() {
        return this.getCost() / 2;
    }

    /**
     * Gets the status of the tower and returns true if broken
     * @return boolean of status of the tower
     */
    public Boolean isBroken() {
        return isBroken;
    }

    /**
     * Gets the reload speed
     * @return an integer of the towers reload speed
     */
    public int getReloadSpeed() {
        return reloadSpeed;
    }

    /**
     * Gets the current image path of the tower
     * @return a String representing the current image path of the tower
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Gets the level of the tower
     * @return an integer representing the level of the tower
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
            return this.getCost() / 2;
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
     * Increments how many times the tower has been used by one (1)
     */
    public void incrementUsed() {
        this.used += 1;
    }

    /**
     * Resets the used value to 0
     */
    public void resetUsed(){this.used = 0;}
}
