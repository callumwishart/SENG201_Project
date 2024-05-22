package seng201.team0.models.gameplay;

import seng201.team0.models.Difficulty;
import seng201.team0.models.consumables.Consumable;
import seng201.team0.models.PlayerInventory;
import seng201.team0.models.resources.Resource;
import seng201.team0.models.towers.Tower;
import seng201.team0.services.InventoryService;
import seng201.team0.utils.Utilities;

import java.util.*;

/**
 * The Round class calculates the number of carts, the length of the track, applies the consumables that the
 * player has purchased for the round, and then consumes them from the inventory once the round is over
 * (triggered by {@link #cleanup() cleanup})
 */
public class Round {
    private ArrayList<Tower> towers;
    private int roundNum;
    private ArrayList<Cart> carts = new ArrayList<>();
    private InventoryService inventoryService;
    private Difficulty difficulty;
    private ArrayList<Consumable> consumables = new ArrayList<>();
    private boolean hasShield;
    private int trackLength;

    /**
     * Constructs a round based on difficulty, what round number, and what towers are equipped by player
     */
    public Round(InventoryService inventoryService, Difficulty difficulty, int roundNum) throws CloneNotSupportedException {
        this.inventoryService = inventoryService;
        this.towers = (ArrayList<Tower>) this.inventoryService.getActiveTowers();
        this.difficulty = difficulty;
        this.roundNum = roundNum;
        this.trackLength = this.calculateTrackLength();
        int cartNum = calculateCartNum();
        this.createCarts(cartNum);
        this.carts.sort(Comparator.comparingInt(Cart::getSpeed));
        Collections.reverse(this.carts); // sort descending so towers select the fastest to fill first
    }

    public void applyConsumables() {
        for (Consumable consumable : this.inventoryService.getConsumables()){
            consumable.apply(this);
        }
    }


    /**
     * Calculates the number of carts.
     * based on the quadratic function: y = 0.4*x^(1.43) + 1
     * y (number of carts) is then multiplied by the roundDifficultyMultiplier to increase/decrease based on difficulty.
     * type casting to (int) truncates the decimal to return a whole number.
     */
    public int calculateCartNum(){
        int roundNum = this.roundNum;
        double function = 0.4 * Math.pow(roundNum, 1.43) + 1;
        return (int) (function * this.difficulty.roundDifficultyMultiplier());
    }

    public void createCarts(int amount) throws CloneNotSupportedException {
        for (int i = 0; i < amount; i++){
            // check to see what number, decide on universal or not, pick a random resource from possibleResources
            if ((i < 1) || Utilities.weightedCoinToss(0.4)){
                this.carts.add(new Cart(this.getRandCartSize(), this.getRandCartSpeed())); // create a universal cart if there is only 1
            }
            else {
                this.carts.add(
                        new Cart(
                                this.getRandCartSize(),
                                this.getRandCartSpeed(),
                                this.getRandCartResource().clone()
                        )
                );
            }
        }
    }

    private Resource getRandCartResource() {
        // get types of towers
        ArrayList<Resource> possibleResources = new ArrayList<>();
        for (Tower tower : this.towers){
            possibleResources.add(tower.getResource());
        }
        // pick random resource
        int bound = possibleResources.size();
        Random random = new Random();
        int randomResourceIndex = random.nextInt(bound);
        return possibleResources.get(randomResourceIndex);
    }

    /**
     * Returns a random speed int between a range calculated from roundNum
     */
    public int getRandCartSpeed(){
        Random random = new Random();
        int maxSpeed = 2;
        int minSpeed = 1;
        int maxRoundBound = 8;

        /**
         * Generates a random int between minSpeed & maxSpeed,
         * the max speed bracket increases as the round number increases, but the min speed bracket
         * increases half as much.
         */
        if (this.roundNum > maxRoundBound){
            int bound = (maxSpeed + maxRoundBound) - (minSpeed + maxRoundBound);
            int result = random.nextInt((maxSpeed - minSpeed) + 1) + (minSpeed + (int)(maxRoundBound / 2));
            return (int) (result * this.difficulty.roundDifficultyMultiplier());
        }
        else {
            int bound = (maxSpeed + this.roundNum) - (minSpeed + this.roundNum);
            int result = random.nextInt((maxSpeed - minSpeed) + 1) + (minSpeed + (int)(this.roundNum / 2));
            return (int) (result * this.difficulty.roundDifficultyMultiplier());
        }
    }

    /**
     * Returns a random size int between a range calculated from roundNum
     * Generates a random int between minSize & maxSize,
     * the max size bracket increases as the round number increases,
     * but the min size bracket increases half as much.
     */
    public int getRandCartSize(){
        Random random = new Random();
        int maxSize = 15;
        int minSize = 5;
        int maxRoundBound = 8;

        if (this.roundNum > maxRoundBound){
            int bound = (maxSize + maxRoundBound) - (minSize + maxRoundBound);
            int result = random.nextInt((maxSize - minSize) + 1) + (minSize + (int)(maxRoundBound / 2));
            return (int) (result * this.difficulty.roundDifficultyMultiplier());
        }
        else {
            int bound = (maxSize + this.roundNum) - (minSize + this.roundNum);
            int result = random.nextInt((maxSize - minSize) + 1) + (minSize + (int)(this.roundNum / 2));
            return (int) (result * this.difficulty.roundDifficultyMultiplier());
        }
    }

    public int calculateTrackLength(){
        int defaultLength = 50;
        int growthRate = 10;
        int variationBound = 15;
        Random random = new Random();
        int variation = random.nextInt(variationBound);
        return defaultLength + (int)((this.roundNum / 2)*growthRate) + variation;
    }

    public void cleanup() {
        this.removeConsumables();
        this.difficulty.reset();
    }

    private void removeConsumables() {
        for (Consumable consumable : this.inventoryService.getConsumables()){
            consumable.remove(this);
        }
        this.inventoryService.removeConsumables();
    }

    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    public boolean hasShield(){
        return this.hasShield;
    }

    public int getRoundNum() {
        return this.roundNum;
    }

    public ArrayList<Cart> getCarts() {
        return this.carts;
    }

    public ArrayList<Consumable> getConsumables() {
        return this.consumables;
    }

    public void setShield(boolean b) {
        this.hasShield = b;
    }

    public ArrayList<Tower> getTowers() {
        return this.inventoryService.getActiveTowers();
    }

    public int getTrackLength() {
        return this.trackLength;
    }
}
