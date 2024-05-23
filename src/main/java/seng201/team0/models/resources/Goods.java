package seng201.team0.models.resources;
/**
 * Resource type Goods which is created by the Factory
 */
public class Goods extends Resource{
    /**
     * Constructor of the goods class which initialises the name and cost
     */
    public Goods() {
        super("Goods", 50);
    }
    /**
     * Checks if the resource passed is equal to Goods and also checks if the object is null
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
    public Goods clone() throws CloneNotSupportedException {
        return (Goods) super.clone();
    }

}
