package seng201.team0.gui;

import seng201.team0.models.GameEnv;

public class InstructionsController {
    private GameEnv gameEnv;
    public InstructionsController (GameEnv gameEnv) {this.gameEnv = gameEnv;}
    public void goBack() {
        this.gameEnv.clearScreen.run();
        this.gameEnv.launchStartScreen();
    }
}
