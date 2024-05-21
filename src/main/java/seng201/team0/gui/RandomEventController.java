package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import seng201.team0.models.randomevents.RandomEvent;

public class RandomEventController {
    @FXML
    private Label eventMessageLabel;
    @FXML
    private Label eventText;
    @FXML
    private ImageView towerAffectedImage;

    public RandomEventController() {

    }
    public void setRandomEvent(RandomEvent randomEvent) {
        this.eventMessageLabel.setText(randomEvent.getName());
        this.eventText.setText(randomEvent.getDescription());
        //Don't forget to get the image of the tower being affected!
    }
}
