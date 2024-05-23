package seng201.team0.models;

import javafx.scene.control.Alert;
import seng201.team0.exceptions.TowerNotFoundException;
import seng201.team0.models.consumables.Consumable;
import seng201.team0.models.consumables.Shield;
import seng201.team0.models.consumables.SlowCartBooster;
import seng201.team0.models.consumables.TowerSpeedBooster;
import seng201.team0.models.randomevents.RandomEvent;
import seng201.team0.models.randomevents.TowerBreakEvent;
import seng201.team0.models.randomevents.TowerBuffEvent;
import seng201.team0.models.randomevents.TowerDebuffEvent;
import seng201.team0.models.towers.*;
import seng201.team0.models.upgrades.CapacityUpgrade;
import seng201.team0.models.upgrades.MoneyUpgrade;
import seng201.team0.models.upgrades.SpeedUpgrade;
import seng201.team0.models.upgrades.Upgrade;
import seng201.team0.services.InventoryService;
import seng201.team0.services.PlayerService;
import seng201.team0.services.ShopService;
import seng201.team0.utils.Utilities;

import java.util.List;
import java.util.function.Consumer;

/**
 * This class is used to hold all of the information of the game and can be thought of as the over
 * arching controller
 */
public class GameEnv {
    /**
     * Player Attribute used to store the player
     */
    private Player player;
    /**
     * Shop attribute used to store instance of the shop
     */
    private final Shop shop;
    /**
     * Difficulty attribute used to hold instance of the Difficulty class
     */
    private Difficulty difficulty;
    /**
     * Integer to store the number of rounds that have been selected
     */
    private int numRounds;
    /**
     * Integer to store the current round number
     */
    private int currentRoundNum = 1;
    /**
     * Inventory service to store instance of the InventoryService Class and is initialised in the GameEnv constructor
     */
    private InventoryService inventoryService;
    /**
     * Shop service to store instance of the ShopService class which is initialized in the GameEnv constructor
     */
    private ShopService shopService;
    /**
     * List of towers that holds the list of possible towers for setup screen to use
     */
    private final List<Tower> possibleTowers = List.of(new Factory(), new Farm(), new Mine(), new Sawmill(), new WaterTower());
    /**
     * List of upgrades that holds options for setup screen to use
     */
    private final List<Upgrade> possibleUpgrades = List.of(new CapacityUpgrade(), new MoneyUpgrade(), new SpeedUpgrade());
    /**
     * List of consumables that holds options for setup screen to use
     */
    private final List<Consumable> possibleConsumables = List.of(new Shield(), new SlowCartBooster(), new TowerSpeedBooster());
    /**
     * A consumer that takes a {@link GameEnv} object and opens the start screen
     */
    private final Consumer<GameEnv> startLauncher;
    /**
     * A consumer that takes a GameEnv object and opens the setup screen
     */
    private final Consumer<GameEnv> setupLauncher;
    /**
     * A consumer that takes a GameEnv object and opens the main play screen
     */
    private final Consumer<GameEnv> playLauncher;
    /**
     * A consumer that takes a GameEnv object and opens the inventory screen.
     */
    private final Consumer<GameEnv> inventoryLauncher;
    /**
     * A consumer that takes a GameEnv object and opens the shop screen
     */
    private final Consumer<GameEnv> shopLauncher;
    /**
     * A consumer that takes a GameEnv object and opens the round summary screen.
     */
    private final Consumer<GameEnv> roundSummaryScreenLauncher;
    /**
     * A consumer that takes a GameEnv object and opens the round style screen
     */
    private final Consumer<GameEnv> roundStyleScreenLauncher;
    /**
     * A consumer that takes a GameEnv object and opens the game over screen
     */
    private final Consumer<GameEnv> gameOverLauncher;
    /**
     * A consumer that takes a GameEnv object and opens the game over screen
     */
    private final Consumer<GameEnv> randomEventLauncher;
    /**
     * A consumer that takes a GameEnv object and opens the instructions screen
     */
    private final Consumer<GameEnv> instructionsLauncher;
    /**
     * A runnable method that will clear the screen
     */
    public final Runnable clearScreen;
    /**
     * A boolean that stores the current result of the game
     */
    private boolean result;
    /**
     * A RandomEvent that holds which randomEvent is currently being used
     */
    private RandomEvent currRandomEvent;

