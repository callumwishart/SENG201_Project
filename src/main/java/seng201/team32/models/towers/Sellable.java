package seng201.team32.models.towers;

/**
 * Sellable interface for towers to use so that the sell cost can be calculated
 */
public interface Sellable {
    /**
     * Gets the sell cost of the tower
     * @return the sell cost of towers as an integer
     */
    int getSellCost();
}
