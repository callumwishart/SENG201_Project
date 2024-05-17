package seng201.team0.services;

import seng201.team0.exceptions.NegativeAdditionException;
import seng201.team0.models.Player;
import seng201.team0.models.PlayerInventory;

public class PlayerService {
    Player player;
    public PlayerService (Player player) {
        this.player = player;
    }
    public void addCoins(int amount) throws NegativeAdditionException {
        player.getInventory().addCoins(amount);
    }
    public void addPoints(int amount) {
        player.increasePoints(amount);
    }
    public void removeCoins(int amount) throws Exception {
        player.getInventory().useCoins(amount);
    }
}