    /**
     * Constructor of GameEnv which links functions and creates new instances of player, shop, player service,
     * inventory service and shop service and then finally launches the start screen
     * @param startLauncher takes the function start launcher from FXWrapper and assigns it to this.startLauncher.
     * @param setupLauncher takes the function setupLauncher from FXWrapper and assigns it to this.setupLauncher.
     * @param clearScreen takes the function clearScreen from FXWrapper and assigns it to this.clearScreen.
     * @param playLauncher takes the function playLauncher from FXWrapper and assigns it to this.playLauncher
     * @param inventoryLauncher takes the function inventoryLauncher from FXWrapper and assigns it to this.inventoryLauncher
     * @param shopLauncher shopLauncher function to launch the shop screen.
     * @param roundSummaryLauncher roundSummaryLauncher function to launch the round summary screen.
     * @param roundStyleScreenLauncher roundStyleScreen Launcher function to launch the round style screen.
     * @param gameOverLauncher gameOverLauncher function to launch the gameOver screen.
     * @param randomEventLauncher randomEventLauncher function to launch the random event screen.
     * @param instructionsLauncher instructionsLauncher function to launch instructions
     */
    public GameEnv(Consumer<GameEnv> startLauncher, Consumer<GameEnv> setupLauncher, Runnable clearScreen, Consumer<GameEnv> playLauncher, Consumer<GameEnv> inventoryLauncher, Consumer<GameEnv> shopLauncher, Consumer<GameEnv> roundSummaryLauncher, Consumer<GameEnv> roundStyleScreenLauncher, Consumer<GameEnv> gameOverLauncher, Consumer<GameEnv> randomEventLauncher, Consumer<GameEnv> instructionsLauncher) {

        this.player = new Player();
        this.shop = new Shop();
        this.inventoryService = new InventoryService(player.getInventory());
        this.shopService = new ShopService(shop, this.inventoryService);
        this.startLauncher = startLauncher;
        this.setupLauncher = setupLauncher;
        this.clearScreen = clearScreen;
        this.playLauncher = playLauncher;
        this.inventoryLauncher = inventoryLauncher;
        this.shopLauncher = shopLauncher;
        this.roundSummaryScreenLauncher = roundSummaryLauncher;
        this.roundStyleScreenLauncher = roundStyleScreenLauncher;
        this.gameOverLauncher = gameOverLauncher;
        this.randomEventLauncher = randomEventLauncher;
        this.instructionsLauncher = instructionsLauncher;
        launchStartScreen();
    }

    /**
     * Closes start screen and opens setup screen
     * <p>
     *     First clears the screen using {@code clearScreen} runnable.
     *     Then launches the setup screen using {@code launchSetupScreen}.
     * </p>
     */
    public void closeStartScreen() {
        clearScreen.run();
        launchSetupScreen();
    }

    /**
     * Opens the round style screen
     * <p>
     *     First clears the screen using {@code clearScreen} runnable.
     *     Then launches the round style screen using {@code launchRoundStyleScreen}
     * </p>
     */
    public void openRoundStyle() {
        clearScreen.run();
        launchRoundStyleScreen();

    }

    /**
     * This sets the result variable
     * <p>
     *      This updates the {@code result} field with the provided Boolean value.
     * </p>
     * @param value the new Boolean value to set for {@code result}.
     */
    public void setResult(Boolean value) {
        result = value;
    }

    /**
     * Opens the main play screen
     * <p>
     *     First this clears the screen using the {@code clearScreen} method.
     *     It then launches the Play screen using {@code launchPlayScreen}.
     * </p>
     */
    public void startRound() {
        clearScreen.run();
        launchPlayScreen();
    }

    /**
     * Opens the inventory
     * <p>
     *     This method first clears the screen using the {@code clearScreen} method.
     *     It then launches the inventory screen by calling {@code launchInventoryScreen}.
     * </p>
     */
    public void openInventory() {
        clearScreen.run();
        launchInventoryScreen();
    }

    /**
     * Opens the shop
     * <p>
     *     This method first clears the screen using the {@code clearScreen} method.
     *     It then launches the shop screen by calling {@code launchShopScreen}.
     * </p>
     */
    public void openShop() {
        clearScreen.run();
        launchShopScreen();
    }

