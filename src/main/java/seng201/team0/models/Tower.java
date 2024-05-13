package seng201.team0.models;

import seng201.team0.models.Upgrade.Upgrade;

import java.util.ArrayList;

public class Tower extends Item{
    String inputDescription;
    String inputName;
    double inputCost;
    int resourceAmount;
    int reloadSpeed;
    Resource resourceType;
    Boolean isBroken;
    int level;
    ArrayList<Upgrade> upgrades = new ArrayList<>();

    public Tower (Resource inputResourceType, int inputReloadSpeed, int inputLevel, String inputName, String inputDescription, double inputCost) {
        super(inputName, inputDescription, inputCost);
        resourceAmount = 0;
        isBroken = false;
        resourceType = inputResourceType;
        reloadSpeed = inputReloadSpeed;
        level = inputLevel;
    }

    public int getResourceAmount() {
        return resourceAmount;
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
    }
}
