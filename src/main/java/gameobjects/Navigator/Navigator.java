package gameobjects.Navigator;

import Map.MapBase;
import gameobjects.Entity.Entity;
import gameobjects.Tile.LinkTile;
import gameobjects.Tile.Tile;
import gameobjects.Tile.TileBase;

import java.util.HashMap;

import static gameobjects.Navigator.Attack.attack;

public class Navigator {

    private static Navigator uniqueInstance;
    private MapBase currentMap;
    private TileBase currentTile;
    private int row, column;
    private Entity player;

    /**
     * Defines Navigator, called in getInstance method if uniqueInstance is not null.
     *
     * @param player               - Entity to represent user.
     * @param map                  - the current map the player is on.
     * @param playerColumnPosition - horizontal coordinate of tile on tile matrix
     * @param playerRowPosition    - vertical coordinate of tile on tile matrix
     **/
    private Navigator(Entity player, MapBase map, int playerRowPosition, int playerColumnPosition) {

        this.player = player;
        this.currentMap = map;
        this.row = playerRowPosition;
        this.column = playerColumnPosition;
        this.currentMap.addEntity(player, playerRowPosition, playerColumnPosition);
    }

    // Getters and setters.
    public static Navigator getInstance(Entity player, MapBase map, int xCoordinate, int yCoordinate) {

        if (Navigator.uniqueInstance == null) {

            Navigator.uniqueInstance = new Navigator(player, map, xCoordinate, yCoordinate);

        }

        return Navigator.uniqueInstance;

    }

    //
    public static Navigator getInstance() {
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
     * Move to tile at new coordinates, returns string which is processed by other functions,
     * (e.g., if it returns "tile-occupied", another method will determine is that occupant is an enemy
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

        if (toMove.getPrimaryOccupant() == this.player) {
            return MoveKey.CURRENT_TILE;
        }else if (toMove.getPrimaryOccupant() == null) {

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

}