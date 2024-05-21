package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import seng201.team0.models.GameEnv;
import seng201.team0.models.randomevents.RandomEvent;
import seng201.team0.models.towers.Tower;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class RandomEventController {
    @FXML
    private Label eventMessageLabel;
    @FXML
    private Text eventText;
    @FXML
    private ImageView towerAffectedImg;
    private Tower tower;
    private GameEnv gameEnv;

    public RandomEventController(GameEnv gameEnv) {
        this.gameEnv = gameEnv;
    }
    public void initialize() {
        this.eventMessageLabel.setText(this.gameEnv.getCurrRandomEvent().getName());
        this.eventText.setText(this.gameEnv.getCurrRandomEvent().getDescription());
        tower = this.gameEnv.getCurrRandomEvent().getTowerAffected();
        String imagePath = tower.getImagePath();
        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream(imagePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image image = new Image(inputStream);

        towerAffectedImg.setImage(image);
    }
    public void continuePressed() {
        this.gameEnv.clearScreen.run();
        this.gameEnv.launchRoundSummaryScreen();
    }
}
