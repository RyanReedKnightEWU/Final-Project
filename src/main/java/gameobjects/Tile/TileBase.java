package gameobjects.Tile;
import gameobjects.Entity.Entity;

public abstract class TileBase {

    // Primary occupant, must be alive.
    private Entity primaryOccupant;
    // Deceased occupants

    public TileBase(Entity primaryOccupant) {
        this.primaryOccupant = primaryOccupant;
    }

    // Set and get primary occupant
    public void setPrimaryOccupant(Entity primaryOccupant) {
        this.primaryOccupant = primaryOccupant;
    }
    public Entity getPrimaryOccupant() {
        return this.primaryOccupant;
    }

}
