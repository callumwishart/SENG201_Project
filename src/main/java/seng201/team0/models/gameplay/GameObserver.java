package seng201.team0.models.gameplay;

import seng201.team0.exceptions.NegativeAdditionException;

public interface GameObserver {
    /**
     * The observe function should be implemented such that the observer class
     * collects the state of the carts and towers active during the round
     * run by gameRunner and represent it graphically or use it for
     * testing purposes.
     */
    public void observe(GameRunner gameRunner);

    void win(int coins, int points) throws NegativeAdditionException;

    void lose();
}
