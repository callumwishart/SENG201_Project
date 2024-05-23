package seng201.team32.models;

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

    /**
     * Checks if the supplied name meets the required standard of between 3-15 chars and no special symbols.
     * @param name the name to be checked
     * @return true if name meets the standard
     */
    public boolean checkName(String name) {
        return (name.length() >= 3 && name.length() <= 15 && name.matches("[a-zA-Z0-9]+"));
    }
}