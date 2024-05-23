package seng201.team32.models.resources;

/**
 * Resource type Water which is created by the Water Tower
 */
public class Water extends Resource{
    /**
     * Constructor of the water class which initialises the name and cost
     */
    public Water() {
        super("Water", 5);
    }
    /**
     * Checks if the resource passed is equal to Water and also checks if the object is null
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
    public Water clone() throws CloneNotSupportedException {
        return (Water) super.clone();
    }

}
