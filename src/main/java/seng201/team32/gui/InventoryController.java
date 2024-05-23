package seng201.team32.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import seng201.team32.exceptions.*;
import seng201.team32.models.GameEnv;
import seng201.team32.models.towers.Tower;
import seng201.team32.models.upgrades.Upgrade;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Controller for inventory.fxml file
 */
public class InventoryController {
    /**
     * These labels are used to keep the user informed of what is happening in game
     */
    @FXML
    private Label coinsLabel, pointsLabel, towerNameLabel, towerStatusLabel, towerLevelLabel, towerResourceLabel,
            pointsNeededLabel, sellCostLabel, repairCostLabel, towerCapacityLabel, towerSpeedLabel,
            selectedUpgradeLabel, upgradeDescLabel, resourceValueLabel;
    /**
     * These buttons allow the user to interact with the game
     */
    @FXML
    private Button t1Btn, t2Btn, t3Btn, t4Btn, t5Btn, reservedT1Btn, reservedT2Btn,
            reservedT3Btn, reservedT4Btn, reservedT5Btn, upgrade1Btn, upgrade2Btn, upgrade3Btn, c1Btn, c2Btn, c3Btn;
    /**
     * Image to show which tower is currently selected
     */
    @FXML
    private ImageView selectedTowerImg;
    /**
     * instance of GameEnv that the controller can use
     */
    private final GameEnv gameEnv;
    /**
     * Sets the selected towers to null
     */
    private Tower currentActiveTower, currentReserveTower, currentTower = null;
    /**
     * Sets the selected upgrade to null
     */
    private Upgrade currentUpgrade = null;

    /**
     * constructor of inventory controller
     * @param gameEnv instance of gameEnv that inventory controller will use
     */
    public InventoryController(GameEnv gameEnv) {this.gameEnv = gameEnv;}

