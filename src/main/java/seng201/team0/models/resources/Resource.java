package seng201.team0.models.resources;

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
    public void increaseCoinValue(int amount) {
        resourceCoinValue += amount;
    }
}
