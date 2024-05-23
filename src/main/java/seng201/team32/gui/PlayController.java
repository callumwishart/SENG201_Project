package seng201.team32.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import seng201.team32.exceptions.NegativeAdditionException;
import seng201.team32.exceptions.TowerNotFoundException;
import seng201.team32.models.GameEnv;
import seng201.team32.models.gameplay.Cart;
import seng201.team32.models.gameplay.GameObserver;
import seng201.team32.models.gameplay.GameRunner;
import seng201.team32.models.gameplay.Round;
import seng201.team32.models.towers.Tower;
import seng201.team32.services.InventoryService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class PlayController implements GameObserver {
    @FXML
    private Label nameLabel, pointsLabel;
    @FXML
    private Button c1Btn, c2Btn, c3Btn, c4Btn, c5Btn, c6Btn, c7Btn, c8Btn, c9Btn, c10Btn, c11Btn, c12Btn, c13Btn, c14Btn, c15Btn;
    @FXML
    private ProgressBar c1Progress, c2Progress, c3Progress, c4Progress, c5Progress, c6Progress, c7Progress, c8Progress, c9Progress, c10Progress;
    @FXML
    private ProgressBar c11Progress, c12Progress, c13Progress, c14Progress, c15Progress;
    @FXML
    private ProgressBar c1Capacity, c2Capacity, c3Capacity, c4Capacity, c5Capacity, c6Capacity, c7Capacity, c8Capacity, c9Capacity, c10Capacity;
    @FXML
    private ProgressBar c11Capacity, c12Capacity, c13Capacity, c14Capacity, c15Capacity;
    @FXML
    private Label c1Speed, c2Speed, c3Speed, c4Speed, c5Speed, c6Speed, c7Speed, c8Speed, c9Speed, c10Speed, c11Speed, c12Speed, c13Speed, c14Speed, c15Speed, c1CapacityLabel, c2CapacityLabel, c3CapacityLabel, c4CapacityLabel, c5CapacityLabel, c6CapacityLabel, c7CapacityLabel, c8CapacityLabel, c9CapacityLabel, c10CapacityLabel, c11CapacityLabel, c12CapacityLabel, c13CapacityLabel, c14CapacityLabel, c15CapacityLabel;
    @FXML
    private Button t1Btn, t2Btn, t3Btn, t4Btn, t5Btn;
    @FXML
    private ProgressBar t1Progress, t2Progress, t3Progress, t4Progress, t5Progress;
    @FXML
    private ImageView tower1Img, tower2Img, tower3Img, tower4Img, tower5Img;
    @FXML
    private Button startButton;
    private final GameEnv gameEnv;
    private final Round round;
    private final GameRunner gameRunner;
    private final InventoryService inventoryService;
    private List<Cart> carts;
    private List<Tower> towers;
    private int trackDistance;

    /**
     * PlayController constructor
     * <p>
     *     Creates a new instance of the Round and new instance of the GameRunner
     * </p>
     * @param gameEnv current instance of gameEnv
     * @throws CloneNotSupportedException is thrown when Round throws errors
     */
    public PlayController(GameEnv gameEnv) throws CloneNotSupportedException {
        this.gameEnv = gameEnv;
        this.round = new Round(this.gameEnv.getInventoryService(), this.gameEnv.getDifficulty(), this.gameEnv.getRoundNum());
        this.gameRunner = new GameRunner(round, this, true);
        this.inventoryService = this.gameEnv.getInventoryService();
        this.carts = round.getCarts();
        this.towers = inventoryService.getActiveTowers();
        this.trackDistance = round.getTrackLength();
    }

    /**
     *Creates lists of buttons and tower images
     * Firstly sets the images of the towers and text of the towers. Next it
     * sets the carts and their respective speeds and buttons. It then sets the text of the player name and their points
     */
    public void initialize(){
        List<Button> cartButtons = List.of(c1Btn, c2Btn, c3Btn, c4Btn, c5Btn, c6Btn, c7Btn, c8Btn, c9Btn, c10Btn, c11Btn, c12Btn, c13Btn, c14Btn, c15Btn);
        List<Button> towerButtons = List.of(t1Btn, t2Btn, t3Btn, t4Btn, t5Btn);
        List<ImageView> towerImages = List.of(tower1Img, tower2Img, tower3Img, tower4Img, tower5Img);
        List<Label> speedLabels = List.of(c1Speed, c2Speed, c3Speed, c4Speed, c5Speed, c6Speed, c7Speed, c8Speed, c9Speed, c10Speed, c11Speed, c12Speed, c13Speed, c14Speed, c15Speed);
        for (int i = 0; i < inventoryService.getActiveTowers().size(); i ++) {
            String imagePath = inventoryService.getActiveTowers().get(i).getImagePath();
            try (InputStream inputStream = getClass().getResourceAsStream(imagePath)) {
                if (inputStream == null) {
                    throw new RuntimeException("Resource not found: " + imagePath);
                }
                Image image = new Image(inputStream);
                towerImages.get(i).setImage(image);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load image: " + imagePath, e);
            }

            towerButtons.get(i).setDisable(false);
            towerButtons.get(i).setText(inventoryService.getActiveTowers().get(i).getName());
        }
        for (int i = 0; i < round.getCarts().size(); i++) {
            cartButtons.get(i).setText(round.getCarts().get(i).getName());
            cartButtons.get(i).setDisable(false);
            cartButtons.get(i).setStyle("-fx-background-color: #ffcccc;");
            speedLabels.get(i).setText("Speed: " + round.getCarts().get(i).getSpeed());
        }
        nameLabel.setText("Name: " + this.gameEnv.getPlayer().getName());
        pointsLabel.setText("Points: " + inventoryService.getPoints());
    }

    /**
     * Starts the game and sets the button to disabled so that you cannot click start game multiple times
     */
    @FXML
    public void startGame() {
        startButton.setDisable(true);
        Thread gameThread = new Thread(gameRunner);
        gameThread.start();
    }

    /**
     * Calls {@code round.cleanup()} then adds coins and the points to the inventory and runs
     * the {@code gameEnv.setHasWon} function with the value of true
     * @param coins The value of coins earned from that round
     * @param points the value of the points earned from that round
     */
    @Override
    public void win(int coins, int points) throws NegativeAdditionException, TowerNotFoundException {
        round.cleanup(); // gets rid of consumables used, resets difficulty multipliers for next round
        this.inventoryService.addCoins(coins);
        this.inventoryService.addPoints(points);
        gameEnv.setHasWon(true);
    }

    /**
     * Calls {@code round.cleanup()} the runs the {@code gameEnv.setHasWon} with false value
     */
    @Override
    public void lose() throws TowerNotFoundException {
        round.cleanup();
        gameEnv.setHasWon(false);
    }

    /**
     * Update Cart Stats
     * <p>
     *     This function is called each time there is an update and sets the progress of each the carts along the track
     *     and the how full they are. This then also sets the colour of the cart to green if it is full and orange
     *     if it is being filled.
     * </p>
     */
    public void updateCartStats() {
        List<ProgressBar> progressBars = List.of(c1Progress, c2Progress, c3Progress, c4Progress, c5Progress, c6Progress, c7Progress, c8Progress, c9Progress, c10Progress, c11Progress, c12Progress, c13Progress, c14Progress, c15Progress);
        List<Button> cartButtons = List.of(c1Btn, c2Btn, c3Btn, c4Btn, c5Btn, c6Btn, c7Btn, c8Btn, c9Btn, c10Btn, c11Btn, c12Btn, c13Btn, c14Btn, c15Btn);
        List<ProgressBar> capacityBars = List.of(c1Capacity, c2Capacity, c3Capacity, c4Capacity, c5Capacity, c6Capacity, c7Capacity, c8Capacity, c9Capacity, c10Capacity, c11Capacity, c12Capacity, c13Capacity, c14Capacity, c15Capacity);
        List<Label> capacityLabels = List.of(c1CapacityLabel, c2CapacityLabel, c3CapacityLabel, c4CapacityLabel, c5CapacityLabel, c6CapacityLabel, c7CapacityLabel, c8CapacityLabel, c9CapacityLabel, c10CapacityLabel, c11CapacityLabel, c12CapacityLabel, c13CapacityLabel, c14CapacityLabel, c15CapacityLabel);
        for (int i = 0; i < carts.size(); i++) {
            progressBars.get(i).setProgress((double) carts.get(i).getDistance() / this.trackDistance);
            capacityBars.get(i).setProgress((double) carts.get(i).getCargoSlotsFilled()/ carts.get(i).getSize());
            capacityLabels.get(i).setText(String.format("Capacity: %d/%d", carts.get(i).getCargoSlotsFilled(), carts.get(i).getSize()));
            if (this.carts.get(i).getCargoSlotsFilled() != 0 && !(this.carts.get(i).isFull())) {
                cartButtons.get(i).setStyle("-fx-background-color: #ffe6cc;");
            } else if (this.carts.get(i).isFull()) {
                cartButtons.get(i).setStyle("-fx-background-color: #ccffcc;");
            }
        }
    }

    /**
     * Update Tower function
     * <p>
     *     Initialises the tower progress bars and updates them based on how far along they
     *     are in their reload progress.
     * </p>
     */
    public void updateTowerStats() {
        List<ProgressBar> towerBars = List.of(t1Progress, t2Progress, t3Progress, t4Progress, t5Progress);
        for (int i = 0; i < this.towers.size(); i++) {
            towerBars.get(i).setProgress((double) towers.get(i).getReloadTimeElapsed() / towers.get(i).getReloadSpeed());
        }
    }

    /**
     * Observe Function
     * <p>
     *     This is called each time there is an update in the game runner and will update the screen using
     *     {@code updateCartStats()} and {@code updateTowerStats()}
     * </p>
     * @param gameRunner the current gameRunner
     */
    @Override
    public void observe(GameRunner gameRunner) {
        this.carts = gameRunner.getCarts();
        this.towers = gameRunner.getTowers();
        this.trackDistance = gameRunner.getTrackDistance();
        this.updateCartStats();
        this.updateTowerStats();
    }
}
