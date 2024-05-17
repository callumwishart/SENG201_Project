package seng201.team0.models;

import java.util.ArrayList;

public class GameRunner{

    GameObserver observer;
    ArrayList<Cart> carts;

    GameRunner(Round round, GameObserver observer){
        this.observer = observer;
        this.carts = round.getCarts();
    }


}
