package GameApplication;

import Map.GameMapFactory;
import Map.MapBase;
import Map.MapFactoryBase;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import gameobjects.Entity.*;

import static FinalProject.Javafx.ApplicationMain.gameWindow;

public class MapScene {
    private BorderPane layout = new BorderPane();
    private GridPane grid = new GridPane();
    private HBox options = new HBox();
    private Button seeInventory, save;

    public void start() {

        MapFactoryBase mapFactory = new GameMapFactory();

        MapBase arenaMap;
        arenaMap = mapFactory.createMap("first arena");


        grid.setPadding(new Insets(20,20,20,20));
        grid.setVgap(10);
        grid.setHgap(10);

        Label label = new Label("This is a label");

        GridPane.setConstraints(label,0,0);

        //map.getChildren().addAll(label);
        fillMap(grid, arenaMap);

        Scene scene = new Scene(grid);

        scene.setRoot(grid);

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

        for(int i = 0; i < map.getRows(); i++){
            for (int j = 0; j < map.getColumns(); j++){

                occupant = map.getTile(i,j).getPrimaryOccupant();
                b = new Button(tileDisplay(occupant));
                grid.add(b, i, j);
            }
        }
    }

    private String tileDisplay(Entity entity) {

        int length = 10, depth = 5, i, j;

        StringBuilder stringBuilder = new StringBuilder();
        if (entity == null) {
            for (i = 0; i < depth; i++) {
                for (j = 0; j < length; j++) {
                    stringBuilder.append(" ");
                }
                stringBuilder.append('\n');
            }
        }
        else {
            for (i = 0; i < 2; i++) {
                for (j = 0; j < length; j++) {
                    stringBuilder.append(" ");
                }
                stringBuilder.append('\n');
            }

            if (entity.getName().length() > length) {
                stringBuilder.append(entity.getName(), 0, length-1).append('\n');
            }
            else {
                stringBuilder.append(entity.getName(), 0, entity.getName().length());
                stringBuilder.append(" ".repeat(Math.max(0, length - entity.getName().length()-1))).append('\n');
            }
            for (i = 4; i < 5; i++) {
                for (j = 0; j < length; j++) {
                    stringBuilder.append(" ");
                }
                stringBuilder.append('\n');
            }
        }

        return stringBuilder.toString();
    }
}
