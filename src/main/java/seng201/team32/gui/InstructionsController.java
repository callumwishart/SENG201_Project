package seng201.team32.gui;

import seng201.team32.models.GameEnv;

/**
 * Instructions controller to control instructions.fxml
 */
public class InstructionsController {
    /**
     * Attribute to hold instance of the gameEnv
     */
    private GameEnv gameEnv;

    /**
     * Constructor to assign this gameEnv to the current instance of the gameEnv
     * @param gameEnv
     */
    public InstructionsController (GameEnv gameEnv) {this.gameEnv = gameEnv;}

    /**
     * Back button to take user back to start screen
     */
    public void goBack() {
        this.gameEnv.clearScreen.run();
        this.gameEnv.launchStartScreen();
    }
}
