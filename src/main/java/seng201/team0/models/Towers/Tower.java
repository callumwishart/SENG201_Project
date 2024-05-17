package seng201.team0.models.Towers;

import seng201.team0.models.Item;
import seng201.team0.models.Resources.Resource;
import seng201.team0.models.Upgrades.Upgrade;

import java.util.ArrayList;

import static java.lang.Math.round;
import static seng201.team0.models.Round.getRoundNum;

public class Tower extends Item implements Sellable{
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

    public Resource getResourceType() {
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

    public int getUpgradePointLimit () {
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
        return (int) round(getCost() * 0.5 * getRoundNum());
    }
    public Boolean getStatus() {
        return isBroken;
    }
}
