package gameobjects.Tile;

import Map.MapBase;
import gameobjects.Entity.Entity;

import java.util.ArrayList;
import java.util.HashMap;

public class LinkTile extends TileBase {

    // Link to new map.
    private MapBase linkToNewMap;

    // Position player emerges at on new map.
    private final int row, column;

    public LinkTile(Entity primaryOccupant, ArrayList<Entity> deceasedOccupants, int row, int column) {
        super(primaryOccupant, deceasedOccupants);
        this.row = row;
        this.column = column;
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
}
