package gameobjects.Tile.SaveLoad;

import gameobjects.Entity.Entity;
import gameobjects.Entity.Player;
import gameobjects.Entity.SaveLoad.EntityLoader;
import gameobjects.SaveLoader.SaveLoader;
import gameobjects.Tile.LinkTile;
import gameobjects.Tile.Tile;
import gameobjects.Tile.TileBase;
import javafx.print.PageLayout;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Loader for tiles, extends SaveLoader.
 * */
public class TileLoader extends SaveLoader<TileBase> {

    /**
     * Rebuild tile saved with its saveInstance method.
     * @param header which contains the result of getClass().getName() from the object before it was saved.
     * @param  sc Scanner used to read file, not closed in this method.
     * @return rebuilt tile
     * */
    public TileBase load(String header, Scanner sc) {

        if (header.startsWith(Tile.class.getName())) {
            Entity ent = (new EntityLoader()).load(sc);
            return new Tile(ent);
        } else if (header.startsWith(LinkTile.class.getName())) {

            Entity entity = (new EntityLoader()).load(sc);
            int hash =  Integer.parseInt(sc.nextLine());
            int[] position = new int[]{Integer.parseInt(sc.nextLine()), Integer.parseInt(sc.nextLine())};
            return new LinkTile(entity, position,hash);

        } else {
            return null;
        }
    }

    /**
     * Rebuild tile saved with its saveInstance method.
     * @param  sc Scanner used to read file, not closed in this method.
     * @return rebuilt tile
     * */
    @Override
    public TileBase load(Scanner sc) {
       return load(sc.nextLine(),sc);
    }

    /**
     * Load an array of tiles.
     * @param sc scanner used to read file, not closed in this method.
     * @return array of rebuilt tiles.
     * */
    @Override
    public TileBase[] loadArray(Scanner sc) {

        LinkedList<TileBase> tileLst = new LinkedList<>();
        String header = sc.nextLine();
        TileBase[] tileArr;

        while(!header.equals(SaveLoader.getEndArrKey())) {
            tileLst.add(load(header,sc));
        }

        tileArr = new TileBase[tileLst.size()];
        return tileLst.toArray(tileArr);
    }


}
