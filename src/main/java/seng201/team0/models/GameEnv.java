package seng201.team0.models;

import seng201.team0.models.gameplay.GameRunner;
import seng201.team0.models.gameplay.Round;
import seng201.team0.models.towers.*;
import seng201.team0.services.InventoryService;
import seng201.team0.services.PlayerService;
import seng201.team0.services.ShopService;

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
    private final Consumer<GameEnv> startLauncher;
    private final Consumer<GameEnv> setupLauncher;
    private final Consumer<GameEnv> playLauncher;
    private final Runnable clearScreen;

    public GameEnv(Consumer<GameEnv> startLauncher, Consumer<GameEnv> setupLauncher, Runnable clearScreen, Consumer<GameEnv> playLauncher) {
        this.player = new Player();
        this.shop = new Shop();
        this.playerService = new PlayerService(player);
        this.inventoryService = new InventoryService(player.getInventory());
        this.shopService = new ShopService(shop, this.inventoryService);
        this.startLauncher = startLauncher;
        this.setupLauncher = setupLauncher;
        this.clearScreen = clearScreen;
        this.playLauncher = playLauncher;
        launchStartScreen();
    }

    public void closeStartScreen() {
        clearScreen.run();
        launchSetupScreen();
    }

    public void closeSetupScreen() throws InterruptedException {
        clearScreen.run();
        launchPlayScreen();
        // Round round = new Round();
    }

    public void launchStartScreen() {
        startLauncher.accept(this);
    }

    public void launchSetupScreen() {
        setupLauncher.accept(this);
    }
    public void launchPlayScreen() {playLauncher.accept(this);}

    public Player getPlayer() {
        return this.player;
    }

    public void setNumRounds(int value) {
        numRounds = value;
    }

    public List<Tower> getPossibleTowers() {
        return possibleTowers;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
    public void openInventory() {

    }

}
