package gameobjects.Map;

public class RectangularMap extends MapBase {


    public RectangularMap(int rows, int columns, String identifier) {
        super(rows, columns, identifier);
    }

    public RectangularMap(int rowsAndColumns, String identifier) {
        super(rowsAndColumns, rowsAndColumns, identifier);
    }

}
