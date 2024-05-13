package seng201.team0.models.Resources;

public class Resource {
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
}
