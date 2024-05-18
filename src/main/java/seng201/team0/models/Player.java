package seng201.team0.models;

public class Player {
    private PlayerInventory inventory;
    private String name;
    int points;
    public Player() {
        points = 0;
        this.inventory = new PlayerInventory();
    }

    public PlayerInventory getInventory() {
        return inventory;
    }
    public void increasePoints(int amount) {
        points += amount;
    }
    public void setName(String name) {
        this.name = name;
    }

}
