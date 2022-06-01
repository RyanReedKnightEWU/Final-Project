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

public class TileLoader extends SaveLoader<TileBase> {

    public TileBase load(String header, Scanner sc) throws LeaveFunction {

        if (header.startsWith(Tile.class.getName())) {
            Entity ent = (new EntityLoader()).load(sc);
            return new Tile(ent);
        } else if (header.startsWith(LinkTile.class.getName())) {

            Entity entity = (new EntityLoader()).load(sc);
            int hash =  Integer.parseInt(sc.nextLine());
            int[] position = new int[]{Integer.parseInt(sc.nextLine()), Integer.parseInt(sc.nextLine())};
            return new LinkTile(entity, position,hash);

        } else if (header.equals("END-ARR")){
            throw new LeaveFunction();
        }else {
            return null;
        }
    }

    @Override
    public TileBase load(Scanner sc) throws LeaveFunction {
       return load(sc.nextLine(),sc);
    }

    @Override
    public TileBase[] loadArray(Scanner sc) throws LeaveFunction {

        LinkedList<TileBase> tileLst = new LinkedList<>();
        String header = sc.nextLine();
        TileBase[] tileArr;

        try {
            while (true) {
                tileLst.add(load(sc));
            }
        }catch (LeaveFunction e){
            tileArr = new TileBase[tileLst.size()];
        }
        return tileLst.toArray(tileArr);
    }


}
