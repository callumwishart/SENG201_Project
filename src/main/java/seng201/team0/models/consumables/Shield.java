package seng201.team0.models.consumables;

import seng201.team0.models.gameplay.Round;

public class Shield extends Consumable {
    public Shield(String inputName, String inputDescription, int inputCost) {
        super(inputName, inputDescription, inputCost);
    }

    public void apply(Round round) {
        round.setShield(true);
    }

    public boolean remove(Round round) {
        round.setShield(false);
        return true;
    }
}
