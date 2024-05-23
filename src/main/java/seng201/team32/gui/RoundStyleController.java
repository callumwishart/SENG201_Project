package seng201.team32.gui;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import seng201.team32.models.GameEnv;

/**
 * Controller for round_style.fxml
 */
public class RoundStyleController {
    /**
     * Initialises the radio buttons
     */
    @FXML
    private RadioButton safeRadioBtn, steadyRadioBtn, riskyRadioBtn;
    /**
     * Initialises the toggle group
     */
    @FXML
    private ToggleGroup toggleGroup = new ToggleGroup();
    /**
     * String to hold the difficulty text
     */
    private String difficultyText;
    /**
     * GameEnv to hold the instance of gameEnv
     */
    private final GameEnv gameEnv;

    /**
     * Constructor to set the gameEnv to the current instance of gameEnv
     * @param gameEnv is the instance of gameEnv that this will be initialised with
     */
    public RoundStyleController(GameEnv gameEnv) {
        this.gameEnv = gameEnv;
    }

    /**
     * Initialises all the radio buttons and adds a listener to the toggle group to update the difficulty text
     */
    public void initialize() {
        safeRadioBtn.setToggleGroup(toggleGroup);
        steadyRadioBtn.setToggleGroup(toggleGroup);
        riskyRadioBtn.setToggleGroup(toggleGroup);
        toggleGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            RadioButton selectedRadioButton = (RadioButton) newToggle;
            difficultyText = selectedRadioButton.getText();
        });
    }

    /**
     * Button to start the round
     * <p>Will throw an alert if the player has not selected a round style then will start the round</p>
     */
    public void startRound() {
        try {
            if (difficultyText == null) {
                throw new RuntimeException("You have not selected a round style");
            }
            this.gameEnv.getDifficulty().updateDifficulty(difficultyText);
        } catch (Exception e) {
            this.gameEnv.showAlert(e.getMessage(), "Please select a round difficulty!");
            return;
        }

        this.gameEnv.startRound();
    }
}
