package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import seng201.team0.models.GameEnv;
import seng201.team0.models.gameplay.Round;

public class RoundSummaryController {
    @FXML
    private Label playerLabel, winLabel, coinsLabel, pointsLabel, roundLabel;
    @FXML
    private ImageView t1Img, t2Img, t3Img, t4Img, t5Img;
    private GameEnv gameEnv;
    public RoundSummaryController(GameEnv gameEnv) {this.gameEnv = gameEnv;}

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
        // Yet to implement
    }
}
