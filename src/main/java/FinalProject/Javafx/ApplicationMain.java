package FinalProject.Javafx;

import GameApplication.MainMenuScene;
import GameApplication.MapScene;
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
        stage.setTitle("Final Project Game");
        //MainMenuScene mainMenuScene = new MainMenuScene();
        MapScene mapScene = new MapScene();
        mapScene.start();
        stage.show();
        //mainMenuScene.start();
    }
}