package gameobjects.Map.Factories;

import gameobjects.Entity.Entity;
import gameobjects.Entity.Goblin;
import gameobjects.Entity.Murderbot;
import gameobjects.Entity.Mutant;
import gameobjects.Entity.SaveLoad.EntityLoader;
import gameobjects.Items.Armors.Clothes;
import gameobjects.Items.Weapons.Pistol;
import gameobjects.Map.MapBase;
import gameobjects.Map.RectangularMap;
import gameobjects.Tile.LinkTile;
import gameobjects.Tile.Tile;

import java.util.ArrayList;
import java.util.Scanner;

public class GameMapFactory extends MapFactoryBase {

    @Override
    public MapBase createMap(int height, int width, String indentifier) {

        MapBase map = new RectangularMap(height, width, indentifier);

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

        MapBase standardArena = new RectangularMap(arenaDimension,arenaDimension,"ARENA-A"),
                standardHall = new RectangularMap(4,1,"HALL-A"),
            recRoom = new RectangularMap(4,2,"RECROOM-A");


        if (key.equals(GameMapFactoryKeys.FIRST_ARENA.toString())) {
            map = standardArena;
            map.addEntity(azog, 3, 0);
            map.addEntity(marduk, 0, 4);
        } else if(key.equals(GameMapFactoryKeys.SECOND_ARENA.toString())) {
            map = standardArena;
            map.addEntity(jean, 6, 6);
            map.addEntity(evilWalee, 1,4);
            map.addEntity(aleric,0,3);
        } else if (key.equals(GameMapFactoryKeys.HALL.toString())) {
            map = standardHall;
        } else if (key.equals(GameMapFactoryKeys.DUNGEONS.toString())) {
            // Store keeper/match maker will be added here.
            map = new RectangularMap(4,2,"Rectangular gameobjects.Map");
        }else {
            /*String spc = ", ";
            throw new IllegalArgumentException("bad param createMap(String), select one of the follwing keys: "
                + firstArena + spc + secondArena + spc + hall + spc + dungeons);*/
            map = standardArena;
        }

        return map;
    }

    @Override
    public ArrayList<MapBase> createMapSet(String key) {
        if (key.equals(GameMapFactoryKeys.STANDARD_MAP.toString())) {

            Entity azog = new Goblin("Azog"), marduk = new Goblin("Marduk"), baal =  new Goblin("Baal"),
                    // Mutants
                    jean = new Mutant("Jean-Francoise"), aleric = new Mutant("Aleric"),
                    // Murderbots
                    evilWalee = new Murderbot("Evil Walee");

            MapBase standardArena = new RectangularMap(7,7,"ARENA-A"),
                    standardHall = new RectangularMap(4,1,"HALL-A"),
                    recRoom = new RectangularMap(4,2,"RECROOM-A");

            int[] arenaToHallHallPosition = new int[]{3, 0}, hallToArenaArenaPosition = new int[]{0, 3};

            LinkTile arenaToHallLink = new LinkTile(standardHall, null, arenaToHallHallPosition),
                    hallToArenaLink = new LinkTile(standardArena, null, hallToArenaArenaPosition);

            standardArena.addEntity(new Murderbot("Lary",new Pistol(),new Clothes(5)),1,1);
            standardArena.addTile(arenaToHallLink, 0, 3);
            standardHall.addTile(hallToArenaLink, 3, 0);
            standardHall.addTile(new LinkTile(recRoom, null, 0, 0), 0, 0);
            standardArena.addEntity(new Goblin("Azog"), 3, 0);
            standardArena.addEntity(new Goblin("Marduk"), 2, 2);
            recRoom.addTile(new LinkTile(standardHall, null, 0, 0), 0, 0);

            ArrayList<MapBase> retList = new ArrayList<>();
            retList.add(standardArena);
            retList.add(standardHall);
            retList.add(recRoom);


            return retList;

        }else {
            return null;
        }
    }

    @Override
    public MapBase load(Scanner sc) throws LeaveFunction {
        MapBase map = new RectangularMap(Integer.parseInt(sc.nextLine()),Integer.parseInt(sc.nextLine()),sc.nextLine());

        for(int i = 0; i < map.getRows(); i++) {
            Entity[] entityArr = (new EntityLoader()).loadArray(sc);

            for(int j = 0; j < map.getColumns(); j++) {
                //map.addTile();
            }

        }
        return null;
    }

    @Override
    public MapBase[] loadArray(Scanner sc) {
        return new MapBase[0];
    }
}