    /**
     * Opens the main playable screen
     * <p>
     *     This method first clears the current screen by executing this {@code clearScreen} runnable.
     *     It then launches the round summary screen by calling the {@code launchRoundSummaryScreen} method.
     * </p>
     */
    public void backToMain() {
        clearScreen.run();
        launchRoundSummaryScreen();
    }

    /**
     * Opens the Finished Game Screen
     * <p>
     *     This method first clears the current screen by executing this {@code clearScreen} runnable.
     *     It then launches the game over screen by calling the {@code launchGameOverScreen} method.
     * </p>
     */
    public void showFinishedGame() {
        clearScreen.run();
        launchGameOverScreen();
    }
    /**
     * Opens the instruction screen for the application.
     * <p>
     * This method first clears the current screen by executing
     * the {@code clearScreen} runnable. It then launches the
     * instructions screen by calling the {@code launchInstructionsScreen}
     * method.
     * </p>
     */
    public void openInstruction() {
        clearScreen.run();
        launchInstructionsScreen();
    }

    /**
     * Sets the game result and handles the game logic based on whether the player has won.
     * <p>
     *  This method updates the {@code result} field with the provided Boolean value, clears the screen,
     *  and increments the round number. If the player has won (i.e., {@code value} is {@code true}),
     *  there is a 33% chance that a random event will occur. Depending on the randomly selected event,
     *  either a tower break, tower buff, or tower debuff event is applied. If no random event occurs,
     *  or if the player has not won, the round summary screen is launched otherwise the random event message screen
     *  is launched.
     * </p>
     * @param value boolean value which is true if the user won that round and false if the user lost the round
     * @throws TowerNotFoundException exception thrown if the random event annot be applied to the tower
     */
    public void setHasWon(Boolean value) throws TowerNotFoundException {
        this.result = value;
        clearScreen.run();
        if (value) {
            currentRoundNum += 1;
            // random event logic
            if (Utilities.weightedCoinToss(0.2)){
                int eventNumber = Utilities.randomEventSelector();
                if (eventNumber == 1) {
                    try{
                        currRandomEvent = new TowerBreakEvent();
                        currRandomEvent.apply(inventoryService);
                    }
                    catch (TowerNotFoundException e){
                        currRandomEvent = new TowerDebuffEvent();
                    }
                } else if (eventNumber == 2) {
                    currRandomEvent = new TowerBuffEvent();
                    currRandomEvent.apply(inventoryService);
                } else {
                    currRandomEvent = new TowerDebuffEvent();
                    currRandomEvent.apply(inventoryService);
                }
                openRandomEvent();
            }
            else {
                launchRoundSummaryScreen();
            }
        } else {
            currentRoundNum += 1;
            launchRoundSummaryScreen();

        }
    }

    /**
     * Launches the random event screen
     * <p>
     *     This method will open the random event launcher by invoking the {@code randomEventLauncher}
     *     consumer with the current instance of the gameEnv
     * </p>
     */
    public void openRandomEvent() {
        randomEventLauncher.accept(this);
    }

    /**
     * Launches the start screen
     * <p>
     *     This method will open the start screen by invoking {@code startLauncher}
     *     consumer with the current instance of the gameEnv
     * </p>
     */
    public void launchStartScreen() {
        startLauncher.accept(this);
    }

    /**
     * Launches the shop screen
     * <p>
     *     This method will open the shop by invoking {@code shopLauncher}
     *     consumer with the current instance of the gameEnv
     * </p>
     */
    public void launchShopScreen() {shopLauncher.accept(this);}

    /**
     * Launches the setup screen
     * <p>
     *     This method will open the setup screen by invoking {@code setupLauncher}
     *     consumer with the current instance of gameEnv
     * </p>
     */
    public void launchSetupScreen() {
        setupLauncher.accept(this);
    }
    /**
     * Launches the main game screen
     * <p>
     *     This method will open the main screen by invoking {@code playLauncher}
     *     consumer with the current instance of gameEnv
     * </p>
     */
    public void launchPlayScreen() {playLauncher.accept(this);}