    /**
     * Initialises the controller with the buttons and their actions and the text so it is up-to-date.
     */
    public void initialize() {
        List<Button> activeTowerButtons = List.of(t1Btn, t2Btn, t3Btn, t4Btn, t5Btn);
        List<Button> reservedTowerButtons = List.of(reservedT1Btn, reservedT2Btn, reservedT3Btn, reservedT4Btn, reservedT5Btn);
        List<Button> consumableButtons = List.of(c1Btn, c2Btn, c3Btn);
        List<Button> upgradeButtons = List.of(upgrade1Btn, upgrade2Btn, upgrade3Btn);

        for (int i=0; i < upgradeButtons.size(); i++) {
            int finalI = i;
            upgradeButtons.get(i).setDisable(true);
            upgradeButtons.get(i).setOnAction(event -> {
                updateUpgradeStats(gameEnv.getInventoryService().getUpgrades().get(finalI));
                upgradeButtons.forEach(button -> {
                    if (button == upgradeButtons.get(finalI)) {
                        button.setStyle("-fx-background-color: #b3b3b3; -fx-backround-radius: 5;");
                        currentUpgrade = gameEnv.getInventoryService().getUpgrades().get(finalI);
                    } else {
                        button.setStyle("");
                    }
                });
            });
        }

        // This for loop initialises the buttons to be disabled and also sets the action event for them;
        for (int i=0; i < activeTowerButtons.size(); i++) {
            int finalI = i;
            activeTowerButtons.get(i).setDisable(true);
            reservedTowerButtons.get(i).setDisable(true);
            activeTowerButtons.get(i).setOnAction(event -> {
                updateTowerStats(gameEnv.getInventoryService().getActiveTowers().get(finalI));
                activeTowerButtons.forEach(button -> {
                    if (button == activeTowerButtons.get(finalI)) {
                        button.setStyle("-fx-background-color: #b3b3b3; -fx-backround-radius: 5;");
                        currentActiveTower = gameEnv.getInventoryService().getActiveTowers().get(finalI);
                        currentTower = currentActiveTower;
                        String imagePath = gameEnv.getInventoryService().getActiveTowers().get(finalI).getImagePath();
                        try (InputStream inputStream = getClass().getResourceAsStream(imagePath)) {
                            if (inputStream == null) {
                                throw new RuntimeException("Resource not found: " + imagePath);
                            }
                            Image image = new Image(inputStream);
                            selectedTowerImg.setImage(image);
                        } catch (IOException e) {
                            throw new RuntimeException("Failed to load image: " + imagePath, e);
                        }

                    } else {
                        button.setStyle("");
                    }
                });

            });
            reservedTowerButtons.get(i).setOnAction(event -> {
                updateTowerStats(gameEnv.getInventoryService().getStockpiledTowers().get(finalI));
                reservedTowerButtons.forEach(button -> {
                    if (button == reservedTowerButtons.get(finalI)) {
                        currentReserveTower = gameEnv.getInventoryService().getStockpiledTowers().get(finalI);
                        currentTower = currentReserveTower;
                        String imagePath = gameEnv.getInventoryService().getStockpiledTowers().get(finalI).getImagePath();
                        try (InputStream inputStream = getClass().getResourceAsStream(imagePath)) {
                            if (inputStream == null) {
                                throw new RuntimeException("Resource not found: " + imagePath);
                            }
                            Image image = new Image(inputStream);
                            selectedTowerImg.setImage(image);
                        } catch (IOException e) {
                            throw new RuntimeException("Failed to load image: " + imagePath, e);
                        }
                    } else {
                        button.setStyle("");
                    }
                });

            });
        }
        // Set the text of the activeTower Buttons to be correct
        for (int i = 0; i < this.gameEnv.getInventoryService().getActiveTowers().size(); i++) {
            activeTowerButtons.get(i).setText(this.gameEnv.getInventoryService().getActiveTowers().get(i).getName());
            activeTowerButtons.get(i).setDisable(false);
        }
        // Set the text of the reserve towers to be correct
        for (int i = 0; i < this.gameEnv.getInventoryService().getStockpiledTowers().size(); i++) {
            reservedTowerButtons.get(i).setText(this.gameEnv.getInventoryService().getStockpiledTowers().get(i).getName());
            reservedTowerButtons.get(i).setDisable(false);
        }
        // Set the text of the upgrades
                for (int i = 0; i < this.gameEnv.getInventoryService().getUpgrades().size(); i++) {
            upgradeButtons.get(i).setText(this.gameEnv.getInventoryService().getUpgrades().get(i).getName());
            upgradeButtons.get(i).setDisable(false);
        }
        // Set the text of the consumables
        for (int i = 0; i < this.gameEnv.getInventoryService().getConsumables().size(); i++) {
            consumableButtons.get(i).setText(this.gameEnv.getInventoryService().getConsumables().get(i).getName());
            consumableButtons.get(i).setDisable(false);
        }
        coinsLabel.setText("Coins: " + this.gameEnv.getInventoryService().getCoins());
        pointsLabel.setText("Points: " + this.gameEnv.getInventoryService().getPoints());
    }

    /**
     * updates the upgrade stats to be what has been selected
     * @param upgrade the upgrade which will have its stats displayed
     */
    private void updateUpgradeStats(Upgrade upgrade) {
        selectedUpgradeLabel.setText("Selected Upgrade: " + upgrade.getName());
        upgradeDescLabel.setText("Description: " + upgrade.getDescription());
    }

    /**
     * updates the upgrades stats to show that no upgrade has been selected
     */
    private void updateUpgradeStats() {
        selectedUpgradeLabel.setText("Selected Upgrade: ");
        upgradeDescLabel.setText("Description: ");
    }

    /**
     * Gets the status of the tower and returns its status
     * @param tower the tower that will have its status checked
     * @return the status of the tower
     */
    public String getStatus(Tower tower) {
        if (tower.isBroken()) {
            return "Broken";
        } else {
            return "OK";
        }
    }

