package Map;


import gameobjects.Tile.Tile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    public abstract MapBase createMap(int height, int width);
    public abstract MapBase createMap(String key);



    /*
    public MapBase createOctoganolMap(int sideLength) throws IllegalArgumentException {

        MapBase map = new Map(sideLength*3,sideLength*3);



    }*/

}
