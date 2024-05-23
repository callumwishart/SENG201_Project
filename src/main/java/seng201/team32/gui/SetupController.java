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

/**
 * Controller for setup_screen.fxml
 */
public class SetupController {
    /**
     * Text field for the users name
     */
    @FXML
    private TextField nameField;
    /**
     * Radio buttons for the game difficulty
     */
    @FXML
    private RadioButton normalRadioBtn, hardRadioBtn;
    /**
     * Slider to select the number of rounds
     */
    @FXML
    private Slider roundSlider;
    /**
     * Buttons to select towers
     */
    @FXML
    private Button t1Btn, t2Btn, t3Btn, t4Btn, t5Btn, selectedT1Btn, selectedT2Btn, selectedT3Btn;
    /**
     * Labels to show tower data
     */
    @FXML
    private Label roundIndicatorLabel, selectedTowerLabel, towerResourceLabel, resourceAmountLabel, resourceValueLabel1, towerSpeedLabel;
    /**
     * Image view to show the selected tower
     */
    @FXML
    private ImageView selectedTowerImage;
    /**
     * Toggle group to hold radio buttons
     */
    @FXML
    private ToggleGroup toggleGroup = new ToggleGroup();
    /**
     * GameEnv to hold instance of the gameEnv
     */
    private final GameEnv gameEnv;
    /**
     * integer to hold selected tower index
     */
    private int selectedTowerIndex = -1;
    /**
     * List of tower for selected towers
     */
    private final Tower[] selectedTowers = new Tower[3];
    /**
     * String to hold difficulty text
     */
    private String difficultyText;

    /**
     * constructor for the controller which sets the gameEnv to the current instance of gameEnv
     * @param gameEnv the instance of GameEnv that the controller will use
     */
    public SetupController(GameEnv gameEnv) {this.gameEnv = gameEnv;}

    /**
     * used to update the tower stats by setting the label text
     * @param tower is the tower which the stats will be displayed of
     */
    private void updateStats(Tower tower) {
        selectedTowerLabel.setText("Selected Tower: " + tower.getName());
        towerResourceLabel.setText("Resource: " + tower.getResource().getResourceType());
        resourceAmountLabel.setText("Resource loading capacity: " + Integer.toString(tower.getResourceAmount()));
        resourceValueLabel1.setText("Resource Value (per unit): " + Integer.toString(tower.getResource().getResourceCoinValue()));
        towerSpeedLabel.setText("Loading Speed: " + Integer.toString(tower.getReloadSpeed()));
    }

    /**
     * Will initialise everything to be up-to-date and set event listeners for when you update things
     */
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

    /**
     * Will create difficulty based on the selected difficulty
     * @param input is the input difficulty
     * @return will return a new instance of difficulty
     */
    private Difficulty createDifficulty(String input) {
        if (input.equals("Hard")) {
            return new Difficulty(1.2,1.3);
        } else {
            return new Difficulty();
        }
    }

    /**
     * Starts the game and checks inputs
     * <p>
     *     This will check all the input and create alerts if any of the inputs have not been entered correctly
     * </p>
     */
    @FXML
    private void startGame() {

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
            if (nameField.getText().isEmpty() || !this.gameEnv.getPlayer().checkName(nameField.getText())) {
                throw new NameCharException();
            }
            this.gameEnv.getPlayer().setName(nameField.getText());
        } catch (Exception e) {
            this.gameEnv.showAlert("Incorrect name", "Your name must be between 3-15 letters, and must not include special characters.");
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

    /**
     * Will go back to the start screen
     */
    @FXML
    private void goBack() {
        this.gameEnv.clearScreen.run();
        this.gameEnv.launchStartScreen();
    }
}
