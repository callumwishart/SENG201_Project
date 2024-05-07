package seng201.team0.models;

import java.util.ArrayList;

public class Round {
    private Difficulties difficulty;
    private ArrayList<Cart> carts = new ArrayList<>();
    private PlayerInventory inventory;
    private int difficultyMul;
    private ArrayList<Booster> boosts = new ArrayList<>();
    boolean play;
    Round(ArrayList<Cart> inputCarts, PlayerInventory tempInventory, int inputDifficulty, ArrayList<Booster> inputBoosters) {
        carts = inputCarts;
        inventory = tempInventory;
        difficultyMul = inputDifficulty;
        boosts = inputBoosters;
    }

    public Difficulties getDifficulty() {
        return difficulty;
    }

    public ArrayList<Cart> getCarts() {
        return carts;
    }

    public PlayerInventory getInventory() {
        return inventory;
    }

    public int getDifficultyMul() {
        return difficultyMul;
    }

    public ArrayList<Booster> getBoosts() {
        return boosts;
    }
}
