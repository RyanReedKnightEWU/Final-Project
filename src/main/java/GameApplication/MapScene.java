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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
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
    private Button seeInventory, save;
    private String saveName;

    Button shop;
    //StoreScene storeScene;

    /**
     * Sets up the map layout.
     * @param saveName The save file name.
     */
    public void start(Navigator nav, String saveName) {
        this.saveName = saveName;
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

    /**
     * Makes the map and shows it to the player.
     */
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

    /**
     * Saves the game.
     */
    public void setSave(Navigator nav) throws IOException {
        TextInputDialog dialogBox = new TextInputDialog("Enter the name of this save.");
        dialogBox.setContentText("Save: ");
        dialogBox.showAndWait();
        String save = dialogBox.getResult();
        char [] forbidonChars = new char[] {'!','@','#','$','%','^','&','*','(',')'};


        if(save!=null && !save.equals("")) {
            nav.saveInstance(save);
        }
    }

    /**
     * Moves the player.
     */
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
            nav.setCurrentMap(nav.getMapCollection().get(toMove.getNewMapHashValue()));


            // Add player to link's corresponding position on new map.
            nav.getCurrentMap().addEntity(nav.getPlayer(), newMapPLayerCoordinate[0],
                        newMapPLayerCoordinate[1]);

            nav.setPosition(newMapPLayerCoordinate[0],newMapPLayerCoordinate[1]);
            MapScene newMapScene = new MapScene();
            newMapScene.start(nav, saveName);
        }
        else if (key == MoveKey.MOVE_SUCCESSFUL) {
            reset(nav);
        }
    }

    /**
     * Resets the map on the screen.
     */
    public void reset(Navigator nav){
        fillGrid(grid,nav);
    }

    /**
     * Open the inventory screen.
     */
    public void setSeeInventory(Navigator nav){
        InventoryScene in = new InventoryScene();
        in.start(this, nav.getPlayer());
    }

    /**
     * Shows the map scene.
     */
    public void setScene(){
        scene.setRoot(layout);
    }

    /**
     * Puts an entity's name into the tile if they exist.
     */
    private String tileDisplay(Entity entity) {
        if (entity != null) {
            return entity.getName();
        }
        else {
            return "";
        }
    }
}
