package seng201.team32.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import seng201.team32.models.GameEnv;


import java.io.IOException;

/**
 * Class to initialise all the other screens
 */
public class FXWrapper {
    /**
     * The pane that serves as a container for all the GUI
     */
    @FXML
    private Pane pane;
    /**
     * The stage instance that represents the main window of the application
     */
    private Stage stage;

    /**
     * Initialise the controller with a new GameEnv and all functions to opens other screens
     * @param stage the stage instance that will be initialised
     */
    public void init(Stage stage) {
        this.stage = stage;
        new GameEnv(this::launchStartScreen, this::launchSetupScreen, this::clearPane, this::launchPlayScreen, this::launchInventoryScreen, this::launchShopScreen, this::launchRoundSummaryScreen, this::launchRoundStyleScreen, this::launchGameOverScreen, this::openRandomEvent, this::openInstructions);
    }
    /**
     * Launches the setup screen for the game.
     * <p>This method loads the FXML layout for the setup screen, initializes the
     * {@code SetupController} with the provided {@code GameEnv} instance, and
     * displays the setup screen within the primary application window.</p>
     * @param gameEnv the game environment to be passed to the setup screen controller
     */
    public void launchSetupScreen(GameEnv gameEnv) {
        try {
            FXMLLoader setupLoader = new FXMLLoader(getClass().getResource("/fxml/setup_screen.fxml"));
            setupLoader.setControllerFactory(param -> new SetupController(gameEnv));
            Parent setupParent  = setupLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Game Setup | NBTD");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * This method gets all the children of the pane and removes them so that the screen clears
     */
    public void clearPane() {
        pane.getChildren().removeAll(pane.getChildren());
    }
    /**
     * Launches the start screen for the game
     * <p>This method loads the FXML layout for the start screen, initializes the
     *    {@code StartController} with the provided {@code GameEnv} instance, and
     *    displays the start screen within the primary application window.
     * </p>
     * @param gameEnv the game environment to be passed to the start controller
     */
    public void launchStartScreen(GameEnv gameEnv) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/start_screen.fxml"));
            mainScreenLoader.setControllerFactory(param -> new StartController(gameEnv));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Welcome to Resource Rush");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Launches the play screen for the game
     * <p>
     *     This method loads the FXML layout for the play screen, initializes the
     *     {@code PlayController}, and displays the play screen within the primary application window.
     * </p>
     * @param gameEnv the game environment to be passed to the play controller
     */
    public void launchPlayScreen(GameEnv gameEnv) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/play_new.fxml"));
            mainScreenLoader.setControllerFactory(param -> {
                try {
                    return new PlayController(gameEnv);
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }
            });
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Resource Rush");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Launches the inventory screen for the game
     * <p>
     *     This method loads the FXML layout for the inventory screen, initializes the
     *     {@code InventoryController}, and displays the inventory screen within the primary application window.
     * </p>
     * @param gameEnv the game environment to be passed to the inventory controller
     */
    public void launchInventoryScreen(GameEnv gameEnv) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/inventory.fxml"));
            mainScreenLoader.setControllerFactory(param -> new InventoryController(gameEnv));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Resource Rush - Inventory");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Launches the shop screen for the game
     * <p>
     *     This method loads the FXML layout for the shop screen, initializes the
     *     {@code ShopController}, and displays the shop screen within the primary application window.
     * </p>
     * @param gameEnv the game environment to be passed to the shop controller
     */
    public void launchShopScreen(GameEnv gameEnv) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/shop.fxml"));
            mainScreenLoader.setControllerFactory(param -> new ShopController(gameEnv));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Resource Rush - Inventory");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    /**
     * Launches the round summary screen
     * <p>
     *     This method loads the FXML layout for the round summary screen, initializes the {@code RoundSummaryController},
     *      and displays the round summary screen within the primary application window.
     * </p>
     * @param gameEnv the game environment to be passed to the round summary controller
     */
    private void launchRoundSummaryScreen(GameEnv gameEnv) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/round_summary.fxml"));
            mainScreenLoader.setControllerFactory(param -> new RoundSummaryController(gameEnv));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Resource Rush - Round Summary");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Launches the round style screen
     * <p>
     *     This method loads the FXML layout for the round style screen, initializes the {@code RoundStyleController},
     *     and displays the round style screen within the primary application window.
     * </p>
     * @param gameEnv the game environment to be passed to the round style controller
     */
    private void launchRoundStyleScreen(GameEnv gameEnv) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/round_style.fxml"));
            mainScreenLoader.setControllerFactory(param -> new RoundStyleController(gameEnv));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Resource Rush - Choose your round style!");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    /**
     * Launches the game over screen
     * <p>
     *     This method loads the FXML layout for the game over screen, initializes the {@code GameOverController},
     *     and displays the game over screen within the primary application window.
     * </p>
     * @param gameEnv the game environment to be passed to the game over controller
     */
    private void launchGameOverScreen(GameEnv gameEnv) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/game_over.fxml"));
            mainScreenLoader.setControllerFactory(param -> new GameOverController(gameEnv));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Resource Rush - Game Finished!");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    /**
     * Opens the random event screen
     * <p>
     *     This method loads the FXML layout for the random event screen, initializes the {@code RandomEventController},
     *     and displays the random event screen within the primary application window.
     * </p>
     * @param gameEnv the game environment to be passed to the random event controller
     */
    public void openRandomEvent(GameEnv gameEnv) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/random_event.fxml"));
            mainScreenLoader.setControllerFactory(param -> new RandomEventController(gameEnv));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Resource Rush - Random Event!");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Opens the instructions
     * <p>
     *     This method loads the FXML layout for the instructions screen, initializes the {@code InstructionsController},
     *     and displays the instructions screen within the primary application window.
     * </p>
     * @param gameEnv the game environment to be passed to the instructions controller
     */
    public void openInstructions(GameEnv gameEnv) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/instructions.fxml"));
            mainScreenLoader.setControllerFactory(param -> new InstructionsController(gameEnv));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Resource Rush - Instructions");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
