package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import seng201.team0.models.GameEnv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class RoundSummaryController {
    @FXML
    private Label playerLabel, winLabel, coinsLabel, pointsLabel, roundLabel;
    @FXML
    private ImageView t1Img, t2Img, t3Img, t4Img, t5Img;
    @FXML
    private Button nextButton;
    private final GameEnv gameEnv;

    public RoundSummaryController(GameEnv gameEnv) {this.gameEnv = gameEnv;}

    public void initialize() {
        List<ImageView> towerImages = List.of(t1Img, t2Img, t3Img, t4Img, t5Img);
        playerLabel.setText(gameEnv.getPlayer().getName());
        String result;
        if (gameEnv.getResult()) {
            result = "Won!";
        } else {
            result = "Lost!";
            nextButton.setDisable(true);
        }
        winLabel.setText("Round " + (gameEnv.getRoundNum() - 1) + " " + result);
        coinsLabel.setText("Coins: " + gameEnv.getInventoryService().getCoins());
        pointsLabel.setText("Points: " + gameEnv.getInventoryService().getPoints());
        roundLabel.setText("Round: " + (this.gameEnv.getRoundNum() - 1) + "/" + this.gameEnv.getNumRounds());
        for (int i = 0; i < this.gameEnv.getInventoryService().getActiveTowers().size(); i ++) {
            String imagePath = this.gameEnv.getInventoryService().getActiveTowers().get(i).getImagePath();
            try (InputStream inputStream = getClass().getResourceAsStream(imagePath)) {
                if (inputStream == null) {
                    throw new RuntimeException("Resource not found: " + imagePath);
                }
                Image image = new Image(inputStream);
                towerImages.get(i).setImage(image);  // replace 'i' with appropriate index
            } catch (IOException e) {
                throw new RuntimeException("Failed to load image: " + imagePath, e);
            }

        }
        if (this.gameEnv.getRoundNum() - 1 == this.gameEnv.getNumRounds()) {
            nextButton.setDisable(true);
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
    public void nextRound() {
        this.gameEnv.getDifficulty().incrementCostMultiplier();
        this.gameEnv.openRoundStyle();
    }
    @FXML
    public void finishGame() {
        if (this.gameEnv.getRoundNum() - 1 != this.gameEnv.getNumRounds()) {
            this.gameEnv.setResult(false);
        }
        this.gameEnv.showFinishedGame();
    }
}
