package gameobjects.Tile.SaveLoad;

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
    @Override
    public TileBase load(Scanner sc) throws LeaveFunction {
        String header = sc.nextLine();
        TileBase tile;
        
        if (header.startsWith(Tile.class.getName())) {
            return new Tile((new EntityLoader()).load(sc));
        } else if (header.startsWith(LinkTile.class.getName())) {

            return new LinkTile((new EntityLoader()).load(sc),
                    new int[]{Integer.parseInt(sc.nextLine()), Integer.parseInt(sc.nextLine())},
                    Integer.parseInt(sc.nextLine()));

        } else if (header.equals("END-ARR")){
            throw new LeaveFunction();
        } else if (header.equals("NULL-ENTITY")){
            System.out.println(header);
            tile = new Tile(null);
        }
        return null;
    }

    @Override
    public TileBase[] loadArray(Scanner sc) throws LeaveFunction {

        LinkedList<TileBase> tileLst = new LinkedList<>();
        String header = sc.nextLine();
        try {
            while (true) {
                tileLst.add(load(sc));
            }
        }catch (LeaveFunction e){
            TileBase[] tileArr = new TileBase[tileLst.size()];
            return tileLst.toArray(tileArr);
        }
    }
}
