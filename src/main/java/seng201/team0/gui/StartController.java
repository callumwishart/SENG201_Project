package seng201.team0.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import seng201.team0.models.GameEnv;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController extends Application{
    private GameEnv gameEnv;
    @FXML
    private Button startBtn;
    public StartController(GameEnv gameEnv) {
        this.gameEnv = gameEnv;
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader baseLoader = new FXMLLoader(getClass().getResource("resources/fxml/setup_screen.fxml"));

        Parent root = baseLoader.load();

    }
    @FXML
    private void startGame() {
        gameEnv.closeStartScreen();
    }
    @FXML void openInstructions() {gameEnv.openInstruction();}
}
