package FinalProject.Javafx;

import GameApplication.MainMenuScene;
import GameApplication.MapScene;
import Map.GameMapFactory;
import Map.GameMapFactoryKeys;
import Map.MapBase;
import Map.MapFactoryBase;
import gameobjects.Entity.Player;
import gameobjects.Navigator.Navigator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationMain extends Application {
    public static Scene scene = new Scene(new FlowPane(), 1000, 1250);
    public static Stage gameWindow;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

        MapFactoryBase mapFactory = new GameMapFactory();
        Navigator nav = Navigator.getInstance(new Player(100,100,100,"Jaque"),
                mapFactory.createMap(GameMapFactoryKeys.FIRST_ARENA.toString()),0,3);

        gameWindow = stage;
        stage.setTitle("Final Project Game");
        MainMenuScene mainMenuScene = new MainMenuScene();
        MapScene mapScene = new MapScene();
        mapScene.start(nav);
        stage.show();
        //mainMenuScene.start(nav.getCurrentMap());
    }
}