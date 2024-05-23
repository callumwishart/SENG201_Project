package seng201.team32.models.gameplay;

import seng201.team32.exceptions.NegativeAdditionException;
import seng201.team32.exceptions.TowerNotFoundException;

/**
 * GameObserver to observe what is happening in the gameRunner and display it in the Play Controller
 */
public interface GameObserver {
    /**
     *  The observe function should be implemented such that the observer class
     *  collects the state of the carts and towers active during the round
     *  run by gameRunner and represent it graphically or use it for
     *  testing purposes.
     * @param gameRunner the game runner class to observe data from
     */
    void observe(GameRunner gameRunner);

    /**
     * The win function should be implemented such that coins/points are calculated to be added to the
     * player's inventory, and the necessary steps are taken to facilitate the continuation of gameplay
     * @param coins the coins won from the round
     * @param points the points won from the round
     * @throws NegativeAdditionException in the case that the points/coins values become negative and the function body tries to add to the inventory
     * @throws TowerNotFoundException thrown if the tower isn't found when running random event related functions after a game is won
     */
    void win(int coins, int points) throws NegativeAdditionException, TowerNotFoundException;

    /**
     * The lose function should be implemented such that the game loss events are triggered and the game ends with a summary.
     * @throws TowerNotFoundException thrown if the tower isn't found when running random event related functions after a game is won
     */
    void lose() throws TowerNotFoundException;
}
