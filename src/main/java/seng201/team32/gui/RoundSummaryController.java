package seng201.team32.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import seng201.team32.models.GameEnv;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Controller for round_summary.fxml
 */
public class RoundSummaryController {
    /**
     * Labels to hold the player data
     */
    @FXML
    private Label playerLabel, winLabel, coinsLabel, pointsLabel, roundLabel;
    /**
     * ImageViews for the towers
     */
    @FXML
    private ImageView t1Img, t2Img, t3Img, t4Img, t5Img;
    /**
     * Button to go to the next round
     */
    @FXML
    private Button nextButton;
    /**
     * GameEnv to hold the current instance
     */
    private final GameEnv gameEnv;

    /**
     * Constructor to assign the gameEnv to the current instance
     * @param gameEnv instance that controller will use
     */
    public RoundSummaryController(GameEnv gameEnv) {this.gameEnv = gameEnv;}

    /**
     * Initialise the labels and buttons of the screen and will disable next button if the player has lost or won
     */
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

    /**
     * Opens the inventory
     */
    @FXML
    public void openInventory() {
        this.gameEnv.openInventory();
    }

    /**
     * Opens the shop
     */
    @FXML
    public void openShop() {
        this.gameEnv.openShop();
    }

    /**
     * Takes the player to the next round and increases the cost multipliers
     */
    @FXML
    public void nextRound() {
        this.gameEnv.getDifficulty().incrementCostMultiplier();
        this.gameEnv.openRoundStyle();
    }

    /**
     * Takes the user to the game over screen
     * <p>
     *     If the user clicks it while they have not finished the game then this will set the result of the game to lost
     * </p>
     */
    @FXML
    public void finishGame() {
        if (this.gameEnv.getRoundNum() - 1 != this.gameEnv.getNumRounds()) {
            this.gameEnv.setResult(false);
        }
        this.gameEnv.showFinishedGame();
    }
}
