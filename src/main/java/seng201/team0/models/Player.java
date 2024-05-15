package seng201.team0.models;

public class Player {
    PlayerInventory inventory;
    String name;
    int points;
    public Player(String inputName, PlayerInventory inputInventory) {
        points = 0;
        name = inputName;
        inventory = inputInventory;
    }

    public PlayerInventory getInventory() {
        return inventory;
    }
    public void increasePoints(int amount) {
        points += amount;
    }

}
