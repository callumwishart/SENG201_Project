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

public class GameEnv {
    private Player player;
    private Shop shop;
    private Difficulty difficulty;
    private int numRounds;

    private int currentRoundNum = 1;
    private int difficultyMultiplier;
    private PlayerService playerService;
    private InventoryService inventoryService;
    private ShopService shopService;
    private final int defaultTowerCost = 30;
    private final int defaultTowerReload = 5;
    private List<Tower> possibleTowers = List.of(new Factory(defaultTowerReload,defaultTowerCost), new Farm(defaultTowerReload,defaultTowerCost), new Mine(defaultTowerReload,defaultTowerCost), new Sawmill(defaultTowerReload,defaultTowerCost), new WaterTower(defaultTowerReload,defaultTowerCost));
    private List<Upgrade> possibleUpgrades = List.of(new CapacityUpgrade(), new MoneyUpgrade(), new SpeedUpgrade());
    private List<Consumable> possibleConsumables = List.of(new Shield(), new SlowCartBooster(), new TowerSpeedBooster());
    private final Consumer<GameEnv> startLauncher;
    private final Consumer<GameEnv> setupLauncher;
    private final Consumer<GameEnv> playLauncher;
    private final Consumer<GameEnv> inventoryLauncher;
    private final Consumer<GameEnv> shopLauncher;
    private final Consumer<GameEnv> roundSummaryScreenLauncher;
    private final Consumer<GameEnv> roundStyleScreenLauncher;
    private final Consumer<GameEnv> gameOverLauncher;
    private final Consumer<GameEnv> randomEventLauncher;
    private final Consumer<GameEnv> instructionsLauncher;
    public final Runnable clearScreen;
    private boolean result;
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
        this.playerService = new PlayerService(player);
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
     * Sets the result variable to whether the player won or lost.
     * <p>
     * Clears the screen
     * Uses weightedCoinToss to decide if a random event will be called, if so the randomEventSelector
     * is used to decide what random event will occur and will open call openRandomEvent(). If no random event then
     * the round summary screen is launched.
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
            if (Utilities.weightedCoinToss(0.33)){
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
     * Calls randomEventLauncher to show the random_event.fxml
     */
    public void openRandomEvent() {
        randomEventLauncher.accept(this);
    }

    public void launchStartScreen() {
        startLauncher.accept(this);
    }
    public void launchShopScreen() {shopLauncher.accept(this);}
    public void launchSetupScreen() {
        setupLauncher.accept(this);
    }
    public void launchPlayScreen() {playLauncher.accept(this);}
    public void launchInventoryScreen() {inventoryLauncher.accept(this);}
    public void launchRoundSummaryScreen() {roundSummaryScreenLauncher.accept(this);}
    private void launchRoundStyleScreen() {
        roundStyleScreenLauncher.accept(this);
    }
    private void launchGameOverScreen() {
        gameOverLauncher.accept(this);
    }
    private void launchInstructionsScreen() {
        instructionsLauncher.accept(this);
    }
    public Player getPlayer() {
        return this.player;
    }

    public boolean getResult() {
        return result;
    }

    public void setNumRounds(int value) {
        numRounds = value;
    }

    public List<Tower> getPossibleTowers() {
        return possibleTowers;
    }
    public List<Upgrade> getPossibleUpgrades() {return possibleUpgrades;}
    public List<Consumable> getPossibleConsumables() {return possibleConsumables;}
    public RandomEvent getCurrRandomEvent() {
        return currRandomEvent;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
    public int getRoundNum() {
        return currentRoundNum;
    }

    public int getNumRounds() {
        return numRounds;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
    public InventoryService getInventoryService() {
        return inventoryService;
    }
    public ShopService getShopService() {
        return shopService;
    }
    public void setCurrentRoundNum(int currentRoundNum) {
        this.currentRoundNum = currentRoundNum;
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
