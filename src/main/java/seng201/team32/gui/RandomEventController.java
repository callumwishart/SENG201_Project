package seng201.team32.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import seng201.team32.models.GameEnv;
import seng201.team32.models.towers.Tower;

import java.io.IOException;
import java.io.InputStream;

/**
 * Controller for random_event.fxml
 */
public class RandomEventController {
    /**
     * Initialising the event message
     */
    @FXML
    private Label eventMessageLabel;
    /**
     * Initialising the event text
     */
    @FXML
    private Text eventText;
    /**
     * Initialising the affected tower image view
     */
    @FXML
    private ImageView towerAffectedImg;
    /**
     * The instance of the game env
     */
    private final GameEnv gameEnv;

    /**
     * Constructor of the RandomEventController to assign the passed gameEnv to this.gameEnv
     * @param gameEnv the instance of gameEnv being used
     */
    public RandomEventController(GameEnv gameEnv) {
        this.gameEnv = gameEnv;
    }

    /**
     * Initialise the Controller to display all the relevant information about the random event
     */
    public void initialize() {
        this.eventMessageLabel.setText(this.gameEnv.getCurrRandomEvent().getName());
        this.eventText.setText(this.gameEnv.getCurrRandomEvent().getDescription());
        Tower tower = this.gameEnv.getCurrRandomEvent().getTowerAffected();
        String imagePath = tower.getImagePath();
        try (InputStream inputStream = getClass().getResourceAsStream(imagePath)) {
            if (inputStream == null) {
                throw new RuntimeException("Resource not found: " + imagePath);
            }
            Image image = new Image(inputStream);
            towerAffectedImg.setImage(image);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load image: " + imagePath, e);
        }
    }

    /**
     * Continue to round summary screen button
     * <p>
     *     Will first clear the screen then launch the round summary screen.
     * </p>
     */
    public void continuePressed() {
        this.gameEnv.clearScreen.run();
        this.gameEnv.launchRoundSummaryScreen();
    }
}
