package gameobjects.Tile;
import gameobjects.Entity.Entity;
import gameobjects.SaveLoader.Savable;
import gameobjects.SaveLoader.SaveLoader;

import java.io.FileWriter;
import java.io.IOException;

public abstract class TileBase implements Savable {

    // Primary occupant, must be alive.
    private Entity primaryOccupant;

    private static final String nullEntity = "NULL-ENTITY";
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

    public static String getNullEntity() {
        return nullEntity;
    }

    @Override
    public void saveInstance(FileWriter saveFile) throws IOException {

        saveFile.write(this.getClass().getName() + "\n");

        if (this.primaryOccupant != null) {
            this.primaryOccupant.saveInstance(saveFile);
        }else {
            saveFile.write(nullEntity + "\n");
        }
    }
}
