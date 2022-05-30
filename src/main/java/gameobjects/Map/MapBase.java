package gameobjects.Map;

import gameobjects.Entity.Entity;
import gameobjects.SaveLoader.Savable;
import gameobjects.SaveLoader.SaveLoader;
import gameobjects.Tile.SaveLoad.TileLoader;
import gameobjects.Tile.Tile;
import gameobjects.Tile.TileBase;

import java.io.FileWriter;
import java.io.IOException;

public abstract class MapBase implements Savable {

    private final TileBase[][] tileMatrix;
    private final String identifier;

    /*
    private class MapLinks {
        protected MapBase linkedMap;
        protected int[] currentMapTileCoordinate;
        protected int linkedMapTileCoordinate;
    }*/

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

    /**Adds tile to specified index
    * @param tile - tile to be added.
    * @param row - row tile will be added to.
    * @param column - column tile will be added to.
    * @throws IllegalArgumentException if row less than 0 OR row greater than this.rows -1 OR column is less than 0 OR
    *    column is greater than this.columns -1
    * !!!!DO NOT ENTER THE SAME TILE REFERENCE INTO THIS METHOD MULTIPLE TIMES!!!!
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


    public int getRows() {
        return this.tileMatrix.length;
    }

    public int getColumns() {
        return this.tileMatrix[0].length;
    }

    public TileBase getTile(int row, int colum) throws IllegalArgumentException {
        this.checkBounds(row, colum);

        return this.tileMatrix[row][colum];
    }

    public TileBase getTile(int[] position) {
        if (position.length != 2) {
            return null;
        }
        else {
            return this.getTile(position[0],position[1]);
        }
    }


    public void addEntity(Entity entity, int row, int column) {
        /*
        this.checkBounds(row,column);

        if (this.tileMatrix[row][column].getPrimaryOccupant() != null) {
            throw new Exception("cannot add Entity to occupied tile.");
        }*/


        this.tileMatrix[row][column].setPrimaryOccupant(entity);

    }

    private void checkBounds(int row, int column) throws IllegalArgumentException {
        if (row < 0 || row >= this.getRows() || column < 0 || column >= this.getColumns()) {
            throw new IllegalArgumentException("bad param addTile, row must be no less than 0 and no greater than " +
                    Integer.toString(this.getRows() - 1) + ".\ncolumn must be no less than 0 and no greater than " +
                    Integer.toString(this.getColumns() - 1) + ". (row is " + Integer.toString(row) + "\tcolumn is " + Integer.toString(column) + ").");
        }
    }

    private void fillMap() {

        int i,j;
        for (i = 0; i < this.getRows(); i++) {
            for (j = 0; j < this.getColumns(); j++) {
                this.tileMatrix[i][j] = new Tile();
            }
        }

    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public void saveInstance(FileWriter saveFile) throws IOException {

        SaveLoader<TileBase> tileLoader = new TileLoader();
        saveFile.write("START-MAP\n");
        saveFile.write(Integer.toString(this.hashCode())+"\n");

        for (TileBase[] t : this.tileMatrix) {
            SaveLoader.saveArray(t,saveFile);
        }
        saveFile.write("END-MAP\n");
    }

    @Override
    public int hashCode() {
        int ret = 0;
        for(int i = 0; i < this.tileMatrix.length; i++){
            for(int j = 0; j < this.tileMatrix[0].length; j++) {
                if (this.tileMatrix[i][j].getPrimaryOccupant() != null) {
                    ret += this.tileMatrix[i][j].getPrimaryOccupant().hashCode()*(j+1)/(1+i);
                }else{
                    ret += (j+1)/(1+i);
                }
            }
        }
        return ret%100000000;
    }
}