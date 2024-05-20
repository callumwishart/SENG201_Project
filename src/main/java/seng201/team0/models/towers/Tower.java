package seng201.team0.models.towers;

import seng201.team0.models.Item;
import seng201.team0.models.resources.Resource;
import seng201.team0.models.upgrades.Upgrade;
import seng201.team0.models.gameplay.Round.*;
import java.io.*;
import java.io.Serializable;
import java.util.ArrayList;

import static java.lang.Math.round;

public class Tower extends Item implements Sellable {
    double inputCost;
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

    public Tower (Resource inputResourceType, int inputReloadSpeed, String inputName, String inputDescription, int inputCost, String initialImagePath, String brokenImage) {
        super(inputName, inputDescription, inputCost);
        resourceAmount = 0;
        isBroken = false;
        resourceType = inputResourceType;
        reloadSpeed = inputReloadSpeed;
        fixedImagePath = initialImagePath;
        imagePath = initialImagePath;
        brokenImagePath = brokenImage;
    }

    public int getResourceAmount() {
        return resourceAmount;
    }
    public void increaseResourceAmount(int amount) {
        resourceAmount += amount;
    }

    public Resource getResource() {
        return resourceType;
    }

    public void levelUp (){
        level += 1;
    }

    public ArrayList<Upgrade> getUpgrades() {
        return upgrades;
    }

    public void increaseReloadSpeed(int amount) {
        reloadSpeed += amount;
    }

    public boolean isReloading() {
        return isReloading;
    }

    public void setReloading(boolean bool){
        this.isReloading = bool;
        if (!bool){
            reloadTimeElapsed = 0; // reset reload time
        }
    }

    public int getReloadTimeElapsed() {
        return reloadTimeElapsed;
    }

    public void incrementReloadTimeElapsed(){
        reloadTimeElapsed += 1;
    }

    public int getUpgradePointLimit() {
        return level * 100;
    }

    public void setToBroken () {
        isBroken = true;
        imagePath = brokenImagePath;
    }
    public void setToFixed() {
        isBroken = false;
        imagePath = fixedImagePath;
    }
    public int getUsed() {
        return used;
    }
    public int getRepairCost() {
        return (int) (this.getCost() / 2);
    }
    public Boolean isBroken() {
        return isBroken;
    }

    public int getReloadSpeed() {
        return reloadSpeed;
    }

    public String getImagePath() {
        return imagePath;
    }
    public int getLevel() {
        return level;
    }

    public int getSellCost() {
        if (!this.isBroken()) {
            return (int) (this.getCost() / 2);
        } else {
            return 0;
        }
    }
}
