package gameobjects.Tile.SaveLoad;

import gameobjects.SaveLoader.SaveLoader;
import gameobjects.Tile.TileBase;

import java.util.Scanner;

public class TileLoader extends SaveLoader<TileBase> {
    @Override
    public TileBase load(Scanner sc) throws LeaveFunction {
        return null;
    }

    @Override
    public TileBase[] loadArray(Scanner sc) {
        return new TileBase[0];
    }
}
