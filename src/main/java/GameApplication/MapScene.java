package GameApplication;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import static FinalProject.Javafx.ApplicationMain.gameWindow;
import static FinalProject.Javafx.ApplicationMain.scene;

public class MapScene {
    private BorderPane layout = new BorderPane();
    private GridPane map = new GridPane();
    private HBox options = new HBox();
    private Button seeInventory, save;

    public void start(){
        System.out.println("Starting map");
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

        gameWindow.setScene(scene);
    }

    private void fillMap(){
        map.setAlignment(Pos.CENTER);
        for(int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                Button b = new Button("[ ]");
                map.add(b, i, j);
            }
        }
    }
}
