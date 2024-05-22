package seng201.team0.models.gameplay;

import seng201.team0.exceptions.NegativeAdditionException;
import seng201.team0.exceptions.TowerNotFoundException;

/**
 * GameObserver to observe what is happening in the gameRunner and display it in the Play Controller
 */
public interface GameObserver {
    /**
     * The observe function should be implemented such that the observer class
     * collects the state of the carts and towers active during the round
     * run by gameRunner and represent it graphically or use it for
     * testing purposes.
     */
    void observe(GameRunner gameRunner);

    void win(int coins, int points) throws NegativeAdditionException, TowerNotFoundException;

    void lose() throws TowerNotFoundException;
}
