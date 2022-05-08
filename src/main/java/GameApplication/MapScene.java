package GameApplication;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import gameobjects.Tile.*;
import gameobjects.Entity.*;
import gameobjects.Items.*;

import static FinalProject.Javafx.ApplicationMain.gameWindow;

public class MapScene {
    private BorderPane layout = new BorderPane();
    private GridPane grid = new GridPane();
    private HBox options = new HBox();
    private Button seeInventory, save;

    public void start(){

        MapFactoryBase mapFactory = new GameMapFactory();
        MapBase arenaMap = mapFactory.createRectangularMap(10,20);
        try {
            arenaMap.addEntity(new Goblin("Azog"), 5, 5);
        } catch (Exception e) {

        }

        grid.setPadding(new Insets(20,20,20,20));
        grid.setVgap(10);
        grid.setHgap(10);

        Label label = new Label("This is a label");

        GridPane.setConstraints(label,0,0);

        //map.getChildren().addAll(label);
        fillMap(grid, arenaMap);

        Scene scene = new Scene(grid,400,400);

        gameWindow.setScene(scene);

        /*System.out.println("Starting map");
        seeInventory = new Button("Inventory");
        save = new Button("Save Game");

        options = new HBox();
        options.setStyle("-fx-background-color: #336699;");
        options.setPadding(new Insets(20,20,20,20));
        options.setSpacing(10);

        options.getChildren().add(seeInventory);
        options.getChildren().add(save);

        fillMap();

        layout.setCenter(map);

        layout.setBottom(options);
        scene.setRoot(layout);

        gameWindow.setScene(scene);*/
    }

    private void fillMap(GridPane grid, MapBase map){
        grid.setAlignment(Pos.CENTER);
        Button b;
        Entity occupant;
        String emptyTile = "       ";

        for(int i = 0; i < map.getRows(); i++){
            for (int j = 0; j < map.getColumns(); j++){

                occupant = map.getTile(i,j).getPrimaryOccupant();

                if (occupant != null ) {
                    b = new Button(occupant.getName());
                }
                else {
                    b = new Button(emptyTile + '\n' + emptyTile + '\n' + emptyTile);
                }
                grid.add(b, i, j);
            }
        }
    }
}
