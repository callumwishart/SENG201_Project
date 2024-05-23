package seng201.team32.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import seng201.team32.exceptions.NameCharException;
import seng201.team32.models.Difficulty;
import seng201.team32.models.GameEnv;
import seng201.team32.models.towers.Tower;

import java.util.ArrayList;
import java.util.List;


public class SetupController {
    @FXML
    private TextField nameField;
    @FXML
    private RadioButton normalRadioBtn, hardRadioBtn;
    @FXML
    private Slider roundSlider;
    @FXML
    private Button t1Btn, t2Btn, t3Btn, t4Btn, t5Btn, selectedT1Btn, selectedT2Btn, selectedT3Btn;
    @FXML
    private Label roundIndicatorLabel, selectedTowerLabel, towerResourceLabel, resourceAmountLabel, resourceValueLabel1, towerSpeedLabel;
    @FXML
    private ImageView selectedTowerImage;
    @FXML
    private ToggleGroup toggleGroup = new ToggleGroup();
    private GameEnv gameEnv;
    private int selectedTowerIndex = -1;
    private final Tower[] selectedTowers = new Tower[3];
    private ArrayList<Tower> finalTowers = new ArrayList<>();
    private String difficultyText;
    public SetupController(GameEnv gameEnv) {this.gameEnv = gameEnv;}
    private void updateStats(Tower tower) {
        selectedTowerLabel.setText("Selected Tower: " + tower.getName());
        towerResourceLabel.setText("Resource: " + tower.getResource().getResourceType());
        resourceAmountLabel.setText("Resource loading capacity: " + Integer.toString(tower.getResourceAmount()));
        resourceValueLabel1.setText("Resource Value (per unit): " + Integer.toString(tower.getResource().getResourceCoinValue()));
        towerSpeedLabel.setText("Loading Speed: " + Integer.toString(tower.getReloadSpeed()));
    }
    public void initialize() {
        List<Button> towerButtons = List.of(t1Btn,t2Btn,t3Btn,t4Btn,t5Btn);
        List<Button> selectedTowerButtons = List.of(selectedT1Btn, selectedT2Btn, selectedT3Btn);
        for (int i=0; i < towerButtons.size(); i++) {
            int finalI = i;
            towerButtons.get(i).setOnAction(event -> {
                updateStats(gameEnv.getPossibleTowers().get(finalI));
                selectedTowerIndex = finalI;
                ShopController.setButtonStyle(towerButtons, finalI, gameEnv, selectedTowerImage);
            });
        }
        for (int i = 0; i <selectedTowerButtons.size(); i++ ) {
            int finalI = i;
            selectedTowerButtons.get(i).setOnAction(event -> {
                if (selectedTowerIndex != -1) {
                    selectedTowerButtons.get(finalI).setText(gameEnv.getPossibleTowers().get(selectedTowerIndex).getName());
                    selectedTowers[finalI] = gameEnv.getPossibleTowers().get(selectedTowerIndex);

                }
            });
        }
        roundIndicatorLabel.setText("5 Rounds");
        roundSlider.setValue(5.0);
        roundSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int value = newValue.intValue();
            roundIndicatorLabel.setText(String.valueOf(value) + " Rounds");
        });
        normalRadioBtn.setToggleGroup(toggleGroup);
        hardRadioBtn.setToggleGroup(toggleGroup);
        toggleGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            RadioButton selectedRadioButton = (RadioButton) newToggle;
            if (selectedRadioButton.getText().equals("Hard")) {
                difficultyText = "Hard";
            } else {
                difficultyText = "Normal";
            }
        });
    }
    private Difficulty createDifficulty(String input) {
        if (input.equals("Hard")) {
            return new Difficulty(1.2,1.3);
        } else {
            return new Difficulty();
        }
    }
    @FXML
    private void startGame() throws InterruptedException, NameCharException {

        ArrayList<Tower> finalTowers = new ArrayList<>(); // This is so that new instances of each tower is set as active towers rather than the same one twice
        for (Tower tower: selectedTowers) {
            try {
                Tower newTower = tower.getClass().getDeclaredConstructor().newInstance();
                finalTowers.add(newTower);
            } catch (Exception e) {
                this.gameEnv.showAlert("Towers not Selected", "Please ensure you have selected all towers");
                return;
            }

        }
        try {
            if (nameField.getText().isEmpty()) {
                throw new NameCharException("You have not entered your name");
            }
            this.gameEnv.getPlayer().setName(nameField.getText());
        } catch (Exception e) {
            this.gameEnv.showAlert(e.getMessage(), "Please input your name");
            return;
        }
        try {
            if (difficultyText == null) {
                throw new RuntimeException("You have not selected a difficulty");
            }
            this.gameEnv.setDifficulty(createDifficulty(difficultyText));
        } catch (Exception e) {
            this.gameEnv.showAlert(e.getMessage(), "Please select a difficulty");
            return;
        }

        this.gameEnv.getPlayer().getInventory().setActiveTowers(finalTowers);
        this.gameEnv.setNumRounds(roundSlider.valueProperty().intValue());
        this.gameEnv.openRoundStyle();
    }
    @FXML
    private void goBack() {
        this.gameEnv.clearScreen.run();
        this.gameEnv.launchStartScreen();
    }
}
