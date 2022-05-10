package Map;

import gameobjects.Entity.Entity;
import gameobjects.Entity.Goblin;
import gameobjects.Entity.Murderbot;
import gameobjects.Entity.Mutant;
import gameobjects.Tile.Tile;

public class GameMapFactory extends MapFactoryBase {

    @Override
    public MapBase createMap(int height, int width) {

        MapBase map = new RectangularMap(height, width);

        // Fill map with non-null values.
        int i,j;
        for (i = 0; i < map.getRows(); i++) {

            for (j = 0; j < map.getColumns(); j++) {
                map.addTile(new Tile(),i,j);
            }

        }

        return map;
    }

    @Override
    public MapBase createMap(String key) {

        String firstArena = "first arena", secondArena = "second arena",
                hall = "hall", dungeons = "dungeons";

        int arenaDimension = 7;

        MapBase map;

        // Entities
        // Goblins
        Entity azog = new Goblin("Azog"), marduk = new Goblin("Marduk"), baal =  new Goblin("Baal"),
                // Mutants
                jean = new Mutant("Jean-Francoise"), aleric = new Mutant("Aleric"),
                // Murderbots
                evilWalee = new Murderbot("Evil Walee");


        if (key.equals(firstArena)) {
            map = new RectangularMap(arenaDimension);
            map.addEntity(azog, 3, 0);
            map.addEntity(marduk, 0, 4);
        }
        else if(key.equals(secondArena)) {
            map = new RectangularMap(arenaDimension);
            map.addEntity(jean, 6, 6);
            map.addEntity(evilWalee, 1,4);
            map.addEntity(aleric,0,3);
        }
        else if (key.equals(hall)) {
            map = new RectangularMap(5,1);
        }
        else if (key.equals(dungeons)) {
            // Store keeper/match maker will be added here.
            map = new RectangularMap(4,2);
        }
        else {
            /*String spc = ", ";
            throw new IllegalArgumentException("bad param createMap(String), select one of the follwing keys: "
                + firstArena + spc + secondArena + spc + hall + spc + dungeons);*/
            map = new RectangularMap(4);
        }

        return map;
    }

}
