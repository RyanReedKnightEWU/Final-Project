package gameobjects.Tile;

import Map.MapBase;
import gameobjects.Entity.Entity;

import java.util.ArrayList;
import java.util.HashMap;

public class LinkTile extends TileBase {

    // Link to new map.
    private MapBase linkToNewMap;

    private MapBase linkToMap;

    // Position player emerges at on new map.
    private final int row, column;

    public LinkTile(MapBase map, Entity primaryOccupant, int row, int column) {
        super(primaryOccupant, null);
        this.row = row;
        this.column = column;
        this.linkToMap = map;
    }

    public int getRowOnNewMap() {
        return this.row;
    }
    public int getColumnOnNewMap() {
        return this.column;
    }
    public int[] getPosition() {
        return new int[] {this.row, this.column };
    }

    public MapBase getLinkToMap() {
        return this.linkToMap;
    }

    /**
     * Set map link
     * @param map - MapBase
     * **/
    public void setLinkToMap(MapBase map) {

        this.linkToMap = map;

    }
}
