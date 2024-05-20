package seng201.team0.gui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import seng201.team0.models.GameEnv;

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
    public void backBtn() {
    }
    @FXML
    public void buyTower() {

    }
    @FXML
    public void buyUpgrade() {

    }
    @FXML
    public void buyConsumable() {

    }

}
