package gameobjects.Entity;

import gameobjects.Items.Armor;
import gameobjects.Items.Armors.Clothes;
import gameobjects.Items.Consumable;
import gameobjects.Items.Items;
import gameobjects.Items.Weapon;
import gameobjects.Items.Weapons.BareHands;

import java.util.ArrayList;

public class Mutant extends Entity {

    public Mutant(String name) {
        super(200, 25, 3, name);
        Weapon weapon = new BareHands();
        addItem(weapon);
        setWeapon(0);
        Armor armor = new Clothes(0);
        addItem(armor);
        setArmor(1);
    }

    public Mutant(String name, Weapon weapon, Armor armor) {
        super(200,25,3,name);
        addItem(weapon);
        setWeapon(0);
        addItem(armor);
        setArmor(1);
    }
    public Mutant(int health, int maxHealth, int damage, int defense, String name, ArrayList inventory,
                  Weapon weapon, Armor armor) {
        super(health, maxHealth, damage, defense, name, inventory, weapon, armor);
    }
}