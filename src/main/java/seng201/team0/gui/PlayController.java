package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import seng201.team0.exceptions.NegativeAdditionException;
import seng201.team0.models.Difficulty;
import seng201.team0.models.GameEnv;
import seng201.team0.models.gameplay.Cart;
import seng201.team0.models.gameplay.GameObserver;
import seng201.team0.models.gameplay.GameRunner;
import seng201.team0.models.gameplay.Round;
import seng201.team0.models.towers.Tower;
import seng201.team0.services.InventoryService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class PlayController implements GameObserver {
    @FXML
    private Label nameLabel, pointsLabel;
    @FXML
    private Button c1Btn, c2Btn, c3Btn, c4Btn, c5Btn, c6Btn, c7Btn, c8Btn, c9Btn, c10Btn, c11Btn, c12Btn, c13Btn, c14Btn, c15Btn, c16Btn, c17Btn, c18Btn, c19Btn, c20Btn;
    @FXML
    private ProgressBar c1Progress, c2Progress, c3Progress, c4Progress, c5Progress, c6Progress, c7Progress, c8Progress, c9Progress, c10Progress;
    @FXML
    private ProgressBar c11Progress, c12Progress, c13Progress, c14Progress, c15Progress, c16Progress, c17Progress, c18Progress, c19Progress, c20Progress;
    @FXML
    private ProgressBar c1Capacity, c2Capacity, c3Capacity, c4Capacity, c5Capacity, c6Capacity, c7Capacity, c8Capacity, c9Capacity, c10Capacity;
    @FXML
    private ProgressBar c11Capacity, c12Capacity, c13Capacity, c14Capacity, c15Capacity, c16Capacity, c17Capacity, c18Capacity, c19Capacity, c20Capacity;
    @FXML
    private Label c1Speed, c2Speed, c3Speed, c4Speed, c5Speed, c6Speed, c7Speed, c8Speed, c9Speed, c10Speed, c11Speed, c12Speed, c13Speed, c14Speed, c15Speed, c16Speed, c17Speed, c18Speed, c19Speed, c20Speed;
    @FXML
    private Button t1Btn, t2Btn, t3Btn, t4Btn, t5Btn;
    @FXML
    private ProgressBar t1Progress, t2Progress, t3Progress, t4Progress, t5Progress;
    @FXML
    private ImageView tower1Img, tower2Img, tower3Img, tower4Img, tower5Img;
    private GameEnv gameEnv;
    private int roundNum;
    private Round round;
    private GameRunner gameRunner;
    private Difficulty difficulty;
    private InventoryService inventoryService;
    private List<Cart> carts;
    private List<Tower> towers;
    private int trackDistance;
    private boolean isSuccess;

    public PlayController(GameEnv gameEnv){
        this.gameEnv = gameEnv;
        this.round = new Round(this.gameEnv.getInventoryService(), this.gameEnv.getDifficulty(), this.gameEnv.getRoundNum());
        this.gameRunner = new GameRunner(round, this);
        this.roundNum = this.gameEnv.getRoundNum();
        this.difficulty = this.gameEnv.getDifficulty();
        this.inventoryService = this.gameEnv.getInventoryService();
        this.carts = round.getCarts();
        this.towers = inventoryService.getActiveTowers();
        this.trackDistance = round.getTrackLength();
    }
    public void initialize(){
        List<Button> cartButtons = List.of(c1Btn, c2Btn, c3Btn, c4Btn, c5Btn, c6Btn, c7Btn, c8Btn, c9Btn, c10Btn, c11Btn, c12Btn, c13Btn, c14Btn, c15Btn, c16Btn, c17Btn, c18Btn, c19Btn, c20Btn);
        List<Button> towerButtons = List.of(t1Btn, t2Btn, t3Btn, t4Btn, t5Btn);
        List<ImageView> towerImages = List.of(tower1Img, tower2Img, tower3Img, tower4Img, tower5Img);
        List<Label> speedLabels = List.of(c1Speed, c2Speed, c3Speed, c4Speed, c5Speed, c6Speed, c7Speed, c8Speed, c9Speed, c10Speed, c11Speed, c12Speed, c13Speed, c14Speed, c15Speed, c16Speed, c17Speed, c18Speed, c19Speed, c20Speed);
        for (int i = 0; i < inventoryService.getActiveTowers().size(); i ++) {
            String imagePath = inventoryService.getActiveTowers().get(i).getImagePath();
            FileInputStream inputStream;
            try {
                inputStream = new FileInputStream(imagePath);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            Image image = new Image(inputStream);
            towerImages.get(i).setImage(image);
            towerButtons.get(i).setDisable(false);
        }
        for (int i = 0; i < round.getCarts().size(); i++) {
            cartButtons.get(i).setText(round.getCarts().get(i).getName());
            cartButtons.get(i).setDisable(false);
            cartButtons.get(i).setStyle("-fx-background-color: #ffcccc;");
            speedLabels.get(i).setText("Speed: " + round.getCarts().get(i).getSpeed());
        }
        nameLabel.setText(this.gameEnv.getPlayer().getName());
        pointsLabel.setText(String.valueOf(inventoryService.getPoints()));
    }
    @FXML
    public void startGame() throws InterruptedException {
        Thread gameThread = new Thread(gameRunner);
        gameThread.start();
    }

    @Override
    public void win(int coins, int points) throws NegativeAdditionException {
        this.inventoryService.addCoins(coins);
        this.inventoryService.addPoints(points);
        gameEnv.setHasWon(true);
    }

    @Override
    public void lose() {
        gameEnv.setHasWon(false);
    }

    public void updateCartStats() {
        List<ProgressBar> progressBars = List.of(c1Progress, c2Progress, c3Progress, c4Progress, c5Progress, c6Progress, c7Progress, c8Progress, c9Progress, c10Progress, c11Progress, c12Progress, c13Progress, c14Progress, c15Progress, c16Progress, c17Progress, c18Progress, c19Progress, c20Progress);
        List<Button> cartButtons = List.of(c1Btn, c2Btn, c3Btn, c4Btn, c5Btn, c6Btn, c7Btn, c8Btn, c9Btn, c10Btn, c11Btn, c12Btn, c13Btn, c14Btn, c15Btn, c16Btn, c17Btn, c18Btn, c19Btn, c20Btn);
        List<ProgressBar> capacityBars = List.of(c1Capacity, c2Capacity, c3Capacity, c4Capacity, c5Capacity, c6Capacity, c7Capacity, c8Capacity, c9Capacity, c10Capacity, c11Capacity, c12Capacity, c13Capacity, c14Capacity, c15Capacity, c16Capacity, c17Capacity, c18Capacity, c19Capacity, c20Capacity);
        for (int i = 0; i < carts.size(); i++) {
            progressBars.get(i).setProgress((double) carts.get(i).getDistance() / this.trackDistance);
            capacityBars.get(i).setProgress((double) carts.get(i).getCargoSlotsFilled()/ carts.get(i).getSize());
            if (this.carts.get(i).getCargoSlotsFilled() != 0 && !(this.carts.get(i).isFull())) {
                cartButtons.get(i).setStyle("-fx-background-color: #ffe6cc;");
            } else if (this.carts.get(i).isFull()) {
                cartButtons.get(i).setStyle("-fx-background-color: #ccffcc;");
            }
        }
    }
    public void updateTowerStats() {
        List<ProgressBar> towerBars = List.of(t1Progress, t2Progress, t3Progress, t4Progress, t5Progress);
        for (int i = 0; i < this.towers.size(); i++) {
            towerBars.get(i).setProgress((double) towers.get(i).getReloadTimeElapsed() / towers.get(i).getReloadSpeed());
        }
    }

    @Override
    public void observe(GameRunner gameRunner) {
        this.carts = gameRunner.getCarts();
        this.towers = gameRunner.getTowers();
        this.trackDistance = gameRunner.getTrackDistance();
        this.updateCartStats();
        this.updateTowerStats();
    }
}
