package gameobjects.Navigator;

import gameobjects.Entity.SaveLoad.EntityLoader;
import gameobjects.Map.Factories.MapLoader;
import gameobjects.Map.MapBase;
import gameobjects.Entity.Entity;
import gameobjects.Map.RectangularMap;
import gameobjects.SaveLoader.Savable;
import gameobjects.SaveLoader.SaveLoader;
import gameobjects.Tile.LinkTile;
import gameobjects.Tile.TileBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Navigator implements Savable{

    private static Navigator uniqueInstance;
    private MapBase currentMap;
    private HashMap<Integer,MapBase> mapCollection;
    private TileBase currentTile;
    private int row, column;
    private Entity player;

    /**
     * Defines Navigator, called in getInstance method if uniqueInstance is not null.
     *
     * @param player               - Entity to represent user.
     * //@param mapArr                  - the current map the player is on.
     * @param playerColumnPosition - horizontal coordinate of tile on tile matrix
     * @param playerRowPosition    - vertical coordinate of tile on tile matrix
     **/
    private Navigator(Entity player, ArrayList<MapBase> mapSet,MapBase currentMap, int playerRowPosition, int playerColumnPosition) {

        this.player = player;
        this.currentMap = currentMap;

        mapCollection = new HashMap<>();
        for (MapBase map : mapSet){
            mapCollection.put(map.hashCode(),map);
        }

        this.row = playerRowPosition;
        this.column = playerColumnPosition;
        this.currentMap.addEntity(player, playerRowPosition, playerColumnPosition);

    }

    private void putMapInHashMap(MapBase[] mapArr) {


        for (int i = 0; i < mapArr.length; i++) {
            mapCollection.put(mapArr[i].hashCode(), mapArr[i]);
        }
    }
    private void putMapInHashMap(MapBase map) {

        //System.out.println(map.getIdentifier() + "\t" + map.hashCode());
        mapCollection.put(map.hashCode(),map);
    }

    // Getters and setters.
    public static Navigator setState(Entity player, ArrayList<MapBase> mapSet,MapBase currentMap, int xCoordinate, int yCoordinate) {

        Navigator.uniqueInstance = new Navigator(player, mapSet,currentMap, xCoordinate, yCoordinate);

        return Navigator.uniqueInstance;
    }

    //
    public static Navigator getInstance() {
        if(Navigator.uniqueInstance == null) {
            reset();
        }
        return Navigator.uniqueInstance;
    }

    public Entity getPlayer() {
        return player;
    }

    public void setPlayer(Entity player) {
        this.player = player;
    }

    public TileBase getCurrentTile() {
        return this.currentMap.getTile(this.row, this.column);
    }

    public void setCurrentTile(TileBase currentTile) {
        this.currentTile = currentTile;
    }


    public void setCurrentMap(MapBase map) {
        this.currentMap = map;
    }

    public MapBase getCurrentMap() {
        return this.currentMap;
    }

    public int[] getPosition() {
        return new int[]{this.row,
                this.column};
    }

    public int getCurrentRow() {
        return this.row;
    }

    public int getCurrentColumn() {
        return this.column;
    }

    public void setPosition(int newRow, int newColumn) {
        this.row = newRow;
        this.column = newColumn;
    }

    /**
     * Move to tile at new coordinates, returns MoveKey enum which is processed by other functions,
     * (e.g., if it returns "tile-occupied" MoveKey, another method will determine is that occupant is an enemy
     * and respond accordingly)
     *
     * @param row    - new row position, cannot be more than one tile away from current row.
     * @param column - new column position, cannot be more than one tile away from current column.
     *               returns MoveKey, which is handled by the scene.
     **/
    public MoveKey moveTile(int row, int column) {

        int verticalDistance = Math.abs(this.getCurrentRow() - row),
                horizontalDistance = Math.abs(this.getCurrentColumn() - column);

        if (row >= this.currentMap.getRows() || column >= this.currentMap.getColumns()
                || column < 0 || row < 0 || horizontalDistance > 1 || verticalDistance > 1) {
            // Bad coordinates, do nothing.
            return MoveKey.BAD_COORDINATES;
        }

        TileBase toMove = this.currentMap.getTile(row, column);

        if (toMove.getPrimaryOccupant() == null ) {

            if (toMove instanceof LinkTile) {
                // Load new map with player located at corresponding entry point.
                return MoveKey.LINK_TO_MAP;
            }else {
                this.forceMove(row, column);
                // move successful, map needs to be updated.
                return MoveKey.MOVE_SUCCESSFUL;
            }
        } else if (toMove.getPrimaryOccupant() != null && toMove.getPrimaryOccupant() != this.player) {

           return MoveKey.TILE_OCCUPIED;

        }  else {
            return MoveKey.BAD_COORDINATES;
        }
    }
    /**
     * Move player to new tile, does not have any checks (e.g., will move player to tile regardless
     * of range of the new tile or if it has an occupant).
     * @param row - row of tile to move to.
     * @param column - column of tile to move to.
     *               !!! USE WITH CAUTION !!!
     * **/
    public void forceMove (int row, int column) {
        System.out.println(this.getCurrentRow() + " " + this.getCurrentColumn());
        this.currentMap.getTile(row, column).setPrimaryOccupant(this.player);
        this.currentMap.getTile(this.getCurrentRow(), this.getCurrentColumn()).setPrimaryOccupant(null);
        this.setPosition(row, column);
        System.out.println(this.getCurrentRow() + " " + this.getCurrentColumn());
    }

    @Override
    public void saveInstance(FileWriter saveFile) throws IOException {

        int i = 0;
        for(MapBase map : this.mapCollection.values()) {
            map.saveInstance(saveFile);
        }
        player.saveInstance(saveFile);
        saveFile.write(getCurrentMap().hashCode() + "\n");
        saveFile.write(row + "\n");
        saveFile.write(column + "\n");

    }

    public void loadGame(String fileName) throws FileNotFoundException {
        reset();
        Scanner sc = new Scanner(new File(fileName));
        MapLoader mapLoader = new MapLoader();

        // Load first map into current map.
        String header = sc.nextLine();
        System.out.println("205 "+header);

        try {
            while (header.equals("START-MAP")) {
                MapBase map = mapLoader.load(header, sc);
                mapCollection.put(map.hashCode(), map);
                sc.nextLine(); // Catch "END-MAP"
                header = sc.nextLine();
            }

            // Load Player
            player = (new EntityLoader()).load(header, sc);
            System.out.println("217: " + player == null);
        }catch (SaveLoader.LeaveFunction e){
            // Do nothing, LeaveFunction not applicable here.
        }

        // Load current map
        currentMap = mapCollection.get(Integer.parseInt(sc.nextLine()));

        // Load position
        row = Integer.parseInt(sc.nextLine());
        column = Integer.parseInt(sc.nextLine());

        // Set current tile
        currentTile = currentMap.getTile(row,column);

        // Place player in their position.
        currentMap.addEntity(player,row,column);

        // Close scanner
        sc.close();
    }
    private static void reset() {
        Navigator.uniqueInstance = new Navigator(null,new ArrayList<MapBase>(),
                new RectangularMap(1,"Dummy Map"),0,0);
    }

    public HashMap<Integer, MapBase> getMapCollection() {
        return mapCollection;
    }
}