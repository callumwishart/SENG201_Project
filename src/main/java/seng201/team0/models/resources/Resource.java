package seng201.team0.models.resources;

import java.io.Serializable;

public class Resource {
    private String resourceType;
    private int resourceCoinValue;

    public Resource(String inputResourceType, int inputResourceValue) {
        resourceType = inputResourceType;
        resourceCoinValue = inputResourceValue;
    }

    /**
     * A copy constructor to make copies of a Resource
     */
    public Resource(Resource other){
        this.resourceType = other.resourceType;
        this.resourceCoinValue = other.resourceCoinValue;
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
}
