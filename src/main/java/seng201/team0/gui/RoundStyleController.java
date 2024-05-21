package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import seng201.team0.models.GameEnv;

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
        this.gameEnv.getDifficulty().updateDifficulty(difficultyText);
        this.gameEnv.startRound();
    }
}
