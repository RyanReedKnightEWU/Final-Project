package gameobjects.Tile;

import gameobjects.Map.MapBase;
import gameobjects.Entity.Entity;

import java.io.FileWriter;
import java.io.IOException;

public class LinkTile extends TileBase {

    // Link to new map.
    private MapBase linkToNewMap;
    private int newMapHashValue;

    private MapBase linkToMap;

    // Position player emerges at on new map.
    private final int row, column;

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
    }

    public LinkTile (Entity primaryOccupant, int[] position, int hashCode) {
        super(primaryOccupant);
        this.row = position[0];
        this.column = position[1];
        this.linkToMap = null;
        this.newMapHashValue = hashCode;
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

    public void setLinkToNewMap(MapBase linkToNewMap) {
        this.linkToNewMap = linkToNewMap;
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
        saveFile.write(Integer.toString(linkToMap.hashCode())+"\n");
        saveFile.write(Integer.toString(this.row) + "\n");
        saveFile.write(Integer.toString(this.column) + "\n");
    }
}
