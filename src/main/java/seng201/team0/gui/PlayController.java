package seng201.team0.gui;

import javafx.fxml.FXML;
import seng201.team0.models.GameEnv;

public class PlayController {
    private GameEnv gameEnv;
    public PlayController(GameEnv gameEnv) {this.gameEnv = gameEnv;}

    @FXML
    public void openInventoryButton() {
        this.gameEnv.openInventory();
    }

    @FXML
    public void openShop() {

    }
}
