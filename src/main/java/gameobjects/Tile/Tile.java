package gameobjects.Tile;

import gameobjects.Entity.Entity;


import java.io.FileWriter;
import java.io.IOException;

public class Tile extends TileBase implements Comparable<Tile> {

    public Tile() {
        super(null);
    }
    public Tile(Entity entity) {
        super(entity);
    }


    @Override
    public int compareTo(Tile tile) {
        if(tile == null) {
            return -1;
        } else if (tile == this) {
            return 0;
        } else if (getPrimaryOccupant() == null && tile.getPrimaryOccupant() != null){
            return 1;
        }else if(getPrimaryOccupant() == null && tile.getPrimaryOccupant() == null){
            return 0;
        }else {
            return getPrimaryOccupant().compareTo(tile.getPrimaryOccupant());
        }
    }
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Tile)) {
            return false;
        } else if (obj == this) {
            return true;
        } else {
            return compareTo((Tile) obj) == 0;
        }
    }
}
