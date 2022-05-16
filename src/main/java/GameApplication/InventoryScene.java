package GameApplication;

import gameobjects.Entity.Entity;
import gameobjects.Entity.Player;
import gameobjects.Items.Items;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

import static FinalProject.Javafx.ApplicationMain.scene;

public class InventoryScene {
    private VBox items = new VBox();
    private HBox options = new HBox();
    private BorderPane layout = new BorderPane();
    private Button exit = new Button("Exit");
    private MapScene map;

    public void start(MapScene map,Entity player){
        this.map = map;
        exit.setOnAction(e -> exit());

        options = new HBox();
        options.setStyle("-fx-background-color: #336699;");
        options.setPadding(new Insets(20,20,20,20));
        options.setSpacing(10);
        options.getChildren().add(exit);
        ArrayList<Items> stuff = player.getInventory();

        for(Items i: stuff){
            Button b = new Button(i.toString());
            items.getChildren().add(b);
        }
        items.setAlignment(Pos.CENTER);
        items.setSpacing(10);

        layout.setCenter(items);
        layout.setBottom(options);
        scene.setRoot(layout);

    }

    public void reset(){

    }

    public void exit(){
        map.setScene();
    }

}
