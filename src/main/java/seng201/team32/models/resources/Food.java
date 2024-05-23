package seng201.team32.models.resources;
/**
 * Resource type Goods which is created by the Factory
 */
public class Food extends Resource{
    /**
     * Constructor of the Food class which initialises the name and cost
     */
    public Food() {
        super("Food", 15);
    }
    /**
     * Checks if the resource passed is equal to Food and also checks if the object is null
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
    public Food clone() throws CloneNotSupportedException {
        return (Food) super.clone();
    }
}
