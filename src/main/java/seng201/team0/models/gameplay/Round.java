package seng201.team0.models.gameplay;

import seng201.team0.models.Difficulty;
import seng201.team0.models.consumables.Consumable;
import seng201.team0.models.PlayerInventory;
import seng201.team0.models.towers.Tower;

import java.util.ArrayList;

public class Round {
    public int trackDistance;
    public ArrayList<Tower> towers;
    private Difficulty difficulty;
    private static int roundNum;
    private static ArrayList<Cart> carts = new ArrayList<>();
    private PlayerInventory inventory;
    private int difficultyMul;
    private ArrayList<Consumable> consumables = new ArrayList<>();

    Round(ArrayList<Cart> inputCarts, PlayerInventory tempInventory, int inputDifficulty, ArrayList<Consumable> inputConsumables, int inputRoundNum) {
        carts = inputCarts;
        inventory = tempInventory;
        difficultyMul = inputDifficulty;
        consumables = inputConsumables;
        roundNum = inputRoundNum;
    }

    public Difficulty getDifficulty() {
        return this.difficulty;
    }
    public static int getRoundNum() {
        return roundNum;
    }

    public static ArrayList<Cart> getCarts() {
        return carts;
    }

    public PlayerInventory getInventory() {
        return this.inventory;
    }

    public int getDifficultyMul() {
        return this.difficultyMul;
    }

    public ArrayList<Consumable> getConsumables() {
        return this.consumables;
    }
}
