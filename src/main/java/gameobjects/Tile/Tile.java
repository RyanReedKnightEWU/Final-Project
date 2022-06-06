package gameobjects.Tile;

import gameobjects.Entity.Entity;


import java.io.FileWriter;
import java.io.IOException;

/**
 * Standard tile, extends TileBase and implements Comparable.
 * */
public class Tile extends TileBase implements Comparable<Tile> {

    /**
     * Default constructor builds tile with null occupant
     * */
    public Tile() {
        super(null);
    }
    /**
     * Constructor builds Tile with occupant.
     * @param entity to be set as the occupant.
     * */
    public Tile(Entity entity) {
        super(entity);
    }

    /**
     * If a tile has a null occupant, the tile with a non-null occupant is considered greater.
     * If both tiles contain non-null entities, returns a compareTo call from this tile's occupant,
     * passing in the occupant of the tile parameter.
     * @param tile tile to be compared.
     * */
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

    /**
     * @param obj object to be compared.
     * */
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
