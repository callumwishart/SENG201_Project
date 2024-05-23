package seng201.team0.models;

/**
 * Player Class for player details
 */
public class Player {
    /**
     * PlayerInventory attribute to hold the inventory
     */
    private final PlayerInventory inventory;
    /**
     * String to hold the players name
     */
    private String name;

    /**
     * Constructor to create a new player with a new PlayerInventory
     */
    public Player() {
        this.inventory = new PlayerInventory();
    }

    /**
     * Get Player Inventory Method
     * @return the players inventory
     */
    public PlayerInventory getInventory() {
        return inventory;
    }

    /**
     * Sets the Player's name
     * @param name is the String that the players name will be set to
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the players name
     * @return the players name
     */
    public String getName() {
        return this.name;
    }

}