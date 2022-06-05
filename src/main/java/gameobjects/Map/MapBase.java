package gameobjects.Map;

import gameobjects.Entity.Entity;
import gameobjects.SaveLoader.Savable;
import gameobjects.SaveLoader.SaveLoader;
import gameobjects.Tile.LinkTile;
import gameobjects.Tile.SaveLoad.TileLoader;
import gameobjects.Tile.Tile;
import gameobjects.Tile.TileBase;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Map class which contains the entities.
 * */
public abstract class MapBase implements Savable, Comparable<MapBase> {

    /**
     * Matrix of tiles that make up the map.
     * */
    private final TileBase[][] tileMatrix;

    /**
     * Used to distinguish separate maps
     * */
    private final String identifier;

    /**
     * @param rows the number of rows in the map's tile matrix.
     * @param columns the number of columns in the map's tile matrix.
     * @param identifier string to identify the map.
     * */
    protected MapBase(int rows, int columns, String identifier) {
        this.identifier = identifier;

        if (rows < 0) {
            rows = 0;
        }
        else if(columns < 0) {
            columns = 0;
        }
        this.tileMatrix = new TileBase[rows][columns];
        this.fillMap();

    }

    /**Adds tile to specified index, used to add LinkTiles.
    * @param tile - tile to be added.
    * @param row - row tile will be added to.
    * @param column - column tile will be added to.
    * @throws IllegalArgumentException if row less than 0 OR row greater than this.rows -1 OR column is less than 0 OR
    *    column is greater than this.columns -1
    * **/
    public void addTile(TileBase tile, int row, int column) {

        // Check that column and row are within bounds of tileMatrix
        if (row >= this.getRows()) {
            row = this.getRows() - 1;
        }
        else if (row < 0) {
            row = 0;
        }
        else if (column >= this.getColumns()) {
            column = this.getColumns() - 1;
        }
        else if (column < 0) {
            column = 0;
        }
        this.tileMatrix[row][column] = tile;
    }

    /**
     * @return the number of rows in the tile matrix.
     * */
    public int getRows() {
        return this.tileMatrix.length;
    }

    /**
     * @return the number of columns in the tile matrix.
     * */
    public int getColumns() {
        return this.tileMatrix[0].length;
    }

    /**
     * Returns the tile at the given row and column.
     * @param row the row the tile is on (index 0).
     * @param colum the column the tile is on (index 0).
     * @return tile at the position.
     * @throws IllegalArgumentException if the bounds are less than 0 or are grater than or equal to outer bounds.
     * */
    public TileBase getTile(int row, int colum) throws IllegalArgumentException {
        checkBounds(row, colum);

        return tileMatrix[row][colum];
    }

   /**
    * Add an entity to the given index.
    * @param entity the entity being added.
    * @param row the row the entity is placed on (index 0).
    * @param column the column the entity is placed on (index 0).
    * @throws IllegalArgumentException if bounds are less than 0 or greater than or equal to the outer bounds.
    * */
    public void addEntity(Entity entity, int row, int column) throws IllegalArgumentException {
        checkBounds(row, column);
        tileMatrix[row][column].setPrimaryOccupant(entity);
    }

    /**
     * Used to check the bounds of row and column input in other methods.
     * @param row the row being checked.
     * @param column the column being checked.
     * @throws IllegalAccessException if either param is less than 0, or if row is less than this.row,
     * or if column is less than this.column.
     * */
    private void checkBounds(int row, int column) throws IllegalArgumentException {
        if (row < 0 || row >= getRows() || column < 0 || column >= getColumns()) {
            throw new IllegalArgumentException("bad param addTile, row must be no less than 0 and no greater than " +
                    (getRows() - 1) + ".\ncolumn must be no less than 0 and no greater than " +
                    (getColumns() - 1) + ". (row is " + row + "\tcolumn is " + column + ").");
        }
    }

