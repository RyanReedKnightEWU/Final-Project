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

    @Override
    public void start(Stage stage) throws IOException {

        File file = new File("save.txt");
        FileWriter fileWriter = new FileWriter(file);

        MapFactoryBase mapFactory = new GameMapFactory();
        Navigator nav = null;

        ArrayList<MapBase> mapSet = mapFactory.createMapSet(GameMapFactoryKeys.STANDARD_MAP.toString());

        for (MapBase m : mapSet) {
            System.out.print(m.hashCode() + "\t");
        }

        nav = Navigator.getInstance(new Player(100,0,0,"Jaque"),
                mapSet,mapSet.get(0),0,3);
        nav.saveInstance(fileWriter);
        fileWriter.close();


        System.out.println("INTEGER ---- "  + nav.getMapCollection().keySet());
        System.out.println(nav.getMapCollection().containsKey(57));

        gameWindow = stage;
        stage.setTitle("Final Project Game");
        MainMenuScene mainMenuScene = new MainMenuScene();
        MapScene mapScene = new MapScene();
        mapScene.start(nav);
        stage.show();
        //mainMenuScene.start(nav.getCurrentMap());



    }
}