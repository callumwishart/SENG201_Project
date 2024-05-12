package seng201.team0.models;

import java.util.ArrayList;

public class Tower {
    int resourceAmount;
    int reloadSpeed;
    Resource resourceType;
    Boolean isBroken;
    int level;
    ArrayList<Upgrade> upgrades = new ArrayList<>();

    public Tower (Resource inputResourceType, int inputReloadSpeed, int inputLevel) {
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
    public int getUpgradePointLimit () {
        return level * 100;
    }
}
