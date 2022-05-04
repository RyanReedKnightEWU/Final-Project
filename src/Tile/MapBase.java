package Tile;

public abstract class MapBase {

    private TileBase [][] tileMatrix;
    private final int rows, columns;

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
        if (row < 0 || row >= this.rows || column < 0 || column >= this.columns) {
            throw new IllegalArgumentException("bad param addTile, row must be no less than 0 and no greater than " +
                    Integer.toString(this.rows - 1) + ".\ncolumn must be no less than 0 and no greter than " +
                    Integer.toString(this.columns - 1) + ".");
        }

        this.tileMatrix[row][column] = tile;

    }


    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    @Override
    public String toString() {

        return "blah";

    }


}
