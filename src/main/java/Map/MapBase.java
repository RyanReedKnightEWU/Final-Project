package Map;

import gameobjects.Entity.Entity;
import gameobjects.Tile.Tile;
import gameobjects.Tile.TileBase;

public abstract class MapBase {

    private TileBase[][] tileMatrix;
    private final int rows, columns;

    /*
    private class MapLinks {
        protected MapBase linkedMap;
        protected int[] currentMapTileCoordinate;
        protected int linkedMapTileCoordinate;
    }*/

    protected MapBase(int rows, int columns) {

        if (rows < 0) {
            rows = 0;
        }
        else if(columns < 0) {
            columns = 0;
        }

        this.rows = rows;
        this.columns = columns;
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
        if (row >= this.rows) {
            row = this.rows - 1;
        }
        else if (row < 0) {
            row = 0;
        }
        else if (column >= this.columns) {
            column = this.columns - 1;
        }
        else if (column < 0) {
            column = 0;
        }
        this.tileMatrix[row][column] = tile;
    }


    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
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
        if (row < 0 || row >= this.rows || column < 0 || column >= this.columns) {
            throw new IllegalArgumentException("bad param addTile, row must be no less than 0 and no greater than " +
                    Integer.toString(this.rows - 1) + ".\ncolumn must be no less than 0 and no greater than " +
                    Integer.toString(this.columns - 1) + ". (row is " + Integer.toString(row) + "\tcolumn is " + Integer.toString(column) + ").");
        }
    }

    private void fillMap() {

        int i,j;
        for (i = 0; i < this.rows; i++) {
            for (j = 0; j < this.columns; j++) {
                this.tileMatrix[i][j] = new Tile();
            }
        }

    }

}