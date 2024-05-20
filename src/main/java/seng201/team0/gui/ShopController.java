package seng201.team0.gui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import seng201.team0.exceptions.ActiveConsumableException;
import seng201.team0.exceptions.PurchaseException;
import seng201.team0.exceptions.TowerInventoryFullException;
import seng201.team0.models.GameEnv;
import seng201.team0.models.consumables.Consumable;
import seng201.team0.models.towers.Tower;
import seng201.team0.models.upgrades.Upgrade;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ShopController {
    GameEnv gameEnv;
    public ShopController(GameEnv gameEnv) {this.gameEnv = gameEnv;}
    @FXML
    private Button t1Btn, t2Btn, t3Btn, t4Btn, t5Btn;
    @FXML
    private Button upgrade1Btn, upgrade2Btn, upgrade3Btn;
    @FXML
    private Button c1Btn, c2Btn, c3Btn;
    @FXML
    private ImageView selectedTowerImg;
    @FXML
    private Label upgradeNameLabel, upgradeDescLabel, upgradeCostLabel, consumableNameLabel, consumableDescLabel, consumableCostLabel;
    @FXML
    private Label towerNameLabel, towerLevelLabel, towerResourceLabel, towerResourceAmountLabel, towerValueLabel, towerReloadLabel, towerCostLabel;
    @FXML
    private Label coinsLabel, pointsLabel;
    private Tower selectedTower;
    private Upgrade selectedUpgrade;
    private Consumable selectedConsumable;

    public void initialize() {
        updatePointsAndCoins();
        List<Button> upgradeButtons = List.of(upgrade1Btn, upgrade2Btn, upgrade3Btn);
        List<Button> towerButtons = List.of(t1Btn,t2Btn,t3Btn,t4Btn,t5Btn);
        List<Button> consumableButtons = List.of(c1Btn, c2Btn, c3Btn);

        // Sets the actions of clicking on the towers
        for (int i = 0; i < towerButtons.size(); i++) {
            int finalI = i;
            towerButtons.get(i).setOnAction(event -> {
                updateTowerStats(gameEnv.getPossibleTowers().get(finalI));
                selectedTower = gameEnv.getPossibleTowers().get(finalI);
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

                       selectedTowerImg.setImage(image);
                   } else {
                       button.setStyle("");
                   }
                });
            });
        }
        // Sets the actions of clicking on the upgrades

        for (int i = 0; i < upgradeButtons.size(); i++) {
            int finalI = i;
            upgradeButtons.get(i).setOnAction(event -> {
                selectedUpgrade = gameEnv.getPossibleUpgrades().get(finalI);
                updateUpgradeStats(selectedUpgrade);
                upgradeButtons.forEach(button -> {
                    if (button == upgradeButtons.get(finalI)) {
                        button.setStyle("-fx-background-color: #b3b3b3; -fx-backround-radius: 5;");
                    } else {
                        button.setStyle("");
                    }
                });
            });
        }

        // Sets the actions of clicking on a consumable
        for (int i =0; i < consumableButtons.size(); i++) {
            int finalI = i;
            consumableButtons.get(i).setOnAction(event -> {
                selectedConsumable = gameEnv.getPossibleConsumables().get(finalI);
                updateConsumableStats(selectedConsumable);
                consumableButtons.forEach(button -> {
                    if (button == upgradeButtons.get(finalI)) {
                        button.setStyle("-fx-background-color: #b3b3b3; -fx-backround-radius: 5;");
                    } else {
                        button.setStyle("");
                    }
                });
            });
        }

        // Initialises tower text
        for (int i = 0; i < towerButtons.size(); i++) {
            int finalI = i;
            towerButtons.get(finalI).setText(this.gameEnv.getPossibleTowers().get(finalI).getName());
        }

        // Initialises upgrade text
        for (int i = 0; i < upgradeButtons.size(); i++) {
            upgradeButtons.get(i).setText(this.gameEnv.getPossibleUpgrades().get(i).getName());
        }

        // Initialises consumables text
        for (int i = 0; i < consumableButtons.size(); i++) {
            consumableButtons.get(i).setText(this.gameEnv.getPossibleConsumables().get(i).getName());
        }
    }

    private void updateConsumableStats(Consumable consumable) {
        consumableNameLabel.setText("Selected Consumable: " + consumable.getName());
        consumableDescLabel.setText("Description: " + consumable.getDescription());
        consumableCostLabel.setText("Cost: " + consumable.getCost());
    }

    private void updateUpgradeStats(Upgrade upgrade) {
        upgradeNameLabel.setText("Selected Upgrade: " + upgrade.getName());
        upgradeDescLabel.setText("Description: " + upgrade.getDescription());
        upgradeCostLabel.setText("Cost: " + upgrade.getCost());
    }

    private void updatePointsAndCoins() {
        coinsLabel.setText("Coins: " + String.valueOf(this.gameEnv.getInventoryService().getCoins()));
        pointsLabel.setText("Points: " + String.valueOf(this.gameEnv.getInventoryService().getPoints()));
    }

    public void updateTowerStats(Tower tower) {
        towerNameLabel.setText("Tower Name: " + tower.getName());
        towerLevelLabel.setText("Level: " + String.valueOf(tower.getLevel()));
        towerResourceLabel.setText("Resource: " + tower.getResource().getResourceType());
        towerResourceAmountLabel.setText("Resource amount: " + String.valueOf(tower.getResourceAmount()));
        towerValueLabel.setText("Value per resource: " + tower.getResource().getResourceCoinValue());
        towerReloadLabel.setText("Reload Speed: " + tower.getReloadSpeed());
        towerCostLabel.setText("Cost: " + tower.getCost());
    }
    @FXML
    public void backBtn() {
        this.gameEnv.backToMain();
    }
    @FXML
    public void buyTower() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, PurchaseException, TowerInventoryFullException {
        Tower newTower = selectedTower.getClass().getDeclaredConstructor().newInstance();
        this.gameEnv.getShopService().purchaseTower(newTower);
        updatePointsAndCoins();
    }
    @FXML
    public void buyUpgrade() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, PurchaseException {
        Upgrade newUpgrade = selectedUpgrade.getClass().getDeclaredConstructor().newInstance();
        this.gameEnv.getShopService().purchaseUpgrade(newUpgrade);
        updatePointsAndCoins();
    }
    @FXML
    public void buyConsumable() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ActiveConsumableException {
        Consumable newConsumable = selectedConsumable.getClass().getDeclaredConstructor().newInstance();
        this.gameEnv.getShopService().purchaseConsumable(newConsumable);
        updatePointsAndCoins();
    }

}
