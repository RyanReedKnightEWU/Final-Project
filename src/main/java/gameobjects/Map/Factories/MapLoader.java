package gameobjects.Map.Factories;

import gameobjects.Map.MapBase;
import gameobjects.Map.RectangularMap;
import gameobjects.SaveLoader.SaveLoader;
import gameobjects.Tile.SaveLoad.TileLoader;
import gameobjects.Tile.TileBase;

import java.util.Scanner;

public class MapLoader extends SaveLoader<MapBase> {
    @Override
    public MapBase load(Scanner sc) throws LeaveFunction {
        return load(sc.nextLine(),sc);
    }

    public MapBase load(String mapHeader, Scanner sc) throws LeaveFunction {

        MapBase map = new RectangularMap(Integer.parseInt(sc.nextLine()), Integer.parseInt(sc.nextLine()),
                sc.nextLine());
        sc.nextLine();
        TileLoader loader = new TileLoader();
        for(int i = 0; i < map.getRows(); i++) {
            loader.loadArray(sc);
        }
        return map;
    }

    @Override
    public MapBase[] loadArray(Scanner sc) {
        return new MapBase[0];
    }
}
