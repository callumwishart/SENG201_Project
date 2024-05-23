package seng201.team32.models.consumables;

import seng201.team32.models.Item;
import seng201.team32.models.gameplay.Round;

/**
 * Consumable class for temporary booster that will apply for one round only
 */
public abstract class Consumable extends Item {
    /**
     * Constructor of the consumable which sets the name, description and cost of the consumable
     * @param inputName name of the consumable
     * @param inputDescription description of the consumable
     * @param inputCost cost of the consumable
     */
    public Consumable(String inputName, String inputDescription, int inputCost) {
        super(inputName, inputDescription, inputCost);
    }

    /**
     * Method to apply the consumable
     * @param round the round that this consumable will be applied to
     */
    public abstract void apply(Round round);

    /**
     * Method to remove the consumable after the round
     * @param round the round this will be applied to
     * @return true to show that the consumable has been taken off
     */
    public abstract boolean remove(Round round);

}
