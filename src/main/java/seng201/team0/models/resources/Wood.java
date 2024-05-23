package seng201.team0.models.resources;

/**
 * Resource type wood which is made by the Sawmill tower
 */
public class Wood extends Resource{
    /**
     * Constructor of the wood class which initialises the name and cost
     */
    public Wood() {
        super("Wood", 20);
    }
    /**
     * Checks if the resource passed is equal to Wood and also checks if the object is null
     * @param obj the object that is trying to be checked
     * @return boolean of if the object equals
     */
    @Override
    public boolean equals(Object obj) {
        // Check if the obj is null
        if (obj == null) {
            return false;
        }
        // Check if both objects are of the same class
        return this.getClass() == obj.getClass();
    }
    /**
     * Clones the current resource by creating a copy of it
     * @return the copy of this resource
     * @throws CloneNotSupportedException if it cannot be cloned
     */
    @Override
    public Wood clone() throws CloneNotSupportedException {
        return (Wood) super.clone();
    }
}
