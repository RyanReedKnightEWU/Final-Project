package GameApplication;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import static FinalProject.Javafx.ApplicationMain.gameWindow;
import static FinalProject.Javafx.ApplicationMain.scene;

public class MainMenuScene {
    HBox layout = new HBox();
    Button newGame, loadGame;
    //Scene scene;
    public static MapScene map;

    public void start(){
        newGame = new Button("Start Game");
        newGame.setOnAction(e -> newGame());

        loadGame = new Button("Load Game");
        loadGame.setOnAction(e -> loadGame());

        layout.getChildren().add(newGame);
        layout.getChildren().add(loadGame);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(30);
        scene = new Scene(layout, 900, 600);

        gameWindow.setScene(scene);
        gameWindow.show();
    }

    private void newGame(){

    }

    private void loadGame(){

    }

    private void startMap(){
        System.out.println("Starting the arena/map.");
        map = new MapScene();
        //arena.Show();
    }
}
