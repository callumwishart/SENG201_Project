package seng201.team0.services;

import seng201.team0.exceptions.NegativeAdditionException;
import seng201.team0.models.Player;
import seng201.team0.models.PlayerInventory;

/**
 * Player Service Class
 * <p>
 *     Acts as a service for the gui to interact with the player
 * </p>
 */
public class PlayerService {
    /**
     * Player Attribute is used to access the player data
     */
    private final Player player;

    /**
     * Player Service Constructor
     * <p>
     *     Assigns {@code this.player} to {@code player}
     * </p>
     * @param player is the player being passed to the instance
     */
    public PlayerService (Player player) {
        this.player = player;
    }

    /**
     * Get Player Name Method
     * <p>
     *     This method gets the players name using
     *     {@code this.player.getName()}
     * </p>
     * @return the players name
     */
    public String getPlayerName(){
        return this.player.getName();
    }

}
