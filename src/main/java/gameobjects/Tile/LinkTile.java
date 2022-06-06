package gameobjects.Tile;

import gameobjects.Map.MapBase;
import gameobjects.Entity.Entity;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Tile which contains a link to a new map.
 * */
public class LinkTile extends TileBase implements Comparable<LinkTile> {

    /**
     * Hashcode of map this tile links to, used to return linked map in Navigator class which
     * has a hashmap mapping each map's hashcode to itself.
     * */
    private int newMapHashValue;

    /** Position player emerges at on new map.*/
    private final int row, column;

    /**
     * @param primaryOccupant Entity occupying the map.
     * @param position array containing the players coordinates on the new map if they use this link
     * @param hashCode the hash code of the map this tile links to.
     * */
    public LinkTile (Entity primaryOccupant, int[] position, int hashCode) {
        super(primaryOccupant);
        this.row = position[0];
        this.column = position[1];
        this.newMapHashValue = hashCode;
    }

    /**
     * @param map map this tile links to.
     * @param occupant Entity occupying the map.
     * @param linkMapRow row the player emerges on the new map if they use this link.
     * @param linkMapColumn column the player emerges on the new map if they use this link.
     * */
    public LinkTile(MapBase map, Entity occupant, int linkMapRow, int linkMapColumn) {
        super(occupant);
        row = linkMapRow;
        column = linkMapColumn;
        newMapHashValue = map.hashCode();
    }

    /**
     * @param map map this tile links to.
     * @param occupant occupant to occupy this tile.
     * @param position position player emerges on if they use this link.
     * */
    public LinkTile(MapBase map, Entity occupant, int[] position) {
        super(occupant);
        row = position[0];
        column = position[1];
        newMapHashValue = map.hashCode();
    }

    /**
     * @return row player emerges to on new map.
     * */
    public int getRowOnNewMap() {
        return this.row;
    }

    /**
     * @return column player emerges to on new map if they use this link.
     * */
    public int getColumnOnNewMap() {
        return this.column;
    }

    /**
     * @return position player emerges on new map if they use this link.
     * */
    public int[] getPosition() {
        return new int[] {this.row, this.column };
    }

    /**
     * @return hashcode of map this tile links to.
     * */
    public int getNewMapHashValue() {
        return newMapHashValue;
    }

    /**
     * @param newMapHashValue hashcode of map this tile links to.
     * */
    public void setNewMapHashValue(int newMapHashValue) {
        this.newMapHashValue = newMapHashValue;
    }

    /**
     * Implementation of saveInstance method, saves the state of this tile to a text file.
     * Overrides saveInstance in TileBase because there are additional fields in LinkTile.
     * @param saveFile FileWriter used to save instance, not closed in this method.
     * @throws IOException due to FIleWriter.
     * */
    @Override
    public void saveInstance(FileWriter saveFile) throws IOException {
        super.saveInstance(saveFile);
        saveFile.write(newMapHashValue +"\n");
        saveFile.write(row + "\n");
        saveFile.write(column + "\n");
    }

    /**
     * Compares by primary occupant, linked map hashcode, rows, and then columns.
     * @param tile link tile being compared.
     * */
    @Override
    public int compareTo(LinkTile tile) {
        if(tile == null) {
            return 1;
        } else if (tile == this) {
            return 0;
        }

        if(getPrimaryOccupant()!= null && !getPrimaryOccupant().equals(tile.getPrimaryOccupant())) {
            return getPrimaryOccupant().compareTo(tile.getPrimaryOccupant());
        }else if (getPrimaryOccupant()==null && tile.getPrimaryOccupant()!=null) {
            return 1;
        }
        if(newMapHashValue != tile.newMapHashValue) {
            return Integer.compare(newMapHashValue,tile.newMapHashValue);
        }
        if(row!=tile.row) {
            return Integer.compare(row,tile.row);
        }
        if(column != tile.column) {
            return Integer.compare(column,tile.column);
        }
        return 0;
    }

    /**
     * @param obj Object to be compared.
     * */
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
