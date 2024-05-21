package seng201.team0.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import seng201.team0.exceptions.TowerNotFoundException;
import seng201.team0.gui.FXWrapper;
import seng201.team0.gui.RandomEventController;
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
import javafx.scene.Parent;
import seng201.team0.utils.Utilities;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

public class GameEnv {
    private Player player;
    private Shop shop;
    private Difficulty difficulty;
    private int numRounds;
    private int currentRoundNum = 0;
    private int difficultyMultiplier;
    private PlayerService playerService;
    private InventoryService inventoryService;
    private ShopService shopService;
    private final int defaultTowerCost = 5;
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
    public final Runnable clearScreen;
    private Exception currException;
    private boolean result;
    private RandomEvent currRandomEvent;

    public GameEnv(Consumer<GameEnv> startLauncher, Consumer<GameEnv> setupLauncher, Runnable clearScreen, Consumer<GameEnv> playLauncher, Consumer<GameEnv> inventoryLauncher, Consumer<GameEnv> shopLauncher, Consumer<GameEnv> roundSummaryLauncher, Consumer<GameEnv> roundStyleScreenLauncher, Consumer<GameEnv> gameOverLauncher, Consumer<GameEnv> randomEventLauncher) {

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
        launchStartScreen();
    }

    public void closeStartScreen() {
        clearScreen.run();
        launchSetupScreen();
    }

    public void closeSetupScreen() {
        clearScreen.run();
        launchRoundStyleScreen();

    }
    public void setResult(Boolean value) {
        result = value;
    }
    public void startRound() {
        clearScreen.run();
        launchPlayScreen();
    }
    public void openInventory() {
        clearScreen.run();
        launchInventoryScreen();
    }
    public void openShop() {
        clearScreen.run();
        launchShopScreen();
    }
    public void backToMain() {
        clearScreen.run();
        launchRoundSummaryScreen();
    }
    public void showFinishedGame() {
        clearScreen.run();
        launchGameOverScreen();
    }

    public void setHasWon(Boolean value) throws TowerNotFoundException {
        this.result = value;
        clearScreen.run();
        if (value) {
            currentRoundNum += 1;
            // random event logic
            if (Utilities.weightedCoinToss(0.33)){
                int eventNumber = Utilities.randomEventSelector();
                if (eventNumber == 1) {
                    currRandomEvent = new TowerBreakEvent();
                } else if (eventNumber == 2) {
                    currRandomEvent = new TowerBuffEvent();
                } else {
                    currRandomEvent = new TowerDebuffEvent();
                }
                currRandomEvent.apply(inventoryService);
                openRandomEvent();
            }
            else {
                launchRoundSummaryScreen();
            }
        } else {
            launchRoundSummaryScreen();

        }
    }
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
    public Exception getCurrException() {
        return currException;
    }
    public void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
