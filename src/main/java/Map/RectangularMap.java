package Map;

public class RectangularMap extends MapBase {


    public RectangularMap(int rows, int columns) {
        super(rows, columns);
    }

    public RectangularMap(int rowsAndColumns) {
        super(rowsAndColumns, rowsAndColumns);
    }

}
