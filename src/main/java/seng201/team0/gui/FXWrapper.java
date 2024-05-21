package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import seng201.team0.models.GameEnv;


import java.io.IOException;

public class FXWrapper {
    @FXML
    private Pane pane;

    private Stage stage;


    public void init(Stage stage) {
        this.stage = stage;
        new GameEnv(this::launchStartScreen, this::launchSetupScreen, this::clearPane, this::launchPlayScreen, this::launchInventoryScreen, this::launchShopScreen, this::launchRoundSummaryScreen);
    }

    public void launchSetupScreen(GameEnv gameEnv) {
        try {
            FXMLLoader setupLoader = new FXMLLoader(getClass().getResource("/fxml/setup_screen.fxml"));
            // provide a custom Controller with parameters
            setupLoader.setControllerFactory(param -> new SetupController(gameEnv));
            Parent setupParent  = setupLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Game Setup | NBTD");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearPane() {
        pane.getChildren().removeAll(pane.getChildren());
    }

    public void launchStartScreen(GameEnv gameEnv) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/start_screen.fxml"));
            mainScreenLoader.setControllerFactory(param -> new StartController(gameEnv));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Welcome to NBTD");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void launchPlayScreen(GameEnv gameEnv) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/play.fxml"));
            mainScreenLoader.setControllerFactory(param -> new PlayController(gameEnv));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Play");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void launchInventoryScreen(GameEnv gameEnv) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/inventory.fxml"));
            mainScreenLoader.setControllerFactory(param -> new InventoryController(gameEnv));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Inventory");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void launchShopScreen(GameEnv gameEnv) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/shop.fxml"));
            mainScreenLoader.setControllerFactory(param -> new ShopController(gameEnv));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Inventory");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    private void launchRoundSummaryScreen(GameEnv gameEnv) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/round_summary.fxml"));
            mainScreenLoader.setControllerFactory(param -> new RoundSummaryController(gameEnv));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Round Summary");
        } catch (IOException e){
            e.printStackTrace();
        }
    }


}
