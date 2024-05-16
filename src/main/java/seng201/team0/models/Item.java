package seng201.team0.models;

public class Item implements Purchasable {
    String name;
    String description;
    int cost;
    public Item (String inputName, String inputDescription, int inputCost) {
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
    public void setCost (int inputCost) {
        cost = inputCost;
    }

    @Override
    public int getCost() {
        return cost;
    }
}
