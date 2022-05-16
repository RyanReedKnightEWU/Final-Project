package Map;

import gameobjects.Entity.Entity;
import gameobjects.Entity.Goblin;
import gameobjects.Entity.Murderbot;
import gameobjects.Entity.Mutant;
import gameobjects.Items.Consumables.AttackBottle;
import gameobjects.Items.Weapons.Pistol;
import gameobjects.Tile.LinkTile;
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

        int arenaDimension = 7;

        MapBase map;

        // Entities
        // Goblins
        Entity azog = new Goblin("Azog"), marduk = new Goblin("Marduk"), baal =  new Goblin("Baal"),
                // Mutants
                jean = new Mutant("Jean-Francoise"), aleric = new Mutant("Aleric"),
                // Murderbots
                evilWalee = new Murderbot("Evil Walee");




        MapBase standardArena = new RectangularMap(arenaDimension), standardHall = new RectangularMap(4,1);
        standardArena.addTile(new LinkTile(standardHall,null,3,0),0,3);
        standardHall.addTile(new LinkTile(standardArena,null,0,3),3,0);

        if (key.equals(GameMapFactoryKeys.FIRST_ARENA.toString())) {
            map = standardArena;
            map.addEntity(azog, 3, 0);
            map.addEntity(marduk, 0, 4);
        }
        else if(key.equals(GameMapFactoryKeys.SECOND_ARENA.toString())) {
            map = standardArena;
            map.addEntity(jean, 6, 6);
            map.addEntity(evilWalee, 1,4);
            map.addEntity(aleric,0,3);
        }
        else if (key.equals(GameMapFactoryKeys.HALL.toString())) {
            map = standardHall;
        }
        else if (key.equals(GameMapFactoryKeys.DUNGEONS.toString())) {
            // Store keeper/match maker will be added here.
            map = new RectangularMap(4,2);
        }
        else {
            /*String spc = ", ";
            throw new IllegalArgumentException("bad param createMap(String), select one of the follwing keys: "
                + firstArena + spc + secondArena + spc + hall + spc + dungeons);*/
            map = standardArena;
        }

        return map;
    }

}
