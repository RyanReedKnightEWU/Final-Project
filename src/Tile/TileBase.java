package Tile;

import Entity.Entity;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class TileBase {

    // Linked tiles
    private HashMap<Direction, TileBase> linkedTiles;
    // Primary occupant, must be alive.
    private Entity primaryOccupant;
    // Deceased occupants
    private ArrayList<Entity> deceasedOccupants;

    public TileBase(Entity primaryOccupant, ArrayList<Entity> deceasedOccupants) {

        this.primaryOccupant = primaryOccupant;
        this.deceasedOccupants = deceasedOccupants;

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



}
