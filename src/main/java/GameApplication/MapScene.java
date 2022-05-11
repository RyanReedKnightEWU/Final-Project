package GameApplication;

import Map.GameMapFactory;
import Map.MapBase;
import Map.MapFactoryBase;
import gameobjects.Navigator.MoveKey;
import gameobjects.Navigator.Navigator;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import gameobjects.Entity.*;
import javafx.scene.layout.VBox;

import static FinalProject.Javafx.ApplicationMain.gameWindow;
import static FinalProject.Javafx.ApplicationMain.scene;

public class MapScene {
    private BorderPane layout = new BorderPane();
    private GridPane grid = new GridPane();
    private HBox options = new HBox();
    private Button seeInventory, save;



    public void start(Navigator nav) {

        MapBase map = nav.getCurrentMap();


        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10);

        Label label = new Label("This is a label");
        GridPane.setConstraints(label,0,0);
        fillGrid(grid,nav);

        scene = new Scene(grid, 700, 1000);
        scene.setRoot(grid);

        gameWindow.setScene(scene);

        seeInventory = new Button("Inventory");
        save = new Button("Save Game");

        options = new HBox();
        options.setStyle("-fx-background-color: #336699;");
        options.setPadding(new Insets(20,20,20,20));
        options.setSpacing(10);

        options.getChildren().add(seeInventory);
        options.getChildren().add(save);

        fillGrid(grid,nav);

        layout.setCenter(grid);

        layout.setBottom(options);
        scene.setRoot(layout);

        gameWindow.setScene(scene);
    }

    private void fillGrid(GridPane grid, Navigator nav){
        grid.setAlignment(Pos.CENTER);
        Button b;
        Entity occupant;

        VBox vBox = new VBox();
        vBox.setPrefHeight(100);
        vBox.setPrefWidth(100);

        for(int i = 0; i < nav.getCurrentMap().getRows(); i++){
            for (int j = 0; j < nav.getCurrentMap().getColumns(); j++){
                int row = i, column = j;
                occupant = nav.getCurrentMap().getTile(i,j).getPrimaryOccupant();
                b = new Button(tileDisplay(occupant));
                b.setMinHeight(vBox.getPrefHeight());
                b.setMinWidth(vBox.getPrefWidth());
                b.setMaxHeight(vBox.getPrefWidth());
                b.setMaxWidth(vBox.getPrefWidth());
                b.setOnMouseClicked(e->{
                    String key = nav.moveTile(row, column).toString();
                    reset(nav);
                });
                grid.add(b, i, j);
            }
        }
    }

    private void reset(Navigator nav){
        MapBase map = nav.getCurrentMap();
        fillGrid(grid,nav);
    }

    private String tileDisplay(Entity entity) {

        if (entity != null) {
            return entity.getName();
        }
        else {
            return "";
        }
    }
}
