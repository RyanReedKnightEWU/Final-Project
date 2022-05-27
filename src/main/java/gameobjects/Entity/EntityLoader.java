package gameobjects.Entity;

import gameobjects.Items.Armor;
import gameobjects.Items.ItemLoader;
import gameobjects.Items.Items;
import gameobjects.Items.Weapon;
import gameobjects.Items.Weapons.WeaponFactory;
import gameobjects.SaveLoader.SaveLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EntityLoader extends SaveLoader<Entity> {
    @Override
    public Entity load(Scanner sc) throws LeaveFunction {

        SaveLoader<Items> itemLoader = new ItemLoader();

        String subclass = sc.nextLine(),name;
        int health = sc.nextInt();
        int maxHealth = sc.nextInt();
        int damage = sc.nextInt();
        int defense = sc.nextInt();
        sc.nextLine();
        name = sc.nextLine();
        Items[] arr = itemLoader.loadArray(sc);
        ArrayList<Items> inventory = new ArrayList<>(List.of(arr));
        Weapon weapon = (Weapon) itemLoader.load(sc);
        Armor armor = (Armor) itemLoader.load(sc);

        if (subclass.equals(Goblin.class.getName())) {
            return new Goblin(health, maxHealth, damage, defense, name,inventory, weapon, armor);
        }


        return null;
    }

    @Override
    public Entity[] loadArray(Scanner sc) {
        return new Entity[0];
    }
}
