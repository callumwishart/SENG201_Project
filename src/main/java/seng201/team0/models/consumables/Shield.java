package seng201.team0.models.consumables;

import seng201.team0.models.gameplay.Round;

/**
 * Shield consumable which makes you invincible for that round
 */
public class Shield extends Consumable {
    /**
     * Constructor of the shield which sets the name and description and cost
     */
    public Shield() {
        super("Shield", "Makes you invincible for the next round.", 750);
    }

    /**
     * Apply the shield function
     * <p>
     *     Sets the {@code shield} to true
     * </p>
     * @param round is the round that this should be applied to
     */
    public void apply(Round round) {
        round.setShield(true);
    }

    /**
     * Removes the shield function
     * <p>
     *     This sets the shield to false
     * </p>
     * @param round the round that this will be applied to
     * @return true to show that it has taken place
     */
    public boolean remove(Round round) {
        round.setShield(false);
        return true;
    }
}
