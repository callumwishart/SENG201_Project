package seng201.team32.models.resources;

/**
 * Resource class which is used to hold data of the resource
 */
public class Resource implements Cloneable{
    /**
     * String to hold the resourceType
     */
    private String resourceType;
    /**
     * Integer to hold the value of the resource
     */
    private int resourceCoinValue;

    /**
     * Constructor of resource
     * @param inputResourceType is the name of the resource as a String
     * @param inputResourceValue is the value of the resource as an int
     */
    public Resource(String inputResourceType, int inputResourceValue) {
        resourceType = inputResourceType;
        resourceCoinValue = inputResourceValue;
    }

    /**
     * Gets the resource type
     * @return a String representing the type of the resource
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * Gets the value of the resource
     * @return an integer of the resources value
     */
    public int getResourceCoinValue() {
        return resourceCoinValue;
    }

    /**
     * Increases the value of the resource
     * @param amount the amount that will be added to the value of the resource
     */
    public void increaseCoinValue(int amount) {
        resourceCoinValue += amount;
    }
    /**
     * Clones the resource
     * @return The new resource object
     * @throws CloneNotSupportedException is thrown if the resource cannot be cloned
     */
    @Override
    public Resource clone() throws CloneNotSupportedException {
        return (Resource) super.clone();
    }
}
