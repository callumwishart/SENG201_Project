package seng201.team0.models;

import seng201.team0.models.Consumables.Consumable;

import java.util.ArrayList;

public class Round {
    private Difficulties difficulty;
    private static int roundNum;
    private static ArrayList<Cart> carts = new ArrayList<>();
    private PlayerInventory inventory;
    private int difficultyMul;
    private ArrayList<Consumable> consumables = new ArrayList<>();
    boolean play;
    Round(ArrayList<Cart> inputCarts, PlayerInventory tempInventory, int inputDifficulty, ArrayList<Consumable> inputConsumables, int inputRoundNum) {
        carts = inputCarts;
        inventory = tempInventory;
        difficultyMul = inputDifficulty;
        consumables = inputConsumables;
        roundNum = inputRoundNum;
    }

    public Difficulties getDifficulty() {
        return difficulty;
    }
    public static int getRoundNum() {
        return roundNum;
    }

    public static ArrayList<Cart> getCarts() {
        return carts;
    }

    public PlayerInventory getInventory() {
        return inventory;
    }

    public int getDifficultyMul() {
        return difficultyMul;
    }

    public ArrayList<Consumable> getConsumables() {
        return consumables;
    }
}
