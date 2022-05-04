package Tile;

import Entity.Entity;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Tile {

    // Linked tiles
    private HashMap<Direction,Tile> linkedTiles;
    // Primary occupant, must be alive.
    private Entity primaryOccupant;
    // Deceased occupants
    private ArrayList<Entity> deceasedOccupants;

    public Tile() {
        this.linkedTiles = new HashMap<Direction, Tile>();

        // Fill hash map, Tiles are null by default.
        for (Direction d : Direction.values()) {

            this.linkedTiles.put(d,null);

        }

        // Primary occupant null by default.
        this.primaryOccupant = null;

        // Empty deceased occupant array list by default.
        this.deceasedOccupants = new ArrayList<Entity>();
    }

    // Set and get primary occupant
    public void setPrimaryOccupant(Entity primaryOccupant) {
        this.primaryOccupant = primaryOccupant;
    }
    public Entity getPrimaryOccupant() {
        return this.primaryOccupant;
    }

    // Get deceased occupant arrList
    public ArrayList<Entity> getDeceasedOccupants() {
        return this.deceasedOccupants;
    }

    // Add deceased occupant, returns true if occupant added,
    // false if not (if occupant is not deceased, it won't be added).
    public boolean addDeceasedOccupant(Entity entity) {

        if (!entity.isAlive()) {
            return false;
        }
        else {
            this.deceasedOccupants.add(entity);
            return true;
        }
    }

    // Set and get linked tiles
    public void setNorth(Tile north) {
        this.linkedTiles.put(Direction.N,north);
    }
    public void setNorthEast(Tile northEast) {
        this.linkedTiles.put(Direction.NE, northEast);
    }
    public void setEast(Tile east) {
        this.linkedTiles.put(Direction.E,east);
    }
    public void setSouthEast(Tile southEast) {
        this.linkedTiles.put(Direction.SE, southEast);
    }
    public void setSouth(Tile south) {
        this.linkedTiles.put(Direction.S, south);
    }
    public void setSouthWest(Tile southWest) {
        this.linkedTiles.put(Direction.SW, southWest);
    }
    public void setWest(Tile west) {
        this.linkedTiles.put(Direction.W, west);
    }
    public void setNorthWest(Tile northWest) {
        this.linkedTiles.put(Direction.NW, northWest);
    }


    public Tile getNorth() {
        return this.linkedTiles.get(Direction.N);
    }
    public Tile getNorthEast() {
        return this.linkedTiles.get(Direction.NE);
    }
    public Tile getEast() {
        return this.linkedTiles.get(Direction.E);
    }
    public Tile getSouthEast() {
        return this.linkedTiles.get(Direction.SE);
    }
    public Tile getSouth() {
        return this.linkedTiles.get(Direction.S);
    }
    public Tile getSouthWest() {
        return this.linkedTiles.get(Direction.SW);
    }
    public Tile getWest() {
        return this.linkedTiles.get(Direction.W);
    }
    public Tile getNorthWest() {
        return this.linkedTiles.get(Direction.NW);
    }

}
