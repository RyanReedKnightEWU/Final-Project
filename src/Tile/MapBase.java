package Tile;

import Entity.Entity;

public abstract class MapBase {

    private TileBase [][] tileMatrix;
    private final int rows, columns;

    /*
    private class MapLinks {
        protected MapBase linkedMap;
        protected int[] currentMapTileCoordinate;
        protected int linkedMapTileCoordinate;
    }*/

    protected MapBase(int rows, int columns) throws IllegalArgumentException {

        if (rows < 0 || columns < 0) {
            throw new IllegalArgumentException("bad param MapBase constructor");
        }

        this.rows = rows;
        this.columns = columns;
        this.tileMatrix = new TileBase[rows][columns];

    }

    /**Adds tile to specified index
    * @param tile - tile to be added.
    * @param row - row tile will be added to.
    * @param column - column tile will be added to.
    * @throws IllegalArgumentException if row less than 0 OR row greater than this.rows -1 OR column is less than 0 OR
    *    column is greater than this.columns -1
    * !!!!DO NOT ENTER THE SAME TILE REFERENCE INTO THIS METHOD MULTIPLE TIMES!!!!
    * **/
    public void addTile(TileBase tile, int row, int column) throws IllegalArgumentException {

        // Throw illegal argument exception if index is out of bounds,
        // so this will not disrupt gameplay.
       this.checkBounds(row, column);

        this.tileMatrix[row][column] = tile;

    }


    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }


    public void addEntity(Entity entity, int row, int column) throws Exception {

        this.checkBounds(row,column);

        if (this.tileMatrix[row][column].getPrimaryOccupant() != null) {
            throw new Exception("cannot add Entity to occupied tile.");
        }

        this.tileMatrix[row][column].setPrimaryOccupant(entity);

    }

    private void checkBounds(int row, int column) throws IllegalArgumentException {
        if (row < 0 || row >= this.rows || column < 0 || column >= this.columns) {
            throw new IllegalArgumentException("bad param addTile, row must be no less than 0 and no greater than " +
                    Integer.toString(this.rows - 1) + ".\ncolumn must be no less than 0 and no greater than " +
                    Integer.toString(this.columns - 1) + ".");
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        int squareSize = 5,i,j;
        for (i = 0; i < squareSize*this.rows; i++) {
            for (j = 0; j < squareSize*this.columns; j++) {

                if(squareSize%(i+1) == 0 || (i+1)%squareSize == 0 ||
                        squareSize%(j+1) == 0|| (j+1)%squareSize == 0) {

                    stringBuilder.append("*  ");
                }

                else {
                    stringBuilder.append("   ");
                }



            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();

    }

}
