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

    /**
     * Applies the consumables by getting them first then using the {@code apply()} method of consumable
     */
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

    /**
     * Method to create the carts
     * <p>
     *     This method will check how many carts it should create and then decide whether or not to make a universal
     *     cart by using a random int, if not it will create a cart that will match at least one of the towers in
     *     the players inventory
     * </p>
     * @param amount is the amount of carts that will be created
     * @throws CloneNotSupportedException is thrown when a resource cannot be cloned
     */
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

    /**
     * Gets a resource to set the type of cart
     * <p>
     *     This will get the list of active towers and add their resource to a list of possible resources
     *     It will then create a random resource based on the possible towers and return it
     * </p>
     * @return a resource which corresponds to a current active tower in inventory
     */
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
     * This method will set the carts size based on if it is a universal cart or a regular carts
     * <p>
     *     This is done by using Random to generate a random int and bounding it by
     *     the max of the type of carts
     * </p>
     * @return an integer of what the generated cart size will be
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

    /**
     * This method is used to calculate the track length
     * <p>
     *     Uses a random integer generator to create variation in track length and then scales it based on what round you
     *     are on
     * </p>
     * @return an integer representation of the track length
     */
    public int calculateTrackLength(){
        int defaultLength = 50;
        int growthRate = 10;
        int variationBound = 15;
        Random random = new Random();
        int variation = random.nextInt(variationBound);
        return defaultLength + (int)((this.roundNum / 2)*growthRate) + variation;
    }

    /**
     * This will reset the difficulty after a round is done using the {@code difficulty.reset()} method.
     */
    public void cleanup() {
        this.removeConsumables();
        this.difficulty.reset();
    }

    /**
     * This will remove the consumable and be applied after the round is done, this will also remove it from
     * the players inventory
     */
    private void removeConsumables() {
        for (Consumable consumable : this.inventoryService.getConsumables()){
            consumable.remove(this);
        }
        this.inventoryService.removeConsumables();
    }

    /**
     * Gets the current difficulty object
     * @return a Difficulty object to calculate various stats for the game
     */
    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    /**
     * Checks if the user has a shield
     * @return a boolean to show if the user has a shield or not
     */
    public boolean hasShield(){
        return this.hasShield;
    }

    /**
     * Gets the current round number
     * @return an integer rep of the current round number
     */
    public int getRoundNum() {
        return this.roundNum;
    }

    /**
     * Gets the current carts
     * @return an ArrayList of the carts
     */
    public ArrayList<Cart> getCarts() {
        return this.carts;
    }

    /**
     * Gets the current consumables
     * @return an ArrayList of the consumable in use
     */
    public ArrayList<Consumable> getConsumables() {
        return this.consumables;
    }

    /**
     * Sets the shield
     * @param b boolean that this shield will be set to
     */
    public void setShield(boolean b) {
        this.hasShield = b;
    }

    /**
     * Gets the towers from the inventory
     * @return an ArrayList of towers
     */
    public ArrayList<Tower> getTowers() {
        return this.inventoryService.getActiveTowers();
    }

    /**
     * Gets the track length
     * @return an integer representing the track length
     */
    public int getTrackLength() {
        return this.trackLength;
    }
}
