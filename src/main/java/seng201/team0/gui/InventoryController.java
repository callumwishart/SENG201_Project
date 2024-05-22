package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import seng201.team0.exceptions.*;
import seng201.team0.models.GameEnv;
import seng201.team0.models.towers.Tower;
import seng201.team0.models.upgrades.Upgrade;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;


public class InventoryController {
    @FXML
    private Label coinsLabel, pointsLabel, towerNameLabel, towerStatusLabel, towerLevelLabel, towerResourceLabel, towerCoinsPerResourceLabel, pointsNeededLabel, sellCostLabel, repairCostLabel, towerCapacityLabel, towerSpeedLabel;
    @FXML
    private Label selectedUpgradeLabel, upgradeDescLabel, resourceValueLabel;
    @FXML
    private Button t1Btn, t2Btn, t3Btn, t4Btn, t5Btn;
    @FXML
    private Button reservedT1Btn, reservedT2Btn, reservedT3Btn, reservedT4Btn, reservedT5Btn, upgrade1Btn, upgrade2Btn, upgrade3Btn;
    @FXML
    private Button c1Btn, c2Btn, c3Btn;
    @FXML
    private ImageView selectedTowerImg;
    private final GameEnv gameEnv;
    private Tower currentActiveTower = null;
    private Tower currentReserveTower = null;
    private Tower currentTower = null;
    private Upgrade currentUpgrade = null;
    public InventoryController(GameEnv gameEnv) {this.gameEnv = gameEnv;}
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
                        FileInputStream inputStream;
                        try {
                            inputStream = new FileInputStream(imagePath);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        Image image = new Image(inputStream);
                        selectedTowerImg.setImage(image);
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
                        FileInputStream inputStream;
                        try {
                            inputStream = new FileInputStream(imagePath);
                        } catch (FileNotFoundException e){
                            throw new RuntimeException(e);
                        }
                        Image image = new Image(inputStream);
                        selectedTowerImg.setImage(image);
                    } else {
                        button.setStyle("");
                    }
                });

            });
        }
        // Set the text of the activeTower Buttons to be correct
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

    private void updateUpgradeStats(Upgrade upgrade) {
        selectedUpgradeLabel.setText("Selected Upgrade: " + upgrade.getName());
        upgradeDescLabel.setText("Description: " + upgrade.getDescription());
    }
    private void updateUpgradeStats() {
        selectedUpgradeLabel.setText("Selected Upgrade: ");
        upgradeDescLabel.setText("Description: ");
    }

    public String getStatus(Tower tower) {
        if (tower.isBroken()) {
            return "Broken";
        } else {
            return "OK";
        }
    }
    private void updateTowerStats(Tower tower) {
        towerNameLabel.setText("Tower Name: " + tower.getName());
        towerStatusLabel.setText("Status: " + getStatus(tower));
        towerLevelLabel.setText("Level: " + tower.getLevel());
        towerResourceLabel.setText("Resource: " + tower.getResource().getResourceType());
        resourceValueLabel.setText("Resource Value: " + tower.getResource().getResourceCoinValue());
        towerCoinsPerResourceLabel.setText("Coins per resource: " + tower.getResource().getResourceCoinValue());
        pointsNeededLabel.setText("Points needed to upgrade: " + tower.getUpgradePointLimit());
        sellCostLabel.setText("Sell cost: " + tower.getSellCost());
        repairCostLabel.setText("Repair Cost: " + tower.getRepairCost());
        towerSpeedLabel.setText("Tower Speed: " + tower.getReloadSpeed());
        towerCapacityLabel.setText("Resource Capacity: " + tower.getResourceAmount());
        String imagePath = tower.getImagePath();
        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream(imagePath);
        } catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }
        Image image = new Image(inputStream);
        selectedTowerImg.setImage(image);
    }
    private void updateTowerStats() {
        towerNameLabel.setText("Tower Name: ");
        towerStatusLabel.setText("Status: ");
        towerLevelLabel.setText("Level: ");
        towerResourceLabel.setText("Resource: ");
        resourceValueLabel.setText("Resource value: ");
        towerCoinsPerResourceLabel.setText("Coins per resource: ");
        pointsNeededLabel.setText("Points needed to upgrade: ");
        sellCostLabel.setText("Sell cost: ");
        repairCostLabel.setText("Repair Cost: ");
        towerSpeedLabel.setText("Tower Speed: ");
        towerCapacityLabel.setText("Resource Capacity: ");
    }
    private void resetButtons() {
        List<Button> activeTowerButtons = List.of(t1Btn, t2Btn, t3Btn, t4Btn, t5Btn);
        List<Button> reservedTowerButtons = List.of(reservedT1Btn, reservedT2Btn, reservedT3Btn, reservedT4Btn, reservedT5Btn);
        List<Button> consumableButtons = List.of(c1Btn, c2Btn, c3Btn);
        List<Button> upgradeButtons = List.of(upgrade1Btn, upgrade2Btn, upgrade3Btn);
        for (int i = 0; i < activeTowerButtons.size(); i++) {
            activeTowerButtons.get(i).setStyle("");
        }
        for (int i = 0; i < reservedTowerButtons.size(); i++) {
            reservedTowerButtons.get(i).setStyle("");
        }
        for (int i = 0; i < consumableButtons.size(); i++) {
            consumableButtons.get(i).setStyle("");
        }
        for (int i = 0; i < upgradeButtons.size(); i++) {
            upgradeButtons.get(i).setStyle("");
        }
    }
    public void resetPointsAndCoins() {
        coinsLabel.setText("Coins: " + this.gameEnv.getInventoryService().getCoins());
        pointsLabel.setText("Points: " + this.gameEnv.getInventoryService().getPoints());
    }

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

    @FXML
    public void repairTower() throws Exception {
        Tower tower = currentTower;

        try {
            if (currentTower == null) {
                throw new NoTowerSelectedException();
            }
            if (!currentTower.isBroken()) {
                throw new IllegalArgumentException();
            }
            this.gameEnv.getInventoryService().repairTower(currentTower);
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
        updateTowerStats(tower);
    }
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
            this.gameEnv.showAlert("Upgrade Failed", "You don't have enough points to upgrade this tower");
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
    @FXML
    public void backBtn() {
        this.gameEnv.backToMain();
    }
}
