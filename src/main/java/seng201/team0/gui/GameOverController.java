package seng201.team0.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seng201.team0.models.GameEnv;

public class GameOverController {
    @FXML
    private Label loseWinLabel, finalScoreLabel, roundCountLabel, thanksLabel;
    GameEnv gameEnv;
    public GameOverController(GameEnv gameEnv) {
        this.gameEnv = gameEnv;
    }
    public void initialize() {
        if (gameEnv.getResult()) {
            loseWinLabel.setText("You won!");
        } else {
            loseWinLabel.setText("You lost!");
        }
        finalScoreLabel.setText("Final Score: " + gameEnv.getInventoryService().getPoints() + " points");
        roundCountLabel.setText("Rounds: " + (gameEnv.getRoundNum() - 1) + "/" + gameEnv.getNumRounds());
        thanksLabel.setText("Thanks for playing " + gameEnv.getPlayer().getName() + "!");
    }

    @FXML
    private void exit() {
        Platform.exit();
    }
    @FXML
    private void playAgain() {
        this.gameEnv.setCurrentRoundNum(1);
        this.gameEnv.getInventoryService().resetCoinsAndPoints();
        gameEnv.closeStartScreen();
    }
}
