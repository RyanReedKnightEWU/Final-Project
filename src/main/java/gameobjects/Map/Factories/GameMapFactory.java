package gameobjects.Map.Factories;

import gameobjects.Entity.*;
import gameobjects.Entity.SaveLoad.EntityLoader;
import gameobjects.Items.Armors.Clothes;
import gameobjects.Items.Armors.LeatherArmor;
import gameobjects.Items.Weapons.Pistol;
import gameobjects.Items.Weapons.Rifle;
import gameobjects.Map.MapBase;
import gameobjects.Map.RectangularMap;
import gameobjects.Tile.LinkTile;
import gameobjects.Tile.Tile;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Factory to build maps and sets of maps for the game.
 * */
public class GameMapFactory extends MapFactoryBase {

    /**
     * Create an instance of MapBase.
     * @param height number of rows.
     * @param width number of columns.
     * */
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

    /**
     * Create a map which corresponds to the given key, the key should be taken from an enum in
     * the GameMapFactoryKeys class.
     * @param key from an enum in GameMapFactoryKeys (use the toString method).
     * @return map which corresponds to the given key.
     * */
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
            map = standardArena;
        }

        return map;
    }

    /**
     * Creates the ArrayList of maps which correspond to the given key.
     * @param key from an enum in GameMapFactoryKeys (use the toString method).
     * @return set of maps that correspond to the given key.
     * */
    @Override
    public ArrayList<MapBase> createMapSet(String key) {

        ArrayList<MapBase> retList = new ArrayList<>();

        if (key.equals(GameMapFactoryKeys.STANDARD_MAP_SET.toString())) {

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

            MapBase dungeon1 = new RectangularMap(7,2,"dungeon1");



            retList.add(standardArena);
            retList.add(standardHall);
            retList.add(recRoom);

        }else if(key.equals(GameMapFactoryKeys.MAP_SET_2.toString())) {

            MapBase startMap = new RectangularMap(9,3,"start hall");
            startMap.addEntity(new Goblin("Azog"),4,0);
            startMap.addEntity(new Goblin("Marduk"),6,1);
            retList.add(startMap);

            MapBase leftBranch = new RectangularMap(3,7,"left branch");
            Mutant leif = new Mutant("Leif the Mutant",new Rifle(),
                    new LeatherArmor(1)), patrik = new Mutant("Patrik the Mutant",new Pistol(),
                    new LeatherArmor(1));

            leif.addGold(50);
            patrik.addGold(50);

            leftBranch.addEntity(leif,0,1);
            leftBranch.addEntity(patrik,2,1);

            // Create link between startMap and leftBranch.
            /*int[] startMapToLeftBranchCoor = new int[] {1,6}, leftBranchToStartUpMapCoor = new int[] {1,0};
            LinkTile startMapToLeftBranch = new LinkTile(leftBranch,null,startMapToLeftBranchCoor),
                leftBranchToStartMap = new LinkTile(startMap,null,leftBranchToStartUpMapCoor);
            startMap.addTile(startMapToLeftBranch,leftBranchToStartUpMapCoor[0],leftBranchToStartUpMapCoor[1]);
            leftBranch.addTile(leftBranchToStartMap,startMapToLeftBranchCoor[0],startMapToLeftBranchCoor[1]);
            */
            linkTwoMaps(startMap,leftBranch,new int[] {1,0}, new int[]{1,6});
            retList.add(leftBranch);

            MapBase upperMap = new RectangularMap(9,3,"top map");

            Murderbot alfred = new Murderbot("Alfred the MurderBot"),
                    greg = new Murderbot("Greg the MurderBot"),
                    evilWallE = new Murderbot("Evil Wall-E the MurderBot");

            alfred.addGold(200);
            greg.addGold(200);
            evilWallE.addGold(200);

            upperMap.addEntity(alfred,4,0);
            upperMap.addEntity(greg,3,2);
            upperMap.addEntity(evilWallE,3,1);

            // Create link between leftBranch and upperMap
            linkTwoMaps(leftBranch,upperMap,new int[]{1,0},new int[]{1,2});
            retList.add(upperMap);

            Zombie zombie1 = new Zombie("Zombie 1"),
            zombie2 = new Zombie("Zombie 2"), zombie3 = new Zombie("zombie 3");

            zombie1.addGold(50);
            zombie2.addGold(50);
            zombie3.addGold(50);

            MapBase rightBranch = new RectangularMap(3,7,"right branch");
            rightBranch.addEntity(zombie1,0,3);
            rightBranch.addEntity(zombie2,1,4);
            rightBranch.addEntity(zombie3,2,3);

            // Create link between upperMap and rightBranch
            linkTwoMaps(upperMap,rightBranch,new int[]{7,2},new int[]{1,0});
            retList.add(rightBranch);

            // Create link between rightBranch and startMap
            linkTwoMaps(rightBranch,startMap,new int[]{1,6},new int[] {7,0});

        }
        return retList;
    }

    /**
     * Private method used to create a link between two maps (mapA and mapB). The link tile displays the text "Doorway",
     * if the player moves onto this tile, they will exit the current map and emerge on the corresponding link on
     * the map being linked to.
     * @param mapA the first map.
     * @param mapB the second map.
     * @param linkCoorOnA the coordinates of the link on mapA.
     * @param linkCoorOnB the coordinates of the corresponding link on mapB.
     *
     * */
    private void linkTwoMaps(MapBase mapA, MapBase mapB, int[] linkCoorOnA, int[] linkCoorOnB) {
        LinkTile mapAToMapB = new LinkTile(mapB,null,linkCoorOnB),
        mapBToMapA = new LinkTile(mapA,null,linkCoorOnA);
        mapA.addTile(mapAToMapB,linkCoorOnA);
        mapB.addTile(mapBToMapA, linkCoorOnB);
    }


}
