package GameApplication;

import gameobjects.Entity.Player;
import gameobjects.Items.Armors.CombatArmor;
import gameobjects.Items.Consumables.AttackBottle;
import gameobjects.Items.Weapons.Pistol;
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

/**
 * Main menu allows the player to load an old save or make a new save.
 */
public class MainMenuScene {
    private HBox layout = new HBox();
    private Button newGame, loadGame;
    private final String mapKey = GameMapFactoryKeys.MAP_SET_2.toString();

    public void start(){
        newGame = new Button("New Game");
        newGame.setOnAction(e -> newGame());

        loadGame = new Button("Load Game");
        loadGame.setOnAction(e -> loadGame());

        layout.getChildren().add(newGame);
        layout.getChildren().add(loadGame);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(30);
        scene.setRoot(layout);

        gameWindow.setScene(scene);
        gameWindow.show();
    }
    /**
     * Used to leave a stage and return to main menu.
     */
    public static void returnToMainMenu(){
        MainMenuScene mainMenuScene = new MainMenuScene();
        gameWindow.close();
        mainMenuScene.start();
    }

    /**
     * Starts a new game.
     */
    private void newGame(){
        TextField textField;
        textField = new TextField();
        Label label = new Label("Enter name of your character: ");

        layout = new HBox();
        layout.getChildren().add(label);
        layout.getChildren().add(textField);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(15);

        scene.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER){

                ArrayList<MapBase> maps = (new GameMapFactory()).createMapSet(mapKey);
                Navigator nav = Navigator.setState(new Player(100,5,5,textField.getText()),
                        maps,
                        maps.get(0),
                        4,
                        2);
                nav.getPlayer().addItem(new Pistol());
                nav.getPlayer().addConsumable(new AttackBottle(12));
                nav.getPlayer().addItem(new CombatArmor(1));
                newSave(nav);
                MapScene mapScene = new MapScene();
                mapScene.start(nav,"new_game");
            }
        });
        scene.setRoot(layout);
        gameWindow.setScene(scene);
    }

    /**
     * @param name Name of save file.
     */
    private void newSave(String name) {
        System.out.println("Player entered: "+name);
        GameMapFactory gameMapFactory = new GameMapFactory();
        ArrayList<MapBase> mapArr = gameMapFactory.createMapSet(mapKey);
        Navigator nav = Navigator.setState(
                new Player(100,5,5,name),
                mapArr,
                mapArr.get(0),
                4,
                2);

        System.out.println(nav.getPlayer()==null);

        MapScene mapScene = new MapScene();
        mapScene.start(nav, name+".txt");
    }

    private void newSave(Navigator nav) {
        System.out.println("Player entered: "+ nav.getPlayer().getName());
        GameMapFactory gameMapFactory = new GameMapFactory();
        ArrayList<MapBase> mapArr = gameMapFactory.createMapSet(mapKey);
        System.out.println(nav.getPlayer()==null);

        MapScene mapScene = new MapScene();
        mapScene.start(nav, "newgame.txt");
    }

    /**
     * Shows a list of buttons, that represent save locations. When pressed that save will load.
     */
    private void loadGame(){
        Navigator nav = Navigator.getInstance();
        String names [];
        //Gets the location of the java directory
        String file = new File("").getAbsolutePath();

        names = new File(file+"").list();
        layout = new HBox();
        layout.setAlignment(Pos.CENTER);

        //List all the names of saves.
        for (String name: names) {
            System.out.println(name);
            if(name.endsWith(".txt")){
                Button button = new Button(name.substring(0,name.indexOf(".")));
                button.setOnAction(e -> {
                    /*The first attempt feeds load game the absolute path, this method works on Windows*/
                    try {
                        nav.loadGame(file+"\\" + name);
                        MapScene mapScene = new MapScene();
                        mapScene.start(nav, name);
                    } catch (FileNotFoundException ex) {

                        /*If the first try block fails, it attempts to load again, this time without
                        * the absolute path, this method works on Linux. */
                        try {
                            nav.loadGame(name);
                            MapScene mapScene = new MapScene();
                            mapScene.start(nav, name);
                        } catch (FileNotFoundException exc) {
                            exc.printStackTrace();
                        }
                    }
                });
                layout.getChildren().add(button);
            }
        }

        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(15);
        scene.setRoot(layout);
        gameWindow.setScene(scene);
    }

    /**
     * Starts a new game.
     */
    private void basicStartNewGame(){
        GameMapFactory gameMapFactory = new GameMapFactory();
        ArrayList<MapBase> mapArr = gameMapFactory.createMapSet(GameMapFactoryKeys.STANDARD_MAP.toString());
        Navigator nav = Navigator.setState(new Player(100,45,45,"Alex"),
                mapArr,
                mapArr.get(0), 0, 3);

        System.out.println(nav.getPlayer()==null);

        MapScene mapScene = new MapScene();
        mapScene.start(nav, "save.txt");
    }

    /**
     * Loads save.txt.
     */
    private void basicLoadGame() {
        Navigator nav = Navigator.getInstance();
        try {
            nav.loadGame("save.txt");
        } catch (Exception e){
            System.out.println(e);
        }

        MapScene mapScene = new MapScene();
        mapScene.start(nav, "save.txt");
    }
}