    /**
     * Launches the inventory screen
     * <p>
     *     This method will open the inventory screen by invoking {@code inventoryLauncher}
     *     consumer with the current instance of gameEnv
     * </p>
     */
    public void launchInventoryScreen() {inventoryLauncher.accept(this);}

    /**
     * Launches the round summary screen
     * <p>
     *     This method will open the round summary screen by invoking {@code roundSummaryScreenLauncher}
     *     consumer with the current instance of gameEnv
     * </p>
     */
    public void launchRoundSummaryScreen() {roundSummaryScreenLauncher.accept(this);}

    /**
     * Launches the round style screen
     * <p>
     *     This method will open the round style selector screen by invoking {@code roundStyleScreenLauncher}
     *     consumer with the current instance of gameEnv
     * </p>
     */
    private void launchRoundStyleScreen() {
        roundStyleScreenLauncher.accept(this);
    }

    /**
     * Launches the game over screen
     * <p>
     *     This method will launch the game over screen by invoking {@code gameOverLauncher}
     *     consumer with the current instance of gameEnv
     * </p>
     */
    private void launchGameOverScreen() {
        gameOverLauncher.accept(this);
    }

    /**
     * Launches instructions screen
     * <p>
     *     This method will launch the game instructions screen {@code instructionsLauncher}
     *     consumer with the current instance of gameEnv
     * </p>
     */
    private void launchInstructionsScreen() {
        instructionsLauncher.accept(this);
    }

    /**
     * Gets the current Player instance
     * @return the {@code this.player} as a Player
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Gets the result
     * @return the result as a boolean
     */
    public boolean getResult() {
        return result;
    }
    /**
     * @param value value which the number of rounds will be set to
     */
    public void setNumRounds(int value) {
        numRounds = value;
    }

    /**
     * Gets the possible towers
     * @return a list of towers that are possible options
     */
    public List<Tower> getPossibleTowers() {
        return possibleTowers;
    }

    /**
     * Gets the possible upgrades
     * @return a list of possible upgrades
     */
    public List<Upgrade> getPossibleUpgrades() {return possibleUpgrades;}

    /**
     * Gets possible consumables
     * @return a list of possible consumables
     */
    public List<Consumable> getPossibleConsumables() {return possibleConsumables;}

    /**
     * Gets the current random event
     * @return a RandomEvent which will is going to be used
     */
    public RandomEvent getCurrRandomEvent() {
        return currRandomEvent;
    }

    /**
     * Gets the current instance of the difficulty
     * @return a Difficulty class which holds current difficulty attributes
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Gets the current round number
     * @return an integer representing the current round number
     */
    public int getRoundNum() {
        return currentRoundNum;
    }

    /**
     * Gets the number of rounds to be played
     * @return the number of rounds to be played as an integer
     */
    public int getNumRounds() {
        return numRounds;
    }

    /**
     * Sets the difficulty
     * <p>
     *     First this sets the current difficulty instance to the provided difficulty
     *     Then sets the shop services difficulty
     * </p>
     * @param difficulty is the difficulty that everything will be set to
     */
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        this.shopService.setDifficulty(difficulty);
    }

    /**
     * Gets the inventory service
     * @return the inventory service in the gameEnv
     */
    public InventoryService getInventoryService() {
        return inventoryService;
    }

    /**
     * Gets the shop service
     * @return the shop service in this gameEnv
     */
    public ShopService getShopService() {
        return shopService;
    }

    /**
     * Sets the current round number
     * @param currentRoundNum the value that the round number will be set to as an integer
     */
    public void setCurrentRoundNum(int currentRoundNum) {
        this.currentRoundNum = currentRoundNum;
    }

    /**
     * Create a new game
     * <p>
     *     This is actioned when the user wants to play again. It first
     *     creates a new instance of the player, inventoryService and shopService
     *     then resets the current round to 1 and calls {@code closeStartScreen()} to open the setup
     *     screen
     * </p>
     */
    public void startNewGame() {
        this.player = new Player();
        this.inventoryService = new InventoryService(player);
        this.shopService = new ShopService(this.shop, this.inventoryService);
        setCurrentRoundNum(1);
        closeStartScreen();
    }

    /**
    * Accepts a title and a message which can then create an alert on screen where the
     * user can view and close the alert. This method will not return anything
     * @param title a string which is the title of an alert
     * @param content a string which is the content of the error
     */
    public void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
