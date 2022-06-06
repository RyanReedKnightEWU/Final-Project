package gameobjects.Tile;
import gameobjects.Entity.Entity;
import gameobjects.SaveLoader.Savable;
import gameobjects.SaveLoader.SaveLoader;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Base class for map tiles, which can hold occupants (entities) and links to different maps.
 * */
public abstract class TileBase implements Savable {

    /**
     * The tile's occupant. Must be removed if its health goes to or below 0.
     * */
    private Entity primaryOccupant;

    /**
     * String to mark a null entity.
     * */
    private static final String nullEntity = "NULL-ENTITY";

    /**
     * @param primaryOccupant can be null.
     * */
    public TileBase(Entity primaryOccupant) {
        this.primaryOccupant = primaryOccupant;
    }

    /**
     * @param primaryOccupant Entity to set to primary occupant
     * */
    public void setPrimaryOccupant(Entity primaryOccupant) {
        this.primaryOccupant = primaryOccupant;
    }

    /**
     * @return primaryOccupant
     * */
    public Entity getPrimaryOccupant() {
        return this.primaryOccupant;
    }

    /**
     * Save the state of tile to a text file.
     * @param saveFile FileWriter is not closed in this method.
     * @throws IOException due to FileWriter
     * */
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