    /**
     * Updates the tower stat labels with the values of the selected tower
     * @param tower the tower which will have its stats displayed
     */
    private void updateTowerStats(Tower tower) {
        towerNameLabel.setText("Tower Name: " + tower.getName());
        towerStatusLabel.setText("Status: " + getStatus(tower));
        towerLevelLabel.setText("Level: " + tower.getLevel());
        towerResourceLabel.setText("Resource: " + tower.getResource().getResourceType());
        resourceValueLabel.setText("Resource Value: " + tower.getResource().getResourceCoinValue());
        pointsNeededLabel.setText("Points needed to upgrade: " + tower.getUpgradePointLimit());
        sellCostLabel.setText("Sell cost: " + tower.getSellCost());
        repairCostLabel.setText("Repair Cost: " + tower.getRepairCost());
        towerSpeedLabel.setText("Tower Speed: " + tower.getReloadSpeed());
        towerCapacityLabel.setText("Resource Capacity: " + tower.getResourceAmount());
        String imagePath = tower.getImagePath();
        try (InputStream inputStream = getClass().getResourceAsStream(imagePath)) {
            if (inputStream == null) {
                throw new RuntimeException("Resource not found: " + imagePath);
            }
            Image image = new Image(inputStream);
            selectedTowerImg.setImage(image);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load image: " + imagePath, e);
        }
    }

    /**
     * Updates the tower stats to show that no tower is selected by resetting labels
     */
    private void updateTowerStats() {
        towerNameLabel.setText("Tower Name: ");
        towerStatusLabel.setText("Status: ");
        towerLevelLabel.setText("Level: ");
        towerResourceLabel.setText("Resource: ");
        resourceValueLabel.setText("Resource value: ");
        pointsNeededLabel.setText("Points needed to upgrade: ");
        sellCostLabel.setText("Sell cost: ");
        repairCostLabel.setText("Repair Cost: ");
        towerSpeedLabel.setText("Tower Speed: ");
        towerCapacityLabel.setText("Resource Capacity: ");
    }

    /**
     * resets all the buttons so that they do not look like they are active
     */
    private void resetButtons() {
        List<Button> activeTowerButtons = List.of(t1Btn, t2Btn, t3Btn, t4Btn, t5Btn);
        List<Button> reservedTowerButtons = List.of(reservedT1Btn, reservedT2Btn, reservedT3Btn, reservedT4Btn, reservedT5Btn);
        List<Button> consumableButtons = List.of(c1Btn, c2Btn, c3Btn);
        List<Button> upgradeButtons = List.of(upgrade1Btn, upgrade2Btn, upgrade3Btn);
        ShopController.resetButtonStyle(activeTowerButtons, reservedTowerButtons, consumableButtons);
        for (int i = 0; i < upgradeButtons.size(); i++) {
            upgradeButtons.get(i).setStyle("");
        }
    }

    /**
     * Resets the coins to show that they are up-to-date
     */
    public void resetPointsAndCoins() {
        coinsLabel.setText("Coins: " + this.gameEnv.getInventoryService().getCoins());
        pointsLabel.setText("Points: " + this.gameEnv.getInventoryService().getPoints());
    }

    /**
     * Resets the screen after something has happened so that nothing is selected and the user can tell
     * that their action took place
     */
    public void resetScreen() {
        resetButtons();
        updateTowerStats();
        updateUpgradeStats();
        resetPointsAndCoins();
        currentReserveTower = null;
        currentActiveTower = null;
        currentTower = null;
        currentUpgrade = null;
        selectedTowerImg.setImage(null);
    }

