package gameobjects.Main;

import gameobjects.Entity.Entity;
import gameobjects.Entity.Goblin;
import gameobjects.Items.Armor;
import gameobjects.Items.Armors.LeatherArmor;
import gameobjects.Items.Consumable;
import gameobjects.Items.Consumables.healthPotion;
import gameobjects.Items.Factories.EntityFactory;
import gameobjects.Items.Factories.FantasyPAEntityFactory;
import gameobjects.Items.Factories.WeaponFactory;
import gameobjects.Items.Items;
import gameobjects.Items.Weapon;
import gameobjects.Items.Weapons.Pistol;

import gameobjects.Navigator.MoveKey;
import javafx.application.Application;
import Map.GameMapFactory;
import Map.MapBase;
import Map.MapFactoryBase;

import java.util.ArrayList;


public class Main{

    public static void main(String[] args) {


        MoveKey key1 = MoveKey.MOVE_SUCCESSFUL;
        MoveKey key2 = MoveKey.FAILED_ATTACK;

        System.out.println(key1 == key2);

        Entity entity1;
        Entity entity2;
        Entity entity3;

        EntityFactory entFactory = new FantasyPAEntityFactory();
        entity1 = entFactory.createEntity("goblin");
        System.out.println(entity1);
        entity1.basicAttack();
        entity1.takeDamage(15);

        System.out.println();
        Weapon weapon = new Pistol();
        Armor armor = new LeatherArmor(5);
        entity2 = entFactory.createEntity("goblin", weapon, armor);
        System.out.println(entity2);
        entity2.basicAttack();
        entity2.takeDamage(15);

        System.out.println();
        Weapon weapon2 = new Pistol();
        Armor armor2 = new LeatherArmor(5);
        ArrayList<Items> inventory = null;
        entity3 = entFactory.createEntity(50, 100, 15, 5, "bandit", inventory,
                                            weapon2, armor2);
        System.out.println(entity3);
        entity3.basicAttack();
        entity3.takeDamage(15);

        Consumable potion = new healthPotion(5);
        Consumable potion2 = new healthPotion(5);
        System.out.println();
        entity1.printInventory();
        System.out.println();
        entity1.addItem(weapon);
        entity1.addItem(armor);
        entity1.addConsumable(potion);
        entity1.addConsumable(potion2);
        entity1.printInventory();

        System.out.println();
        entity1.removeConsumable(potion);
        entity1.removeItem(armor);
        entity1.printInventory();

        System.out.println();
        String entSave = entity1.saveString();
        System.out.println(entSave);

/*
        MapFactoryBase gameMapFactory = new GameMapFactory();
        MapBase arena, dungeons;

        try {
            arena = gameMapFactory.createMap("first-arena");
            dungeons = gameMapFactory.createMap("dungeons");
        }
        catch(Exception e){
            System.out.println("EXCEPTION");
        }
*/

    }

}
