package gameobjects.Tile;

import gameobjects.Entity.Entity;

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

    public TileBase getTile(int row, int colum) throws IllegalArgumentException {
        this.checkBounds(row, colum);

        return this.tileMatrix[row][colum];
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
                    Integer.toString(this.columns - 1) + ". (row is " + Integer.toString(row) + "\tcolumn is " + Integer.toString(column) + ").");
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        StringBuilder tst = new StringBuilder();

        int squareSize = 10,i,j,n = 0,m = 0;
        for (i = 0; i < squareSize*(this.rows + 1); i++) {
            for (j = 0; j < squareSize*(this.columns +1); j++) {

                if( (toStringHelper(i,squareSize)|| toStringHelper(j,squareSize)) && i >= (squareSize-1) && j >= (squareSize -1)) {
                    stringBuilder.append("*  ");
                }
                else if(this.tileMatrix[n][m].getPrimaryOccupant() != null) {
                    stringBuilder.append(this.tileMatrix[n][m].getPrimaryOccupant().getName());
                        j += this.getTile(n, m).getPrimaryOccupant().getName().length();
                }
                else {
                    stringBuilder.append("   ");
                }
                if ((j+1)%squareSize == 0 && j >= (squareSize -1) && j < this.columns) {
                    ++m;
                }
            }
            m = 0;

            if ((i+1)%squareSize == 0 && i >= (squareSize -1) && i < this.rows) {
                ++n;
            }
            tst.append(n).append('\t').append(m).append('\n');
            stringBuilder.append("\n");
        }

        System.out.println(tst);

        return stringBuilder.toString();

    }

    private boolean toStringHelper(int n, int squareSize) {
        return squareSize%(n+1) == 0 || (n+1)%squareSize == 0;
    }

}