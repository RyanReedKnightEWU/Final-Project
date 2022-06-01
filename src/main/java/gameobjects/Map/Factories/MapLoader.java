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

        MapBase map = new RectangularMap(Integer.parseInt(sc.nextLine()), Integer.parseInt(sc.nextLine()),
                sc.nextLine());
        sc.nextLine();
        //TileBase[][] tileMatrix = new TileBase[map.getRows()][map.getColumns()];

        for(int i = 0; i < map.getRows(); i++) {
            String tileHeader = sc.nextLine(); //occupantHeader = sc.nextLine();
            for (int j = 0; !tileHeader.equals(SaveLoader.getEndArrKey()); j++) {

                map.addTile((new TileLoader()).load(tileHeader, sc),i,j);
                tileHeader = sc.nextLine();
                System.out.println("Header\t" + tileHeader);
            }
        }
        return map;
    }

    @Override
    public MapBase[] loadArray(Scanner sc) {
        return new MapBase[0];
    }
}
