package seng201.team0.models.Consumables;


import seng201.team0.models.gameplay.Cart;
import static seng201.team0.models.gameplay.Round.getCarts;

public class SlowCartBooster extends Consumable {
    int cost = 20;

    public SlowCartBooster(String inputName, String inputDescription, int inputCost) {
        super(inputName, inputDescription, inputCost);
    }

    public void apply() {
        for (Cart cart : getCarts()) {
            cart.changeSpeed(2);
        }
    }
    public void remove() {
        for (Cart cart : getCarts()) {
            cart.changeSpeed(-2);
        }
    }
}
