package FinalProject.Javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationMain extends Application {
    public static Scene scene;
    public static Stage gameWindow;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        gameWindow = stage;
        System.exit(0);
    }
}