package seng201.team0.models.Consumables;

import seng201.team0.models.Item;

public abstract class Booster extends Item {
    public Booster(String inputName, String inputDescription, int inputCost) {
        super(inputName, inputDescription, inputCost);
    }
    public abstract void apply();
    public abstract void remove();

}
