package seng201.team0.models;

public class Consumable extends Item{
    public Consumable(String inputName, String inputDescription, double inputCost) {
        super(inputName, inputDescription, inputCost);
    }
    public void apply() {//Yet to implement
    }
    public void remove() {
        //Yet to be implemented
    }
    public double getCost() {
        return cost * Round.getRoundNum();
    }
}
