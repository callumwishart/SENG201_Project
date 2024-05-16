package seng201.team0.services;

import seng201.team0.models.Player;
import seng201.team0.models.PlayerInventory;

public class PlayerService {
    Player player;
    public PlayerService (String inputName, PlayerInventory inputInventory) {
        Player player = new Player(inputName, inputInventory);
    }
    public void addCoins(int amount) {
        player.getInventory().addCoins(amount);
    }
    public void addPoints(int amount) {
        player.increasePoints(amount);
    }
    public void removeCoins(int amount) throws Exception {
        player.getInventory().useCoins(amount);
    }
}
