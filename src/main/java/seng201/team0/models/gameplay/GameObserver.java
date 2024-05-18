package seng201.team0.models.gameplay;

public interface GameObserver {
    /**
     * @author Theo Parker
     * the observe function should be implemented such that the observer class collects the state
     * of the carts and towers active during the round run by gameRunner and represent it graphically
     * or use it for testing purposes.
     */
    public void observe(GameRunner gameRunner);
}
