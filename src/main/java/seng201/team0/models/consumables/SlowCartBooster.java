package seng201.team0.models.consumables;


import seng201.team0.models.gameplay.Cart;
import seng201.team0.models.gameplay.Round;

public class SlowCartBooster extends Consumable {
    int cost = 20;

    public SlowCartBooster(String inputName, String inputDescription, int inputCost) {
        super(inputName, inputDescription, inputCost);
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
