package seng201.team32.gui;

import seng201.team32.models.GameEnv;

public class InstructionsController {
    private GameEnv gameEnv;
    public InstructionsController (GameEnv gameEnv) {this.gameEnv = gameEnv;}
    public void goBack() {
        this.gameEnv.clearScreen.run();
        this.gameEnv.launchStartScreen();
    }
}
