package gameobjects.Main;

import gameobjects.Entity.Entity;
import gameobjects.Entity.Goblin;
import Map.GameMapFactory;
import Map.MapBase;
import Map.MapFactoryBase;


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
        MapBase arena, dungeons;

        try {
            arena = gameMapFactory.createMap("first-arena");
            dungeons = gameMapFactory.createMap("dungeons");
        }
        catch(Exception e){
            System.out.println("EXCEPTION");
        }


    }

}
