package Main;

import Entity.Entity;
import Entity.Goblin;
import Navigator.Navigator;
import Tile.GameMapFactory;
import Tile.MapBase;
import Tile.MapFactoryBase;
import Tile.TileBase;

public class Main {

    public static void main(String[] args) {

        Entity entity;
        entity = new Goblin("Grimble");
        System.out.println(entity);
        entity.basicAttack();
        entity.takeDamage(15);
        entity.takeDamage(0);
        entity.takeDamage(70);

        MapFactoryBase gameMapFactory = new GameMapFactory();
        MapBase arena = gameMapFactory.createRectangularMap(10, 15),
            dungeons = gameMapFactory.createRectangularMap(4,6);

        System.out.println(arena);
    }

}
