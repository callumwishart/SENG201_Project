package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import seng201.team0.models.GameEnv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class RoundSummaryController {
    @FXML
    private Label playerLabel, winLabel, coinsLabel, pointsLabel, roundLabel;
    @FXML
    private ImageView t1Img, t2Img, t3Img, t4Img, t5Img;
    @FXML
    private Button nextButton;
    private GameEnv gameEnv;
    private String result;
    public RoundSummaryController(GameEnv gameEnv) {this.gameEnv = gameEnv;}

    public void initialize() {
        List<ImageView> towerImages = List.of(t1Img, t2Img, t3Img, t4Img, t5Img);
        playerLabel.setText(gameEnv.getPlayer().getName());
        if (gameEnv.getResult()) {
            result = "Won!";
        } else {
            result = "Lost!";
            nextButton.setDisable(true);
        }
        winLabel.setText("Round " + String.valueOf(gameEnv.getRoundNum()) + " " + result);
        coinsLabel.setText("Coins: " + gameEnv.getInventoryService().getCoins());
        pointsLabel.setText("Points: " + gameEnv.getInventoryService().getPoints());
        roundLabel.setText("Round: " + String.valueOf(this.gameEnv.getRoundNum()) + "/" + String.valueOf(this.gameEnv.getNumRounds()));
        for (int i = 0; i < this.gameEnv.getInventoryService().getActiveTowers().size(); i ++) {
            String imagePath = this.gameEnv.getInventoryService().getActiveTowers().get(i).getImagePath();
            FileInputStream inputStream;
            try {
                inputStream = new FileInputStream(imagePath);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            Image image = new Image(inputStream);
            towerImages.get(i).setImage(image);
        }
    }

    @FXML
    public void openInventory() {
        this.gameEnv.openInventory();
    }
    @FXML
    public void openShop() {
        this.gameEnv.openShop();
    }
    @FXML
    public void nextRound() throws InterruptedException {
        this.gameEnv.openRoundStyle();
    }
    @FXML
    public void finishGame() {
        if (this.gameEnv.getRoundNum() != this.gameEnv.getNumRounds()) {
            this.gameEnv.setResult(false);
        }
        this.gameEnv.showFinishedGame();
    }
}
