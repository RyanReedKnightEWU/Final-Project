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

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
/**
 * Keeps track of the current player, their location on the current map,
 * and which map (out of a group of maps) they are one.
 * Saves and loads games.
 * Implements Savable interface.
 * */
public class Navigator implements Savable{
    /**
     * Single instance of the navigator in use.
     * */
    private static Navigator uniqueInstance;

    /**
     * The map currently occupied by the player.
     * */
    private MapBase currentMap;

    /**
     * A hashmap of the maps in the game, each map's in the set uses its hashcode as a key. Necessary for loading maps.
     * */
    private final HashMap<Integer,MapBase> mapCollection;

    /**
     * Tile on the current map the player occupies.
     * */
    private TileBase currentTile;

    /**
     * The coordinates of the player on the map, index 0.
     * */
    private int row, column;

    /**
     * The current player.
     * */
    private Entity player;

    /**
     * Boolean indicating whether the game is a new game built in the program, or one that has been
     * loaded from a text file. Necessary in other parts of the program.
     * */
    public boolean newGame;

    /**
     * Defines Navigator, called in setState method.
     * @param player - Entity to represent user.
     * @param mapSet - set of maps to be included in this instance of the game.
     * @param playerColumnPosition - horizontal coordinate of tile on tile matrix
     * @param playerRowPosition - vertical coordinate of tile on tile matrix
     *
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
        this.newGame = true;

    }

    /**
     * Sets the state of the navigator.
     * @param player - the player.
     * @param mapSet - the set of maps to be used in this instance of the game.
     * @param currentMap - the map the player occupies.
     * @param column - column the player occupies.
     * @param row - row the player occupies.
     * @return Navigator.uniqueInstance.
     *
     * */
    public static Navigator setState(Entity player, ArrayList<MapBase> mapSet,MapBase currentMap, int column, int row) {

        Navigator.uniqueInstance = new Navigator(player, mapSet,currentMap, column, row);

        return Navigator.uniqueInstance;
    }

    /**
     * Get the current instance of the navigator, if one has not been defined yet,
     * define a one with null and dummy values.
     * @return Navigator.uniqueInstance
     * */
    public static Navigator getInstance() {
        if(Navigator.uniqueInstance == null) {
            reset();
        }
        return Navigator.uniqueInstance;
    }

    /**
     * @return this.player
     * */
    public Entity getPlayer() {
        return player;
    }

    /**
     * @param player - the player.
     * */
    public void setPlayer(Entity player) {
        this.player = player;
    }

    /**
     * @return tile the player ocupies.
     * */
    public TileBase getCurrentTile() {
        return this.currentMap.getTile(this.row, this.column);
    }

    /**
     * @param currentTile - the tile being set as the current tile.
     * */
    public void setCurrentTile(TileBase currentTile) {
        this.currentTile = currentTile;
    }

    /**
     * Used when moving from one map to another.
     * @param map to set to current.
     * */
    public void setCurrentMap(MapBase map) {
        this.currentMap = map;
    }
    /**
     * @return this.currentMap, the map the player occupies.
     * */
    public MapBase getCurrentMap() {
        return this.currentMap;
    }

    /**
     * @return an array of the row and column which define the players position.
     * */
    public int[] getPosition() {
        return new int[]{this.row,
                this.column};
    }

    /**
     * Get the row (index 0) of the player on the current map.
     * @return this.row
     * */
    public int getCurrentRow() {
        return this.row;
    }
    /**
     * Get the column (index 0) of the player on the current map.
     * @return this.column
     * */
    public int getCurrentColumn() {
        return this.column;
    }
    /**
     * Column and row are index 0. Places player in the new position on the map.
     * @param newRow - row of the position player is moving to.
     * @param newColumn - column of the new position player is moving to.
     *
     * */
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
     * !!! USE WITH CAUTION !!!
     * @param row - row of tile to move to.
     * @param column - column of tile to move to.
     * **/
    public void forceMove (int row, int column) {
        System.out.println(this.getCurrentRow() + " " + this.getCurrentColumn());
        this.currentMap.getTile(row, column).setPrimaryOccupant(this.player);
        this.currentMap.getTile(this.getCurrentRow(), this.getCurrentColumn()).setPrimaryOccupant(null);
        this.setPosition(row, column);
        System.out.println(this.getCurrentRow() + " " + this.getCurrentColumn());
    }

    /**
     *  Saves the state of the game to a file named saveFileName. Saves the maps which
     *  the games use. After this, the player is saved, then the hashcode of the map, and
     *  then the players position on the current map. FileWriter opened and closed in method.
     * @param saveFileName - name of the file to be saves, method appends a ".txt" suffix if it is not included.
     * @throws IOException - due to FileWriter.
     * */
    public void saveInstance(String saveFileName) throws IOException {

        saveFileName = appendTxtSuffix(saveFileName);

        FileWriter saveFile = new FileWriter(saveFileName);
        saveInstance(saveFile);
        saveFile.close();
    }
    /**
     * Other than the parameter, identical to saveFile(String).
     * @param saveFile - instance of FileWriter, if this method is used it must be closed outside the method.
     * @throws IOException - due to FileWriter.
     * */
    @Override
    public void saveInstance(FileWriter saveFile) throws IOException {

        for(MapBase map : this.mapCollection.values()) {
            map.saveInstance(saveFile);
        }
        player.saveInstance(saveFile);
        saveFile.write(getCurrentMap().hashCode() + "\n");
        saveFile.write(row + "\n");
        saveFile.write(column + "\n");

    }

    /**
     * Loads an instance of the game previously saved by saveInstance method.
     * @param fileName - name of file being loaded.
     * @throws FileNotFoundException - if file is not found.
     * */
    public void loadGame(String fileName) throws FileNotFoundException {


        fileName = appendTxtSuffix(fileName);
        reset();
        Scanner sc = new Scanner(new File(fileName));
        MapLoader mapLoader = new MapLoader();

        // Load first map into current map.
        String header = sc.nextLine();
        System.out.println("205 "+header);


        while (header.equals("START-MAP")) {
            MapBase map = mapLoader.load(header, sc);
            mapCollection.put(map.hashCode(), map);
            sc.nextLine(); // Catch "END-MAP"
            header = sc.nextLine();
        }

        // Load Player
        player = (new EntityLoader()).load(header, sc);


        // Load current map
        currentMap = mapCollection.get(Integer.parseInt(sc.nextLine()));

        // Load position
        row = Integer.parseInt(sc.nextLine());
        column = Integer.parseInt(sc.nextLine());

        // Set current tile
        currentTile = currentMap.getTile(row,column);

        // Place player in their position.
        currentMap.addEntity(player,row,column);

        this.newGame = false;

        // Close scanner
        sc.close();
    }
    /**
     * Sets values in navigator to values that are either null, or to placeholder values.
     * */
    private static void reset() {
        Navigator.uniqueInstance = new Navigator(null,new ArrayList<MapBase>(),
                new RectangularMap(1,"Dummy Map"),0,0);
    }

    public HashMap<Integer, MapBase> getMapCollection() {
        return mapCollection;
    }

    /**
     * To be used when feeding a method a txt file name,
     * if there is no txt suffix at the end of the file,
     * appends it to fileName.
     * @param fileName - name of file.
     * */
    private String appendTxtSuffix(String fileName) {

        // If file name does not have text file suffix, append it.
        String sfx = ".txt";
        if (!fileName.endsWith(sfx)) {
            return fileName + sfx;
        }else {
            return fileName;
        }
    }
}