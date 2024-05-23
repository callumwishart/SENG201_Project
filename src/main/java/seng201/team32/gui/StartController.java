package seng201.team32.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import seng201.team32.models.GameEnv;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller of start_screen.fxml
 */
public class StartController{
    /**
     * GameEnv to hold the instance of the gameEnv
     */
    private final GameEnv gameEnv;

    /**
     * Constructor of the start controller
     * @param gameEnv the instance of GameEnv that will be used
     */
    public StartController(GameEnv gameEnv) {
        this.gameEnv = gameEnv;
    }

    /**
     * Start button game which will close the start screen and open the setup screen
     */
    @FXML
    private void startGame() {
        gameEnv.closeStartScreen();
    }

    /**
     * Open instruction button to show instructions
     */
    @FXML void openInstructions() {gameEnv.openInstruction();}
}
