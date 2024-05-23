package seng201.team0.models.consumables;


import seng201.team0.models.gameplay.Cart;
import seng201.team0.models.gameplay.Round;

public class SlowCartConsumable extends Consumable {
    int cost = 20;

    public SlowCartConsumable() {
        super("Slow Carts", "Slows all carts by 1 for 1 round", 350);
    }

    public void apply(Round round) {
        for (Cart cart : round.getCarts()) {
            if (cart.getSpeed() > 1){
                cart.changeSpeed(-1);
            }
        }
    }

    public boolean remove(Round round) {
        return true;
    }
}
