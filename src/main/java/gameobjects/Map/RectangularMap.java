package gameobjects.Map;

/**
 * Subclass to implement MapBase
 * */
public class RectangularMap extends MapBase {

    /**
     * Builds rectangular map.
     * @param rows number of rows.
     * @param columns number of columns.
     * @param identifier map identifier.
     * */
    public RectangularMap(int rows, int columns, String identifier) {
        super(rows, columns, identifier);
    }

    /**
     * Builds square map
     * @param rowsAndColumns number of rows and columns (the same in square map).
     * @param identifier map identifier.
     * */
    public RectangularMap(int rowsAndColumns, String identifier) {
        super(rowsAndColumns, rowsAndColumns, identifier);
    }

}
