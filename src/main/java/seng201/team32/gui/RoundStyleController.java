package seng201.team32.gui;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import seng201.team32.models.GameEnv;

public class RoundStyleController {
    @FXML
    private RadioButton safeRadioBtn, steadyRadioBtn, riskyRadioBtn;
    @FXML
    private ToggleGroup toggleGroup = new ToggleGroup();
    private String difficultyText;
    private GameEnv gameEnv;
    public RoundStyleController(GameEnv gameEnv) {
        this.gameEnv = gameEnv;
    }
    public void initialize() {
        safeRadioBtn.setToggleGroup(toggleGroup);
        steadyRadioBtn.setToggleGroup(toggleGroup);
        riskyRadioBtn.setToggleGroup(toggleGroup);
        toggleGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            RadioButton selectedRadioButton = (RadioButton) newToggle;
            difficultyText = selectedRadioButton.getText();
        });
    }

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
