package seng201.team0.models;

/**
 * This class is for items that are purchasable and allows the to be easily interacted with
 */
public class Item implements Purchasable {
    /**
     * Name of item as a string
     */
    String name;
    /**
     * Description of item as a string
     */
    String description;
    /**
     * Cost of item as Integer
     */
    int cost;

    /**
     * Constructor of item to assign name, description and cost
     * @param inputName is the name of the item as a String
     * @param inputDescription is the description of the item as a String
     * @param inputCost  is the cost of the item as an integer
     */
    public Item (String inputName, String inputDescription, int inputCost) {
        name = inputName;
        description = inputDescription;
        cost = inputCost;
    }

    /**
     * Gets the name of the item
     * @return the name of the item as a String
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of the item
     * @return the description of the item as a String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the cost of items
     * @param inputCost is the cost the item will be set to
     */
    public void setCost (int inputCost) {
        cost = inputCost;
    }

    /**
     * Gets the cost of the items
     * @return the cost of the items as an integer
     */
    @Override
    public int getCost() {
        return cost;
    }
}
