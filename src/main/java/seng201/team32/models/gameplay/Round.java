package seng201.team32.models.gameplay;

import seng201.team32.models.Difficulty;
import seng201.team32.models.consumables.Consumable;
import seng201.team32.models.resources.Resource;
import seng201.team32.models.towers.Tower;
import seng201.team32.services.InventoryService;
import seng201.team32.utils.Utilities;

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
     * based on round number - linear relationship
     */
    public int calculateCartNum(){
        return this.roundNum;
    }

    public void createCarts(int amount) throws CloneNotSupportedException {
        for (int i = 0; i < amount; i++){
            // check to see what number, decide on universal or not, pick a random resource from possibleResources
            if ((i < 1) || Utilities.weightedCoinToss(0.4)){
                this.carts.add(
                        new Cart(
                                this.getRandCartSize(true),
                                this.getRandCartSpeed()
                        ) // create a universal cart if there is only 1
                );
            }
            else {
                this.carts.add(
                        new Cart(
                                this.getRandCartSize(false),
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

        if (this.roundNum >= 1 && this.roundNum <= 5){
            maxSpeed += 1;
        } else if (this.roundNum >= 5 && this.roundNum <= 10){
            maxSpeed += 2;
        } else if (this.roundNum >= 10 && this.roundNum <= 15){
            maxSpeed += 3;
        }

        if (this.roundNum >= 3){
            return random.nextInt(maxSpeed) + 2; // raise the min speed to 2 after round 3
        }
        else {
            return random.nextInt(maxSpeed) + 1;
        }

    }

    /**
     *
     */
    public int getRandCartSize(boolean universal){
        Random random = new Random();
        int universalMax = 10;
        int universalMin = 5;
        int regularMax = 3;
        int regularMin = 2;

        if (universal){
            int result = (int)(((random.nextInt(universalMax)) + universalMin) * this.difficulty.roundDifficultyMultiplier());
            if (result < 1){
                return 1;
            }
            else {
                return result;
            }
        }
        else {
            int result = (int)((random.nextInt(regularMax) + regularMin) * this.difficulty.roundDifficultyMultiplier());
            if (result < 1){
                return 1;
            }
            else {
                return result;
            }
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
