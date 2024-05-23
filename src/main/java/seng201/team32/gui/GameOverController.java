package seng201.team32.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seng201.team32.models.GameEnv;

/**
 * Controller for game_over.fxml
 */
public class GameOverController {
    /**
     * Labels attributes for everything on screen
     */
    @FXML
    private Label loseWinLabel, finalScoreLabel, roundCountLabel, thanksLabel;
    /**
     * GameEnv attribute to hold the gameEnv instance
     */
    GameEnv gameEnv;

    /**
     * Constructor of the GameOverController which sets the gameEnv
     * @param gameEnv is the instance of GameEnv that the controller will use
     */
    public GameOverController(GameEnv gameEnv) {
        this.gameEnv = gameEnv;
    }

    /**
     * Initialises the controller with the relevant text
     * <p>
     *     Sets the result text, final score text, round count text and thank you label with player name
     * </p>
     */
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

    /**
     * FXML exit button handler
     * <p>
     *     This is called when the exit button is clicked and will cause the app to close
     *     by using {@code Platform.exit()}
     * </p>
     */
    @FXML
    private void exit() {
        Platform.exit();
    }

    /**
     * FXML play again button handler
     * <p>
     *     This is called when the play again button is clicked and will cause the
     *     gameEnv to start a new game using the {@code startNewGame()} method
     * </p>
     */
    @FXML
    private void playAgain() {
        this.gameEnv.startNewGame();
    }
}
