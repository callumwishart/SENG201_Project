package seng201.team0.models.consumables;

import seng201.team0.models.Item;
import seng201.team0.models.gameplay.Round;

public abstract class Consumable extends Item {
    public Consumable(String inputName, String inputDescription, int inputCost) {
        super(inputName, inputDescription, inputCost);
    }
    public abstract void apply(Round round);
    public abstract boolean remove(Round round);

}
