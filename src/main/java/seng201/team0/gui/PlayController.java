package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import seng201.team0.models.Difficulty;
import seng201.team0.models.GameEnv;
import seng201.team0.models.gameplay.Cart;
import seng201.team0.models.gameplay.GameObserver;
import seng201.team0.models.gameplay.GameRunner;
import seng201.team0.models.gameplay.Round;
import seng201.team0.services.InventoryService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class PlayController implements GameObserver {
    @FXML
    private Label playerLabel, pointsLabel;
    @FXML
    private Button c1Btn, c2Btn, c3Btn, c4Btn, c5Btn, c6Btn, c7Btn, c8Btn, c9Btn, c10Btn, c11Btn, c12Btn, c13Btn, c14Btn, c15Btn, c16Btn, c17Btn, c18Btn, c19Btn, c20Btn;
    @FXML
    private ProgressBar c1Progress, c2Progress, c3Progress, c4Progress, c5Progress, c6Progress, c7Progress, c8Progress, c9Progress, c10Progress;
    @FXML
    private ProgressBar c11Progress, c12Progress, c13Progress, c14Progress, c15Progress, c16Progress, c17Progress, c18Progress, c19Progress, c20Progress;
    @FXML
    private Button t1Btn, t2Btn, t3Btn, t4Btn, t5Btn;
    @FXML
    private ProgressBar t1Progress, t2Progress, t3Progress, t4Progress, t5Progress;
    @FXML
    private ImageView tower1Img, tower2Img, tower3Img, tower4Img, tower5Img;
    private GameEnv gameEnv;
    private int roundNum;
    private Round round;
    private GameRunner gameRunner;
    private Difficulty difficulty;
    private InventoryService inventoryService;
    private List<Cart> carts;
    public PlayController(GameEnv gameEnv){
        this.gameEnv = gameEnv;
        this.round = new Round(this.gameEnv.getInventoryService(), this.gameEnv.getDifficulty(), this.gameEnv.getRoundNum());
        this.gameRunner = new GameRunner(round, this);
        this.roundNum = this.gameEnv.getRoundNum();
        this.difficulty = this.gameEnv.getDifficulty();
        this.inventoryService = this.gameEnv.getInventoryService();
        this.carts = round.getCarts();
    }
    public void initialize(){
        List<Button> cartButtons = List.of(c1Btn, c2Btn, c3Btn, c4Btn, c5Btn, c6Btn, c7Btn, c8Btn, c9Btn, c10Btn, c11Btn, c12Btn, c13Btn, c14Btn, c15Btn, c16Btn, c17Btn, c18Btn, c19Btn, c20Btn);
        List<Button> towerButtons = List.of(t1Btn, t2Btn, t3Btn, t4Btn, t5Btn);
        List<ImageView> towerImages = List.of(tower1Img, tower2Img, tower3Img, tower4Img, tower5Img);
        for (int i = 0; i < inventoryService.getActiveTowers().size(); i ++) {
            String imagePath = inventoryService.getActiveTowers().get(i).getImagePath();
            FileInputStream inputStream;
            try {
                inputStream = new FileInputStream(imagePath);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            Image image = new Image(inputStream);
            towerImages.get(i).setImage(image);
            towerButtons.get(i).setDisable(false);
        }
        for (int i = 0; i < round.getCarts().size(); i++) {
            cartButtons.get(i).setText(round.getCarts().get(i).getName());
            cartButtons.get(i).setDisable(false);
        }
    }
    @FXML
    public void openInventoryButton() {
        this.gameEnv.openInventory();
    }

    @FXML
    public void openShop() {
        this.gameEnv.openShop();
    }
    @FXML
    public void startGame() throws InterruptedException {
        this.gameRunner.run();
    }

    @Override
    public void observe(GameRunner gameRunner) {
        
    }
}
