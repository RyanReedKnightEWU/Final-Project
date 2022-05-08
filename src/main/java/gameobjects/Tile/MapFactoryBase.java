package gameobjects.Tile;


/**
 * Factory to make maps of various different dimensions
 * Abstract so Map factories which inherit it can focus more on game specific details.
 * **/
public abstract class MapFactoryBase {

    /**
     * Make rectangular map,
     * @throws IllegalArgumentException if height or width is less than 0.
     * @param height - border height
     * @param width - border width
     * **/
    public MapBase createRectangularMap(int height, int width) throws IllegalArgumentException {

        MapBase map = new Map(height, width);

        // Fill map with non-null values.
        int i,j;
        for (i = 0; i < map.getRows(); i++) {

            for (j = 0; j < map.getColumns(); j++) {
                map.addTile(new Tile(),i,j);
            }

        }

        return map;
    }

    public MapBase createSquareMap(int length) throws IllegalArgumentException {
        return this.createRectangularMap(length, length);
    }
    /*
    public MapBase createOctoganolMap(int sideLength) throws IllegalArgumentException {

        MapBase map = new Map(sideLength*3,sideLength*3);



    }*/

}
