package gameobjects.Tile;

import gameobjects.Entity.Entity;
import gameobjects.Entity.Goblin;
import gameobjects.Map.MapBase;
import gameobjects.Map.RectangularMap;
import gameobjects.SaveLoader.SaveLoader;
import gameobjects.Tile.SaveLoad.TileLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TestSaveLoad {

    public static void main(String[] args) throws IOException, SaveLoader.LeaveFunction {

        MapBase map = new RectangularMap(5,"ALPHA");

        Entity ent = new Goblin("Azog");

        TileBase t1 = new LinkTile(ent,new int[]{1,1},map.hashCode());
        TileBase t2;

        File tile = new File("tile.txt");
        FileWriter fileWriter = new FileWriter(tile);
        t1.saveInstance(fileWriter);
        fileWriter.close();

        Scanner sc = new Scanner(tile);

        t2 = (new TileLoader()).load(sc);
        System.out.println(t1.getPrimaryOccupant().equals(t2.getPrimaryOccupant()));
        System.out.println(map.hashCode());
        System.out.println(((LinkTile)t1).equals((LinkTile)t2));
        System.out.println(((LinkTile)t1).compareTo((LinkTile) t2));
    }
}