    /**
     * This is called when the swap button is called when the swap button is clicked
     * <p>
     *     This will try all the different types of swapping the towers and will create an alert if nothing is selected
     *     It will then reset the screen so that everything is up to date
     * </p>
     * @throws TowerInventoryFullException is thrown if the tower inventory is full
     */
    @FXML
    public void swapTowers() throws TowerInventoryFullException {
        try {
            if (currentActiveTower != null && currentReserveTower != null) {
                this.gameEnv.getInventoryService().swapTowers(currentActiveTower, currentReserveTower);
                resetScreen();
            } else if (currentActiveTower != null) {
                this.gameEnv.getInventoryService().swapTowers(currentActiveTower, true);
                resetScreen();
            } else if (currentReserveTower != null) {
                this.gameEnv.getInventoryService().swapTowers(currentReserveTower, false);
                resetScreen();
            } else if (currentActiveTower == null && currentReserveTower == null) {
                throw new NoTowerSelectedException();
            }
        } catch (NoTowerSelectedException e) {
            this.gameEnv.showAlert("No Tower Selected", "Selected a tower to continue");
        }

        // Set the text of the activeTower Buttons to be correct
        List<Button> activeTowerButtons = List.of(t1Btn, t2Btn, t3Btn, t4Btn, t5Btn);
        List<Button> reservedTowerButtons = List.of(reservedT1Btn, reservedT2Btn, reservedT3Btn, reservedT4Btn, reservedT5Btn);

        for (int i =0; i < activeTowerButtons.size(); i++) {
            activeTowerButtons.get(i).setDisable(true);
            activeTowerButtons.get(i).setText("None");
            activeTowerButtons.get(i).setStyle("");
            reservedTowerButtons.get(i).setDisable(true);
            reservedTowerButtons.get(i).setText("None");
            reservedTowerButtons.get(i).setStyle("");
        }

        for (int i = 0; i < this.gameEnv.getInventoryService().getActiveTowers().size(); i++) {
            activeTowerButtons.get(i).setText(this.gameEnv.getInventoryService().getActiveTowers().get(i).getName());
            activeTowerButtons.get(i).setDisable(false);
        }
        // Set the text of the reserve towers to be correct
        for (int i = 0; i < this.gameEnv.getInventoryService().getStockpiledTowers().size(); i++) {
            reservedTowerButtons.get(i).setText(this.gameEnv.getInventoryService().getStockpiledTowers().get(i).getName());
            reservedTowerButtons.get(i).setDisable(false);
        }
    }

    /**
     * Repairs the selected tower
     * <p>
     *     Checks if the tower is broken or if there is no tower selected and if so alerts the user. It then tries to
     *     repair the tower, if the player does not have enough coins then an alert will be created. It will then update
     *     the tower stats so that the user can tell that it has been repaired
     * </p>
     */
    @FXML
    public void repairTower() {
        Tower tower = currentTower;

        try {
            if (currentTower == null) {
                throw new NoTowerSelectedException();
            }
            if (!currentTower.isBroken()) {
                throw new IllegalArgumentException();
            }
            this.gameEnv.getInventoryService().repairTower(currentTower);
            tower.resetUsed();
            resetScreen();
        } catch (NoTowerSelectedException e) {
            this.gameEnv.showAlert("No tower selected", "Select a tower and try again");
            return;
        } catch (IllegalArgumentException e) {
            this.gameEnv.showAlert("Tower not Broken", "You cannot repair this tower as it is not broken");
        } catch (Exception e) {
            this.gameEnv.showAlert("Not enough money!", "You don't have enough money to buy this!");
            return;
        }
        assert tower != null;
        updateTowerStats(tower);
    }

