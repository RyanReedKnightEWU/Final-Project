package FinalProject.Javafx;

import GameApplication.MainMenuScene;
import GameApplication.MapScene;
import gameobjects.Map.Factories.GameMapFactory;
import gameobjects.Map.Factories.GameMapFactoryKeys;
import gameobjects.Map.Factories.MapFactoryBase;
import gameobjects.Entity.Player;
import gameobjects.Map.Factories.MapLoader;
import gameobjects.Map.MapBase;
import gameobjects.Navigator.Navigator;
import gameobjects.SaveLoader.SaveLoader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ApplicationMain extends Application {
    public static Scene scene = new Scene(new FlowPane(), 1000, 1250);
    public static Stage gameWindow;

    public static void main(String[] args) {
        launch();
    }

    /**
     * Starts the GUI.
     */
    @Override
    public void start(Stage stage) throws IOException {
        gameWindow = stage;
        stage.setTitle("Final Project Game");
        MainMenuScene mainMenuScene = new MainMenuScene();
        mainMenuScene.start();
    }
}