package FinalProject.Javafx;

import GameApplication.MainMenuScene;
import GameApplication.MapScene;
import gameobjects.Map.GameMapFactory;
import gameobjects.Map.GameMapFactoryKeys;
import gameobjects.Map.MapFactoryBase;
import gameobjects.Entity.Player;
import gameobjects.Navigator.Navigator;
import javafx.application.Application;
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
        Navigator nav = Navigator.getInstance(new Player(100,0,0,"Jaque"),
                mapFactory.createMap(GameMapFactoryKeys.STANDARD_MAP.toString()),0,3);

        System.out.println("INTEGER ---- "  + nav.mapCollection.keySet());


        gameWindow = stage;
        stage.setTitle("Final Project Game");
        MainMenuScene mainMenuScene = new MainMenuScene();
        MapScene mapScene = new MapScene();
        mapScene.start(nav);
        stage.show();
        //mainMenuScene.start(nav.getCurrentMap());



    }
}