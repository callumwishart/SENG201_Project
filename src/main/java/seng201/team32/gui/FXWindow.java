package seng201.team32.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

/**
 * Class starts the javaFX application window
 * @author seng201 teaching team
 */
public class FXWindow extends Application {
    /**
     * Opens the gui with the fxml content specified in resources/fxml/start_screen.fxml
     * @param primaryStage The current fxml stage, handled by javaFX Application class
     * @throws IOException if there is an issue loading fxml file
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader baseLoader = new FXMLLoader(getClass().getResource("/fxml/fx_wrapper.fxml"));
        Parent root = baseLoader.load();
        FXWrapper fxWrapper = baseLoader.getController();
        Scene scene = new Scene(root, 1000, 700);

        // Use getClass().getResourceAsStream to load the image from the classpath
        String imagePath = "/images/logo.png";
        try (InputStream inputStream = getClass().getResourceAsStream(imagePath)) {
            if (inputStream == null) {
                throw new RuntimeException("Resource not found: " + imagePath);
            }
            primaryStage.getIcons().add(new Image(inputStream));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load image: " + imagePath, e);
        }

        primaryStage.setTitle("FX Wrapper");
        primaryStage.setScene(scene);
        primaryStage.show();
        fxWrapper.init(primaryStage);
    }

    /**
     * Launches the FXML application, this must be called from another class (in this cass App.java) otherwise JavaFX
     * errors out and does not run
     * @param args command line arguments
     */
    public static void launchWrapper(String[] args) {
        launch(args);
    }

}
