package seng201.team0.models.consumables;

import seng201.team0.models.gameplay.Round;

public class Shield extends Consumable {
    public Shield() {
        super("Shield", "Protects you from one cart finishing without being filled", 500);
    }

    public void apply(Round round) {
        round.setShield(true);
    }

    public boolean remove(Round round) {
        round.setShield(false);
        return true;
    }
}
