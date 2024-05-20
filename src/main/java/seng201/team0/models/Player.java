package seng201.team0.models;

import seng201.team0.exceptions.NameCharException;

public class Player {

    private PlayerInventory inventory;
    private String name;

    public Player() {
        this.inventory = new PlayerInventory();
    }

    public PlayerInventory getInventory() {
        return inventory;
    }

    public void setName(String name) throws NameCharException {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}