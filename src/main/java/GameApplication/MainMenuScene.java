package GameApplication;

import gameobjects.Entity.Player;
import gameobjects.Map.Factories.GameMapFactory;
import gameobjects.Map.Factories.GameMapFactoryKeys;
import gameobjects.Map.MapBase;
import gameobjects.Navigator.Navigator;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static FinalProject.Javafx.ApplicationMain.gameWindow;
import static FinalProject.Javafx.ApplicationMain.scene;

public class MainMenuScene {
    private HBox layout = new HBox();
    private Button newGame, loadGame;

    public static MapScene map;

    public void start(){
        newGame = new Button("New Game");
        newGame.setOnAction(e -> basicStartNewGame());

        loadGame = new Button("Load Game");
        loadGame.setOnAction(e -> basicLoadGame());

        //FOR TESTING
        Button skip = new Button("Skip");
        skip.setOnAction(e -> {
            try {
                Skip();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        //layout.getChildren().add(skip);

        layout.getChildren().add(newGame);
        layout.getChildren().add(loadGame);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(30);
        scene = new Scene(layout, 900, 600);

        gameWindow.setScene(scene);
        gameWindow.show();
    }

    private void newGame(){
        TextField textField;
        textField = new TextField();
        Label label = new Label("Enter name of save: ");

        layout = new HBox();
        layout.getChildren().add(label);
        layout.getChildren().add(textField);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(15);

        scene.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER){
                newSave(textField.getText());
            }
        });
        scene.setRoot(layout);
        gameWindow.setScene(scene);
    }

    private void newSave(String name){
        System.out.println("Player entered: "+name);
    }

    private void basicStartNewGame(){
        GameMapFactory gameMapFactory = new GameMapFactory();
        ArrayList<MapBase> mapArr = gameMapFactory.createMapSet(GameMapFactoryKeys.STANDARD_MAP.toString());
        Navigator nav = Navigator.setState(new Player(100,45,45,"Alex"),
                mapArr,
                mapArr.get(0), 0, 3);

        System.out.println(nav.getPlayer()==null);

        MapScene mapScene = new MapScene();
        mapScene.start(nav);
    }

    private void basicLoadGame() {
        Navigator nav = Navigator.getInstance();
        try {
            nav.loadGame("save.txt");
        } catch (Exception e){
            System.out.println(e);
        }

        MapScene mapScene = new MapScene();
        mapScene.start(nav);
    }

    private void loadGame(){
        String names [];
        //Gets the location of the java directory
        String file = new File("").getAbsolutePath();
        //System.out.println(file);

        names = new File(file+"\\Saves").list();
        layout = new HBox();
        layout.setAlignment(Pos.CENTER);

        //List all the names of saves.
        for (String name: names) {
            Button button = new Button(name.substring(0,name.indexOf(".")));
            button.setOnAction(e -> readGameFile(name));
            layout.getChildren().add(button);
        }

        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(15);
        scene.setRoot(layout);
        gameWindow.setScene(scene);
    }

    private void readGameFile(String name){

    }

    private void startMap() throws Exception {
        System.out.println("Starting the arena/map.");
        map = new MapScene();
        map.start(Navigator.getInstance());
    }

    //allows us to skip the main menu for testing purposes
    private void Skip() throws Exception {
        startMap();
    }
}
