package seng201.team0.models.resources;

import seng201.team0.models.towers.Tower;

import java.io.Serializable;

public class Resource implements Cloneable{
    private String resourceType;
    private int resourceCoinValue;

    public Resource(String inputResourceType, int inputResourceValue) {
        resourceType = inputResourceType;
        resourceCoinValue = inputResourceValue;
    }

    public String getResourceType() {
        return resourceType;
    }

    public int getResourceCoinValue() {
        return resourceCoinValue;
    }
    public void increaseCoinValue(int amount) {
        resourceCoinValue += amount;
    }

    @Override
    public Resource clone() throws CloneNotSupportedException {
        return (Resource) super.clone();
    }
}
