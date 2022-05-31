package gameobjects.Entity.SaveLoad;

import gameobjects.Entity.*;
import gameobjects.Items.Armor;
import gameobjects.Items.ItemLoader;
import gameobjects.Items.Items;
import gameobjects.Items.Weapon;
import gameobjects.Items.Weapons.WeaponFactory;
import gameobjects.SaveLoader.SaveLoader;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class EntityLoader extends SaveLoader<Entity> {
    @Override
    public Entity load(Scanner sc) throws LeaveFunction {
        return load(sc.nextLine(),sc);
    }

    public Entity load(String subclass, Scanner sc) throws LeaveFunction {

        if (subclass.equals("NULL-ENTITY")) {
            return null;
        }

        ItemLoader itemLoader = new ItemLoader();

        int health = Integer.parseInt(sc.nextLine());
        int maxHealth = sc.nextInt();
        int damage = sc.nextInt();
        int defense = sc.nextInt();
        sc.nextLine();
        String name = sc.nextLine();
        Items[] arr = itemLoader.loadArray(sc);
        ArrayList<Items> inventory = new ArrayList<>(List.of(arr));
        Weapon weapon = (Weapon) itemLoader.load(sc);
        Armor armor = (Armor) itemLoader.load(sc);

        if (subclass.equals(Goblin.class.getName())) {
            return new Goblin(health, maxHealth, damage, defense, name,inventory, weapon, armor);
        } else if (subclass.equals(Bandit.class.getName())) {
            return new Bandit(health,maxHealth,damage,defense,name,inventory,weapon,armor);
        } else if (subclass.equals(Murderbot.class.getName())) {
            return new Murderbot(health,maxHealth,damage,defense,name,inventory,weapon,armor);
        } else if(subclass.equals(Mutant.class.getName())) {
            return new Mutant(health,maxHealth,damage,defense,name,inventory,weapon,armor);
        } else if (subclass.equals(Zombie.class.getName())) {
            return new Zombie(health,maxHealth,damage,defense,name,inventory,weapon,armor);
        } else if (subclass.equals(Player.class.getName())) {
            return new Player(health,maxHealth,damage,defense,name,inventory,weapon,armor);
        }else if(subclass.equals("END-ARR")) {
            throw new LeaveFunction();
        } else {
            return null;
        }
    }


    @Override
    public Entity[] loadArray(Scanner sc) {
        LinkedList<Entity> entityList = new LinkedList<>();
        try {
            entityList.add(load(sc));
            while(true) {
                entityList.add(load(sc));
            }

        } catch (LeaveFunction e) {
            Entity[] arr = new Entity[entityList.size()];
            return entityList.toArray(arr);
        }
    }
}
