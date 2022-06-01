package gameobjects.Tile;

import gameobjects.Map.MapBase;
import gameobjects.Entity.Entity;

import java.io.FileWriter;
import java.io.IOException;

public class LinkTile extends TileBase implements Comparable<LinkTile> {

    // Link to new map.
    private int newMapHashValue;

    // Position player emerges at on new map.
    private final int row, column;
    /*
    public LinkTile(MapBase map, Entity primaryOccupant, int row, int column) {
        super(primaryOccupant);
        this.row = row;
        this.column = column;
        this.linkToMap = map;
        this.newMapHashValue = map.hashCode();
    }
    public LinkTile(MapBase map, Entity primaryOccupant, int[] position) {
        super(primaryOccupant);
        this.row = position[0];
        this.column = position[1];
        this.linkToMap = map;
        this.newMapHashValue = map.hashCode();
    }*/

    public LinkTile (Entity primaryOccupant, int[] position, int hashCode) {
        super(primaryOccupant);
        this.row = position[0];
        this.column = position[1];
        this.newMapHashValue = hashCode;
    }

    public LinkTile(MapBase map, Entity occupant, int linkMapRow, int linkMapColumn) {
        super(occupant);
        row = linkMapRow;
        column = linkMapColumn;
        newMapHashValue = map.hashCode();
    }

    public LinkTile(MapBase map, Entity occupant, int[] position) {
        super(occupant);
        row = position[0];
        column = position[1];
        newMapHashValue = map.hashCode();
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

    public int getNewMapHashValue() {
        return newMapHashValue;
    }

    public void setNewMapHashValue(int newMapHashValue) {
        this.newMapHashValue = newMapHashValue;
    }

    @Override
    public void saveInstance(FileWriter saveFile) throws IOException {
        super.saveInstance(saveFile);
        saveFile.write(newMapHashValue +"\n");
        saveFile.write(row + "\n");
        saveFile.write(column + "\n");
    }

    @Override
    public int compareTo(LinkTile tile) {
        if(tile == null) {
            return -1;
        } else if (tile == this) {
            return 0;
        }

        if (getPrimaryOccupant().equals(tile.getPrimaryOccupant())){
            if(hashCode() == tile.hashCode()) {
                if(row == tile.row) {
                    if(column == tile.column) {
                        return 0;
                    }
                    return Integer.compare(column,tile.column);
                }
                return Integer.compare(row,tile.row);
            }
            return Integer.compare(newMapHashValue,tile.newMapHashValue);
       }
       return getPrimaryOccupant().compareTo(tile.getPrimaryOccupant());
    }
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof LinkTile)) {
            return false;
        } else if (obj == this) {
            return true;
        } else {
            return compareTo((LinkTile) obj) == 0;
        }
    }
}
