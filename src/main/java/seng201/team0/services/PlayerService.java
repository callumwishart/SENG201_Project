package seng201.team0.services;

import seng201.team0.exceptions.NegativeAdditionException;
import seng201.team0.models.Player;
import seng201.team0.models.PlayerInventory;

public class PlayerService {
    private Player player;

    public PlayerService (Player player) {
        this.player = player;
    }

    public String getPlayerName(){
        return this.player.getName();
    }

}
