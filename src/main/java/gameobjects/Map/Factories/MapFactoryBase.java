package gameobjects.Map.Factories;


import gameobjects.Map.MapBase;
import gameobjects.SaveLoader.SaveLoader;

import java.util.ArrayList;

/**
 * Factory to make maps of various different dimensions
 * Abstract so gameobjects.Map factories which inherit it can focus more on game specific details.
 * **/
public abstract class MapFactoryBase extends SaveLoader<MapBase> {
    /**
     * Make rectangular map,
     * @throws IllegalArgumentException if height or width is less than 0.
     * @param height - border height
     * @param width - border width
     * **/
    public abstract MapBase createMap(int height, int width, String identifier);
    public abstract MapBase createMap(String key);
    public abstract ArrayList<MapBase> createMapSet(String key);


    /*
    public MapBase createOctoganolMap(int sideLength) throws IllegalArgumentException {

        MapBase map = new gameobjects.Map(sideLength*3,sideLength*3);



    }*/

}
