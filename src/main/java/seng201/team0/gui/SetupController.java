package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import seng201.team0.models.GameEnv;
import seng201.team0.models.Towers.Tower;

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
    private GameEnv gameEnv;
    private int selectedTowerIndex = -1;
    private final Tower[] selectedTowers = new Tower[3];
    public SetupController(GameEnv gameEnv) {this.gameEnv = gameEnv;}
    private void updateStats(Tower tower) {
        selectedTowerLabel.setText("Selected Tower: " + tower.getName());
        towerResourceLabel.setText("Resource: " + tower.getResourceType().getResourceType());
        resourceAmountLabel.setText("Resource loading capacity: " + Integer.toString(tower.getResourceAmount()));
        resourceValueLabel1.setText("Resource Value (per unit): " + "Need to implement");
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
                towerButtons.forEach(button -> {
                    if (button == towerButtons.get(finalI)) {
                        button.setStyle("-fx-background-color: #b3b3b3; -fx-backround-radius: 5;");
                    } else {
                        button.setStyle("");
                    }
                });
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
    }
    @FXML
    public void startGame() {
        List<Tower> finalTowers = new ArrayList<>(); // This is so that new instances of each tower is set as active towers rather than the same one twice
        for (Tower tower: selectedTowers) {
            try {
                Tower newTower = tower.getClass().getDeclaredConstructor().newInstance();
                finalTowers.add(newTower);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.gameEnv.getPlayer().setName(nameField.getText());
        this.gameEnv.setNumRounds(roundSlider.valueProperty().intValue());
        this.gameEnv.getPlayer().getInventory().setActiveTowers(finalTowers);
    }

}
