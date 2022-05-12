package gameobjects.Entity;

import gameobjects.Items.Armor;
import gameobjects.Items.Armors.Clothes;
import gameobjects.Items.Consumable;
import gameobjects.Items.Items;
import gameobjects.Items.Weapon;
import gameobjects.Items.Weapons.BareHands;

import java.util.ArrayList;

public class Goblin extends Entity {

    public Goblin(String name) {
        super(75, 15, 3, name);
        Weapon weapon = new BareHands();
        addItem(weapon);
        setWeapon(0);
        Armor armor = new Clothes(0);
        setArmor(armor);
    }

    public Goblin(String name, Weapon weapon, Armor armor) {
        super(75,15,3,name);
        setWeapon(weapon);
        setArmor(armor);
    }
    public Goblin(int health, int maxHealth, int damage, int defense, String name, ArrayList inventory,
                  Weapon weapon, Armor armor) {
        super(health, maxHealth, damage, defense, name, inventory, weapon, armor);
    }
}