    /**
     * Sells the selected tower
     * <p>
     *     Checks if a tower is found and creates alerts then sells the tower and resets the screen so that all
     *     info is correct
     * </p>
     * @throws NegativeAdditionException if the sell value is negative
     */
    @FXML
    public void sellTower() throws NegativeAdditionException {
        try {
            if (currentTower == null) {
                throw new NoTowerSelectedException();
            }
            this.gameEnv.getInventoryService().sellTower(currentTower);
            resetScreen();
        } catch (TowerNotFoundException e) {
            this.gameEnv.showAlert("Error", "Something went wrong here, let us try again");
            return;
        } catch (NoTowerSelectedException e) {
            this.gameEnv.showAlert("No tower Selected", "Please select a tower and try again");
            return;
        }
        List<Button> activeTowerButtons = List.of(t1Btn, t2Btn, t3Btn, t4Btn, t5Btn);
        List<Button> reservedTowerButtons = List.of(reservedT1Btn, reservedT2Btn, reservedT3Btn, reservedT4Btn, reservedT5Btn);

        for (int i =0; i < activeTowerButtons.size(); i++) {
            activeTowerButtons.get(i).setDisable(true);
            activeTowerButtons.get(i).setText("None");
            activeTowerButtons.get(i).setStyle("");
            reservedTowerButtons.get(i).setDisable(true);
            reservedTowerButtons.get(i).setText("None");
            reservedTowerButtons.get(i).setStyle("");
        }

        for (int i = 0; i < this.gameEnv.getInventoryService().getActiveTowers().size(); i++) {
            int finalI = i;
            activeTowerButtons.get(finalI).setText(this.gameEnv.getInventoryService().getActiveTowers().get(finalI).getName());
            activeTowerButtons.get(finalI).setDisable(false);
        }
        // Set the text of the reserve towers to be correct
        for (int i = 0; i < this.gameEnv.getInventoryService().getStockpiledTowers().size(); i++) {
            int finalI = i;
            reservedTowerButtons.get(finalI).setText(this.gameEnv.getInventoryService().getStockpiledTowers().get(finalI).getName());
            reservedTowerButtons.get(finalI).setDisable(false);
        }
    }

    /**
     * Applies the upgrade to the selected tower
     * <p>
     *     Checks that tower and upgrade is selected then applies the upgrade and resets the screen to show that the
     *     upgrade has been applied
     * </p>
     */
    @FXML
    public void applyUpgrade() {
        List<Button> upgradeButtons = List.of(upgrade1Btn, upgrade2Btn, upgrade3Btn);
        Tower tower = currentActiveTower;
        try {
            if (currentActiveTower == null) {
                throw new NoTowerSelectedException();
            }
            if (currentUpgrade == null) {
                throw new NoUpgradeSelectedException();
            }
            this.gameEnv.getInventoryService().applyUpgrade(currentActiveTower, currentUpgrade);
            resetScreen();
        } catch (NoTowerSelectedException e) {
            this.gameEnv.showAlert("No active tower selected", "Please select an active tower and try again");
            return;
        } catch (NoUpgradeSelectedException e) {
            this.gameEnv.showAlert("No Upgrade Selected", "Please select an upgrade and try again");
            return;
        } catch (UpgradeException e) {
            this.gameEnv.showAlert("Upgrade Failed", "You don't have enough points to upgrade this tower, try another tower or try again once you have more points!");
        } catch (UpgradeMaxException e){
            this.gameEnv.showAlert("Upgrade maxed out","You've applied this upgrade the maximum amount of times, please try apply this to another tower");
        }
        updateTowerStats(tower);
        currentActiveTower = tower;
        for (int i = 0; i < upgradeButtons.size(); i++) {
            upgradeButtons.get(i).setDisable(true);
            upgradeButtons.get(i).setText("None");
            upgradeButtons.get(i).setStyle("");
        }
        for (int i = 0; i < this.gameEnv.getInventoryService().getUpgrades().size(); i++) {
            upgradeButtons.get(i).setText(this.gameEnv.getInventoryService().getUpgrades().get(i).getName());
            upgradeButtons.get(i).setDisable(false);
        }
    }

    /**
     * Back button to take you back to round summary screen
     */
    @FXML
    public void backBtn() {
        this.gameEnv.backToMain();
    }
}
