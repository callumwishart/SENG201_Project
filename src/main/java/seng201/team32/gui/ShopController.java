package seng201.team32.gui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import seng201.team32.exceptions.*;
import seng201.team32.models.Difficulty;
import seng201.team32.models.GameEnv;
import seng201.team32.models.consumables.Consumable;
import seng201.team32.models.towers.Tower;
import seng201.team32.models.upgrades.Upgrade;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Controller for shop.fxml
 */
public class ShopController {
    /**
     * GameEnv to hold the gameEnv instance
     */
    GameEnv gameEnv;
    /**
     * Difficulty for the shop to use
     */
    private Difficulty difficulty;

    /**
     * Constructor which will assign the gameEnv to the current gameEnv
     * @param gameEnv the instance of GameEnv which will be used
     */
    public ShopController(GameEnv gameEnv) {this.gameEnv = gameEnv;}

    /**
     * Buttons for user to use to interact with game
     */
    @FXML
    private Button t1Btn, t2Btn, t3Btn, t4Btn, t5Btn, upgrade1Btn, upgrade2Btn, upgrade3Btn, c1Btn, c2Btn, c3Btn;
    /**
     * Images to show the current activeTowers and the selectedTower
     */
    @FXML
    private ImageView activeTower1, activeTower2, activeTower3, activeTower4, activeTower5, selectedTowerImg;
    /**
     * Labels to show the player the information about the shop items
     */
    @FXML
    private Label upgradeNameLabel, upgradeDescLabel, upgradeCostLabel, consumableNameLabel, consumableDescLabel,
            consumableCostLabel, towerNameLabel, towerLevelLabel, towerResourceLabel, towerResourceAmountLabel,
            towerValueLabel, towerReloadLabel, towerCostLabel, coinsLabel, pointsLabel;
    /**
     * Tower to hold selected tower
     */
    private Tower selectedTower;
    /**
     * Upgrade to hold selected upgrade
     */
    private Upgrade selectedUpgrade;
    /**
     * Consumable to hold selected consumable
     */
    private Consumable selectedConsumable;
    /**
     * Initialises the screen with all the possible items to purchase and what happens when you click them as well as
     * other shop data
     */
    public void initialize() {
        updatePointsAndCoins();
        List<Button> upgradeButtons = List.of(upgrade1Btn, upgrade2Btn, upgrade3Btn);
        List<Button> towerButtons = List.of(t1Btn,t2Btn,t3Btn,t4Btn,t5Btn);
        List<Button> consumableButtons = List.of(c1Btn, c2Btn, c3Btn);
        List<ImageView> activeTowerImages = List.of(activeTower1, activeTower2, activeTower3, activeTower4, activeTower5);
        this.difficulty = gameEnv.getDifficulty();
        // Sets the actions of clicking on the towers
        for (int i = 0; i < towerButtons.size(); i++) {
            int finalI = i;
            towerButtons.get(i).setOnAction(event -> {
                updateTowerStats(gameEnv.getPossibleTowers().get(finalI));
                selectedTower = gameEnv.getPossibleTowers().get(finalI);
                setButtonStyle(towerButtons, finalI, gameEnv, selectedTowerImg);
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
            towerButtons.get(i).setText(this.gameEnv.getPossibleTowers().get(i).getName());
        }

        // Initialises upgrade text
        for (int i = 0; i < upgradeButtons.size(); i++) {
            upgradeButtons.get(i).setText(this.gameEnv.getPossibleUpgrades().get(i).getName());
        }

        // Initialises consumables text
        for (int i = 0; i < consumableButtons.size(); i++) {
            consumableButtons.get(i).setText(this.gameEnv.getPossibleConsumables().get(i).getName());
        }
        // Initialises active tower images
        initializeActiveTowers(activeTowerImages);
    }

    /**
     * Initialises the active towers to let user see what towers they already have
     * @param activeTowerImages is the image views that this will apply to
     */
    private void initializeActiveTowers(List<ImageView> activeTowerImages) {
        for (int i = 0; i < this.gameEnv.getInventoryService().getActiveTowers().size(); i++) {
            String imagePath = gameEnv.getInventoryService().getActiveTowers().get(i).getImagePath();
            try (InputStream inputStream = getClass().getResourceAsStream(imagePath)) {
                if (inputStream == null) {
                    throw new RuntimeException("Resource not found: " + imagePath);
                }
                Image image = new Image(inputStream);
                activeTowerImages.get(i).setImage(image);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load image: " + imagePath, e);
            }

        }
    }

    /**
     * Sets the button styles to what should happen when you click on them
     * @param towerButtons buttons that this will apply to
     * @param finalI index that this should use to get the tower button
     * @param gameEnv the current instance of gameEnv
     * @param selectedTowerImg the image this will apply to
     */
    static void setButtonStyle(List<Button> towerButtons, int finalI, GameEnv gameEnv, ImageView selectedTowerImg) {
        towerButtons.forEach(button -> {
           if (button == towerButtons.get(finalI)) {
               button.setStyle("-fx-background-color: #b3b3b3; -fx-backround-radius: 5;");
               String imagePath = gameEnv.getPossibleTowers().get(finalI).getImagePath();
               try (InputStream inputStream = ShopController.class.getResourceAsStream(imagePath)) {
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
    }

    /**
     * Updates the consumable texts to the provided consumable
     * @param consumable the consumable that this will provide the stats of
     */
    private void updateConsumableStats(Consumable consumable) {
        consumableNameLabel.setText("Selected Consumable: " + consumable.getName());
        consumableDescLabel.setText("Description: " + consumable.getDescription());
        consumableCostLabel.setText("Cost: " + (int)(consumable.getCost() * this.difficulty.getCostMultiplier()));
    }
    /**
     * Updates the consumables stats to their initial texts
     */
    private void updateConsumableStats() {
        consumableNameLabel.setText("Selected Consumable: ");
        consumableDescLabel.setText("Description: ");
        consumableCostLabel.setText("Cost: ");
    }

    /**
     * Updates the upgrade stats to the selected upgrade stats
     * @param upgrade the upgrade that this will apply to
     */
    private void updateUpgradeStats(Upgrade upgrade) {
        upgradeNameLabel.setText("Selected Upgrade: " + upgrade.getName());
        upgradeDescLabel.setText("Description: " + upgrade.getDescription());
        upgradeCostLabel.setText("Cost: " + (int)(upgrade.getCost() * this.difficulty.getCostMultiplier()));
    }

    /**
     * Will reset the upgrade labels to their initial text
     */
    private void updateUpgradeStats() {
        upgradeNameLabel.setText("Selected Upgrade: ");
        upgradeDescLabel.setText("Description: ");
        upgradeCostLabel.setText("Cost: ");
    }

    /**
     * Will update the points and coins labels to the correct values
     */
    private void updatePointsAndCoins() {
        coinsLabel.setText("Coins: " + this.gameEnv.getInventoryService().getCoins());
        pointsLabel.setText("Points: " + this.gameEnv.getInventoryService().getPoints());
    }

    /**
     * Updates the tower stats to the provided towers stats
     * @param tower the tower that will have its stats displayed
     */
    public void updateTowerStats(Tower tower) {
        towerNameLabel.setText("Tower Name: " + tower.getName());
        towerLevelLabel.setText("Level: " + tower.getLevel());
        towerResourceLabel.setText("Resource: " + tower.getResource().getResourceType());
        towerResourceAmountLabel.setText("Resource amount: " + tower.getResourceAmount());
        towerValueLabel.setText("Value per resource: " + tower.getResource().getResourceCoinValue());
        towerReloadLabel.setText("Reload Speed: " + tower.getReloadSpeed());
        towerCostLabel.setText("Cost: " + (int)(tower.getCost() * this.difficulty.getCostMultiplier()));
    }

    /**
     * Will reset the tower stats to its initial text
     */
    public void updateTowerStats() {
        towerNameLabel.setText("Tower Name: ");
        towerLevelLabel.setText("Level: ");
        towerResourceLabel.setText("Resource: ");
        towerResourceAmountLabel.setText("Resource amount: ");
        towerValueLabel.setText("Value per resource: ");
        towerReloadLabel.setText("Reload Speed: ");
        towerCostLabel.setText("Cost: ");
    }

    /**
     * Will reset all the buttons to have no styles
     */
    private void resetButtons() {
        List<Button> upgradeButtons = List.of(upgrade1Btn, upgrade2Btn, upgrade3Btn);
        List<Button> towerButtons = List.of(t1Btn,t2Btn,t3Btn,t4Btn,t5Btn);
        List<Button> consumableButtons = List.of(c1Btn, c2Btn, c3Btn);
        resetButtonStyle(upgradeButtons, towerButtons, consumableButtons);
    }

    /**
     * Helper function of resetButtons to set the styles
     * @param upgradeButtons upgradeButton List
     * @param towerButtons towerButtons List
     * @param consumableButtons consumable List
     */
    public static void resetButtonStyle(List<Button> upgradeButtons, List<Button> towerButtons, List<Button> consumableButtons) {
        for (Button upgradeButton : upgradeButtons) {
            upgradeButton.setStyle("");
        }
        for (Button towerButton : towerButtons) {
            towerButton.setStyle("");
        }
        for (Button consumableButton : consumableButtons) {
            consumableButton.setStyle("");
        }
    }

    /**
     * Reset the images of the active towers
     */
    public void resetImages() {
        List<ImageView> activeTowerImages = List.of(activeTower1, activeTower2, activeTower3, activeTower4, activeTower5);
        initializeActiveTowers(activeTowerImages);
    }

    /**
     * Resets the screen to show nothing selected and up-to-date stats
     */
    private void resetScreen() {
        updateConsumableStats();
        updateTowerStats();
        updateUpgradeStats();
        updatePointsAndCoins();
        resetImages();
        resetButtons();
        selectedTower = null;
        selectedConsumable = null;
        selectedUpgrade = null;
        selectedTowerImg.setImage(null);
    }

    /**
     * Will take the user back to the round summary screen
     */
    @FXML
    public void backBtn() {
        this.gameEnv.backToMain();
    }

    /**
     * Will buy selected tower
     * <p>
     *     This method will handle errors and throw alerts as well as reset the screen after the tower has been bought
     * </p>
     * @throws NoSuchMethodException thrown if the provided method does not exist
     * @throws InvocationTargetException thrown if the target is not there
     * @throws InstantiationException thrown if the object cannot be instantiated
     * @throws IllegalAccessException thrown if the object cannot be accessed
     */
    @FXML
    public void buyTower() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        try {
            if (selectedTower == null) {
                throw new NoTowerSelectedException();
            }
            Tower newTower = selectedTower.getClass().getDeclaredConstructor().newInstance();
            this.gameEnv.getShopService().purchaseTower(newTower);
        } catch (TowerInventoryFullException e) {
            this.gameEnv.showAlert("Tower Inventory Full", "Your tower inventory is full, please sell a tower before trying again");
            return;
        } catch (PurchaseException e) {
            this.gameEnv.showAlert("Not enough money!", "You don't have enough money to buy this, please try again in the next round");
            return;
        } catch (NoTowerSelectedException e) {
            this.gameEnv.showAlert("No Tower Selected", "Please select your tower and try again");
            return;
        } catch (NegativeAdditionException e) {
            throw new RuntimeException(e);
        }
        resetScreen();
    }

    /**
     * Buys the upgrade and throws alerts if there is no tower or upgrade selected then resets the screen
     * @throws NoSuchMethodException ..
     * @throws InvocationTargetException ..
     * @throws InstantiationException ..
     * @throws IllegalAccessException ..
     */
    @FXML
    public void buyUpgrade() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        try {
            if (selectedUpgrade == null) {
                throw new NoUpgradeSelectedException();
            }
            Upgrade newUpgrade = selectedUpgrade.getClass().getDeclaredConstructor().newInstance();
            this.gameEnv.getShopService().purchaseUpgrade(newUpgrade);
        } catch (PurchaseException e) {
            this.gameEnv.showAlert("Not enough money", "You don't have enough money to buy this, please try again in the next round");
            return;
        } catch (NoUpgradeSelectedException e) {
            this.gameEnv.showAlert("No upgrade selected", "Please select an upgrade");
            return;
        } catch (UpgradesFullException e) {
            this.gameEnv.showAlert("Upgrade inventory full", "You already have 3 upgrades in your inventory, if you want more, apply them and then come back for more");
            return;
        }
        resetScreen();
    }

    /**
     * Buys consumable and handles errors if no consumable selected then resets screen
     * @throws NoSuchMethodException ..
     * @throws InvocationTargetException ..
     * @throws InstantiationException ..
     * @throws IllegalAccessException ..
     */
    @FXML
    public void buyConsumable() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        try {
            if (selectedConsumable == null) {
                throw new NoConsumableSelectedException();
            }
            Consumable newConsumable = selectedConsumable.getClass().getDeclaredConstructor().newInstance();
            this.gameEnv.getShopService().purchaseConsumable(newConsumable);
        } catch (ActiveConsumableException e) {
            this.gameEnv.showAlert("Consumable already Active!", "You already have this consumable so you don't need another!");
            return;
        } catch (NoConsumableSelectedException e) {
            this.gameEnv.showAlert("No Consumable Selected", "Please select a consumable");
            return;
        } catch (PurchaseException e) {
            this.gameEnv.showAlert("Not enough money", "You don't have enough money to buy this, please try again in the next round");
        }
        resetScreen();
    }

}
