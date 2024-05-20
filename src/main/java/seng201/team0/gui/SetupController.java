package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import seng201.team0.exceptions.NameCharException;
import seng201.team0.models.Difficulty;
import seng201.team0.models.GameEnv;
import seng201.team0.models.towers.Tower;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
                towerButtons.forEach(button -> {
                    if (button == towerButtons.get(finalI)) {
                        button.setStyle("-fx-background-color: #b3b3b3; -fx-backround-radius: 5;");
                        String imagePath = gameEnv.getPossibleTowers().get(finalI).getImagePath();
                        FileInputStream inputStream;
                        try {
                            inputStream = new FileInputStream(imagePath);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        Image image = new Image(inputStream);

                        selectedTowerImage.setImage(image);

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
        roundSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int value = newValue.intValue();
            roundIndicatorLabel.setText(String.valueOf(value) + " Rounds");
        });
        normalRadioBtn.setToggleGroup(toggleGroup);
        hardRadioBtn.setToggleGroup(toggleGroup);
        toggleGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            RadioButton selectedRadioButton = (RadioButton) newToggle;
            if (selectedRadioButton.getText() == "Hard" ) {
                difficultyText = "Hard";
            } else {
                difficultyText = "Normal";
            }
        });
    }
    public Difficulty createDifficulty(String input) {
        if (input == "hard") {
            return new Difficulty(1,2,3);
        } else {
            return new Difficulty(1,1,1);
        }
    }
    @FXML
    public void startGame() throws InterruptedException, NameCharException {
        ArrayList<Tower> finalTowers = new ArrayList<>(); // This is so that new instances of each tower is set as active towers rather than the same one twice
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
        this.gameEnv.setDifficulty(createDifficulty(difficultyText));
        this.gameEnv.closeSetupScreen();
    }

}
