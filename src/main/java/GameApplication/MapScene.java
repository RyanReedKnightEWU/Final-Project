package GameApplication;

import gameobjects.Map.MapBase;
import gameobjects.Items.Armors.CombatArmor;
import gameobjects.Items.Consumables.AttackBottle;
import gameobjects.Items.Weapons.Pistol;
import gameobjects.Navigator.MoveKey;
import gameobjects.Navigator.Navigator;
import gameobjects.Tile.LinkTile;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import gameobjects.Entity.*;
import javafx.scene.layout.VBox;

import java.io.FileWriter;
import java.io.IOException;

import static FinalProject.Javafx.ApplicationMain.gameWindow;
import static FinalProject.Javafx.ApplicationMain.scene;

public class MapScene {
    private BorderPane layout = new BorderPane();
    private GridPane grid = new GridPane();
    private HBox options = new HBox();
    private Button seeInventory, save, load;

    //To REMOVE later
    Button shop;
    StoreScene storeScene;

    public void start(Navigator nav) {
        MapBase map = nav.getCurrentMap();
        StoreScene storeScene = new StoreScene(this,nav);

        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10);

        Label label = new Label("This is a label");
        GridPane.setConstraints(label,0,0);
        fillGrid(grid,nav);

        seeInventory = new Button("Inventory");
        seeInventory.setOnAction(e -> setSeeInventory(nav));
        save = new Button("Save Game");

        // Start testing save/load
        save.setOnMouseClicked(e -> {
            try {
                setSave(nav);
                System.out.println("HI");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        // End testing save/load

        options = new HBox();
        options.setStyle("-fx-background-color: #336699;");
        options.setPadding(new Insets(20,20,20,20));
        options.setSpacing(10);

        options.getChildren().add(seeInventory);
        options.getChildren().add(save);

        //REMOVE after shopkeeper is made
        shop = new Button("Shop");
        //Does nothing for now.
        shop.setOnAction(e -> storeScene.show());
        options.getChildren().add(shop);

        fillGrid(grid,nav);

        layout.setCenter(grid);
        fillGrid(grid,nav);
        layout.setCenter(grid);
        layout.setBottom(options);
        fillGrid(grid,nav);
        layout.setCenter(grid);

        scene.setRoot(layout);
        gameWindow.setScene(scene);

        if (nav.newGame) {
            nav.getPlayer().addItem(new Pistol());
            nav.getPlayer().addConsumable(new AttackBottle(12));
            nav.getPlayer().addItem(new CombatArmor(1));
        }
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

                String doorway = "Doorway";
                boolean isLink = nav.getCurrentMap().getTile(i,j) instanceof LinkTile,
                        hasOccupant = nav.getCurrentMap().getTile(i,j).getPrimaryOccupant() != null;

                if (isLink && hasOccupant) {
                    b = new Button(doorway + '\n' + occupant.getName());
                } else if (isLink) {
                    b = new Button(doorway);
                } else {
                    b = new Button(tileDisplay(occupant));
                }
                b.setMinHeight(vBox.getPrefHeight());
                b.setMinWidth(vBox.getPrefWidth());
                b.setMaxHeight(vBox.getPrefWidth());
                b.setMaxWidth(vBox.getPrefWidth());
                b.setOnMouseClicked(e->move(nav,row,column));
                grid.add(b, i, j);
            }


        }
    }

    /*private void newMap() {
        start(nav);
        s
    }*/

    public void setSave(Navigator nav) throws IOException {
        String saveName = "save.txt";
        try (FileWriter saveFile = new FileWriter(saveName)) {
            //saveItemArray(new Items[]{ new throwingKnife(), new Clothes(4), new PlateArmor(6),
            // new Pistol()},saveFile);
            nav.saveInstance(saveFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void move(Navigator nav, int row, int column){

        MoveKey key = nav.moveTile(row, column);


        if (key == MoveKey.CURRENT_TILE) {
            // To fix bug where player disappears from map when clicked.
            return;
        }else if (key == MoveKey.TILE_OCCUPIED) {



            AttackScene attackScene = new AttackScene(this, nav,row,column);
            attackScene.start((Player) nav.getPlayer(),
                    nav.getCurrentMap().getTile(row, column).getPrimaryOccupant());

            /*TEST*/ System.out.println("DEFENDER ALIVE BEFORE BLOCK " +
                    nav.getCurrentMap().getTile(row, column).getPrimaryOccupant().isAlive());

            if (!nav.getCurrentMap().getTile(row, column).getPrimaryOccupant().isAlive()) {
                System.out.println("DEFENDER IS NOT ALIVE " +
                        !nav.getCurrentMap().getTile(row, column).getPrimaryOccupant().isAlive());
                nav.forceMove(row, column);

                reset(nav);
            }

        }
        else if (key == MoveKey.LINK_TO_MAP) {
            // Get link to tile.
            LinkTile toMove = (LinkTile) nav.getCurrentMap().getTile(row,column);
            // Get coordinates of player on new map.
            int[] newMapPLayerCoordinate = toMove.getPosition();

            nav.getCurrentMap().getTile(nav.getCurrentRow(),
                    nav.getCurrentColumn()).setPrimaryOccupant(null);

            // Set current map in Navigator to new map.
            System.out.println(nav.getMapCollection().keySet());
            System.out.println("Hash code at 186 " + toMove.getNewMapHashValue());
            System.out.println("187 NULL?"+nav.getMapCollection().get(toMove.getNewMapHashValue()));
            nav.setCurrentMap(nav.getMapCollection().get(toMove.getNewMapHashValue()));


            System.out.println(nav.getMapCollection().get(toMove.getNewMapHashValue()) == null);
            // Add player to link's corresponding position on new map.

            System.out.println(nav.getCurrentMap() == null);
            System.out.println("Keys: " + nav.getMapCollection().keySet());
            System.out.println("Values: " + nav.getMapCollection().values());

            nav.getCurrentMap().addEntity(nav.getPlayer(), newMapPLayerCoordinate[0],
                        newMapPLayerCoordinate[1]);

            nav.setPosition(newMapPLayerCoordinate[0],newMapPLayerCoordinate[1]);
            MapScene newMapScene = new MapScene();
            newMapScene.start(nav);
        }
        else if (key == MoveKey.MOVE_SUCCESSFUL) {
            reset(nav);
        }
    }
    public void reset(Navigator nav){
        MapBase map = nav.getCurrentMap();
        fillGrid(grid,nav);
    }

    public void setSeeInventory(Navigator nav){
        InventoryScene in = new InventoryScene();
        in.start(this, nav.getPlayer());
    }

    public void setScene(){
        scene.setRoot(layout);
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
