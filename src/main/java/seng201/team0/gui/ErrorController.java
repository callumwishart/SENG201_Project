package seng201.team0.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seng201.team0.models.GameEnv;

public class ErrorController {
    GameEnv gameEnv;
    Exception currException;
    @FXML
    private Label errorMessage;
    public ErrorController(GameEnv gameEnv) {
        this.gameEnv = gameEnv;
    }
    public void initialize() {
        currException = gameEnv.getCurrException();
        errorMessage.setText(currException.getMessage());
    }
    @FXML
    private void closeWindow() {
        Platform.exit();
    }

}
