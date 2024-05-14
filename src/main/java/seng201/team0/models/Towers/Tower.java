package seng201.team0.models.Towers;

import seng201.team0.models.Item;
import seng201.team0.models.Resources.Resource;
import seng201.team0.models.Upgrades.Upgrade;

import java.util.ArrayList;

public class Tower extends Item {
    double inputCost;
    int resourceAmount;
    int reloadSpeed;
    Resource resourceType;
    Boolean isBroken;
    int level;
    String imagePath;
    String brokenImagePath;
    int used;
    ArrayList<Upgrade> upgrades = new ArrayList<>();

    public Tower (Resource inputResourceType, int inputReloadSpeed, String inputName, String inputDescription, double inputCost, String initialImagePath, String brokenImage) {
        super(inputName, inputDescription, inputCost);
        resourceAmount = 0;
        isBroken = false;
        resourceType = inputResourceType;
        reloadSpeed = inputReloadSpeed;
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
    public int getUpgradePointLimit () {
        return level * 100;
    }
    public void setToBroken () {
        isBroken = true;
        imagePath = brokenImagePath;
    }
}
