package gameobjects.Map.Factories;

import gameobjects.Entity.Entity;
import gameobjects.Entity.SaveLoad.EntityLoader;
import gameobjects.Map.MapBase;
import gameobjects.Map.RectangularMap;
import gameobjects.SaveLoader.SaveLoader;
import gameobjects.Tile.LinkTile;
import gameobjects.Tile.SaveLoad.TileLoader;
import gameobjects.Tile.Tile;
import gameobjects.Tile.TileBase;

import java.util.Scanner;

public class MapLoader extends SaveLoader<MapBase> {
    @Override
    public MapBase load(Scanner sc) throws LeaveFunction {
        return load(sc.nextLine(),sc);
    }

    public MapBase load(String mapHeader, Scanner sc) throws LeaveFunction {

        // Loaders
        TileLoader tileLoader = new TileLoader();

        int rows = Integer.parseInt(sc.nextLine());
        int columns = Integer.parseInt(sc.nextLine());
        String identifier = sc.nextLine();
        sc.nextLine(); // Catch hashcode, which does not need to be loaded.

        // Make map.
        MapBase map = new RectangularMap(rows,columns,identifier);
        TileBase tile;

        for(int i = 0; i < rows; i++) {
            String header = sc.nextLine();
            for (int j = 0;!header.equals(SaveLoader.getEndArrKey());j++){
                tile = tileLoader.load(header,sc);
                map.addTile(tile,i,j);
                header = sc.nextLine();
            }
        }
        return map;
    }

    @Override
    public MapBase[] loadArray(Scanner sc) {
        return new MapBase[0];
    }
}
