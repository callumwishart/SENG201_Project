package seng201.team32.models.consumables;


import seng201.team32.models.gameplay.Cart;
import seng201.team32.models.gameplay.Round;

/**
 * Slow cart consumable which will make all the carts slower for that round thus making it easier for them to be filled
 * before they reach the end of the track.
 */
public class SlowCartConsumable extends Consumable {
    /**
     * Constructor which sets the name and description and cost of the consumable
     */
    public SlowCartConsumable() {
        super("Slow Carts", "Slows all carts by 1 for one round.", 350);
    }

    /**
     * This will apply the effect of the consumable to the carts
     * <p>
     *     This will iterate through all the carts in the round and remove one (1) from the cart speed if
     *     the cart speed is greater than one (1)
     * </p>
     * @param round round that this is applied to
     */
    public void apply(Round round) {
        for (Cart cart : round.getCarts()) {
            if (cart.getSpeed() > 1){
                cart.changeSpeed(-1);
            }
        }
    }

    /**
     * Remove function does not do anything as carts do not need to be reset
     * @param round round that this is applied to
     * @return boolean if the method has happened
     */
    public boolean remove(Round round) {
        return true;
    }
}