    /**
     * Fill map with non-null tiles.
     * */
    private void fillMap() {

        int i,j;
        for (i = 0; i < this.getRows(); i++) {
            for (j = 0; j < this.getColumns(); j++) {
                tileMatrix[i][j] = new Tile();
            }
        }

    }


    /**
     * @return identifier
     * */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Saves an instance of the map to a text file, which can be reloaded by the MapLoader class.
     * @param saveFile FileWriter used to save map, NOT CLOSED IN THIS METHOD.
     * @throws IOException due to FileWriter.
     * */
    @Override
    public void saveInstance(FileWriter saveFile) throws IOException {

        SaveLoader<TileBase> tileLoader = new TileLoader();
        saveFile.write("START-MAP\n");
        saveFile.write(this.getRows() + "\n");
        saveFile.write(this.getColumns() + "\n");
        saveFile.write(this.identifier + "\n");
        saveFile.write(this.hashCode() +"\n");

        for (TileBase[] tileArr : this.tileMatrix) {

            for (TileBase tile : tileArr ) {

                if (tile.getClass().getName().startsWith(LinkTile.class.getName())){
                    ((LinkTile)tile).saveInstance(saveFile);
                } else {
                    tile.saveInstance(saveFile);
                }

            }
            saveFile.write(SaveLoader.getEndArrKey() + "\n");
        }
        saveFile.write("END-MAP\n");
    }

    /**
     * Hashcode used to identify map in aggregating Navigator class.
     * @return hashcode.
     * */
    @Override
    public int hashCode() {
        int ret = 0;
        for(int i = 0; i < this.tileMatrix.length; i++){
            for(int j = 0; j < this.tileMatrix[0].length; j++) {
                ret += (j+1)/(1+i);
            }
        }
        return ret%100000000;
    }

    /**
     * Implementation of compareTo method, compares by identifier, rows, columns, and then matrix dimensions.
     * @param map to compare to.
     * */
    @Override
    public int compareTo(MapBase map) {

        if(!identifier.equals(map.identifier)) {
            return identifier.compareTo(map.identifier);
        }
        if(getRows()!= map.getRows()){
            return Integer.compare(getRows(),map.getRows());
        }
        if(getColumns()!=map.getColumns()){
            return Integer.compare(getColumns(),map.getColumns());
        }

        for (int i = 0; i < tileMatrix.length; i++) {
            for (int j = 0; j < tileMatrix[i].length; j++) {

                if (tileMatrix[i][j].getPrimaryOccupant() != null
                        && !tileMatrix[i][j].getPrimaryOccupant().equals(map.tileMatrix[i][j].getPrimaryOccupant())) {
                    return tileMatrix[i][j].getPrimaryOccupant().compareTo(map.tileMatrix[i][j].getPrimaryOccupant());
                } else if (map.tileMatrix[i][j].getPrimaryOccupant() !=
                        null && !map.tileMatrix[i][j].getPrimaryOccupant().equals(tileMatrix[i][j].getPrimaryOccupant())) {
                    return -1*map.tileMatrix[i][j].getPrimaryOccupant().compareTo(tileMatrix[i][j].getPrimaryOccupant());
                }
            }
        }
        return 0;
    }
    /**
     * Implementation of equals method.
     * @param obj to compare.
     * */
    @Override
    public boolean equals(Object obj){
        if (!(obj instanceof MapBase)) {
            return false;
        } else if (obj == this) {
            return true;
        } else {
            return compareTo((MapBase) obj) == 0;
        }
    }

    /**
     * Implementation of toString method.
     * */
    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder("{\t");
        for(TileBase[] tileArr : tileMatrix) {
            for (TileBase tile : tileArr) {
                if (tile.getPrimaryOccupant() != null) {
                    ret.append(tile.getPrimaryOccupant().getName());
                }else {
                    ret.append("EMPTY");
                }
                ret.append("\t");
            }
            ret.append("\n");
        }
        return ret + "\t}";
    }
}