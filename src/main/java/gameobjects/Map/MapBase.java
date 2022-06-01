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

public abstract class MapBase implements Savable, Comparable<MapBase> {

    private TileBase[][] tileMatrix;
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

    public void setMatrix(TileBase[][] matrix) {
        tileMatrix = matrix;
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

    public void setRow(TileBase[] tileArr, int row) {

        if (row < 0) {
            row = 0;
        } else if (row >= getRows()) {
            row = getRows() - 1;
        }

        if (getColumns() >= 0) {
            System.arraycopy(tileArr, 0, tileMatrix[row], 0, getColumns());
        }
    }
    @Override
    public void saveInstance(FileWriter saveFile) throws IOException {

        SaveLoader<TileBase> tileLoader = new TileLoader();
        saveFile.write("START-MAP\n");
        saveFile.write(this.getRows() + "\n");
        saveFile.write(this.getColumns() + "\n");
        saveFile.write(this.identifier + "\n");
        saveFile.write(this.hashCode() +"\n");

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
                /*if (this.tileMatrix[i][j] instanceof LinkTile) {
                    ret += (j+1)/(1+i)*2;
                }else*/{
                    ret += (j+1)/(1+i);
                }
            }
        }
        return ret%100000000;
    }

    @Override
    public int compareTo(MapBase map) {
        if (identifier.equals(map.getIdentifier())) {
            if (getRows() == map.getRows()) {
                if (getColumns() == map.getColumns()) {
                    for (int i = 0; i < tileMatrix.length; i++) {
                        for (int j = 0; j < tileMatrix[i].length; j++) {

                            if (tileMatrix[i][j].getClass().getName().equals(
                                    map.tileMatrix[i][j].getClass().getName())) {

                                if (tileMatrix[i][j] instanceof Tile &&
                                        !((Tile) tileMatrix[i][j]).equals((Tile) map.tileMatrix[i][j])) {
                                    return ((Tile) tileMatrix[i][j]).compareTo((Tile) map.tileMatrix[i][j]);
                                } else if (tileMatrix[i][j] instanceof LinkTile &&
                                        !((LinkTile) tileMatrix[i][j]).equals((LinkTile) map.tileMatrix[i][j])) {
                                    return ((LinkTile) tileMatrix[i][j]).compareTo((LinkTile) map.tileMatrix[i][j]);
                                }
                            }else {
                                return tileMatrix[i][j].getClass().getName().compareTo(
                                        map.tileMatrix[i][j].getClass().getName());
                            }
                        }
                    }
                    return 0;
                }
                return Integer.compare(getColumns(), map.getColumns());
            }
            return Integer.compare(getRows(), map.getRows());
        }
       return identifier.compareTo(map.identifier);
    }
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