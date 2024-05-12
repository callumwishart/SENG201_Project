package seng201.team0.models;

public class Item implements Purchasable {
    String name;
    String description;
    double cost;
    public Item (String inputName, String inputDescription, double inputCost) {
        name = inputName;
        description = inputDescription;
        cost = inputCost;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public void setCost (double inputCost) {
        cost = inputCost;
    }

    @Override
    public double getCost() {
        return cost;
    }
}
