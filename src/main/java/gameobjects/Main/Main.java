package gameobjects.Main;

import gameobjects.Entity.Entity;
import gameobjects.Entity.Goblin;
import gameobjects.Items.Armor;
import gameobjects.Items.Consumable;
import gameobjects.Items.Items;
import gameobjects.Items.Weapon;
import gameobjects.Tile.GameMapFactory;
import gameobjects.Tile.MapBase;
import gameobjects.Tile.MapFactoryBase;
import gameobjects.Tile.TileBase;
import javafx.application.Application;


public class Main{

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
