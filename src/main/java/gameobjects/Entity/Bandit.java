package gameobjects.Entity;

import gameobjects.Items.Armor;
import gameobjects.Items.Armors.Clothes;
import gameobjects.Items.Consumable;
import gameobjects.Items.Items;
import gameobjects.Items.Weapon;
import gameobjects.Items.Weapons.BareHands;

public class Bandit extends Entity {

    public Bandit(String name) {
        super(100, 15, 5, name);
        Weapon weapon = new BareHands();
        setWeapon(weapon);
        Armor armor = new Clothes(0);
        setArmor(armor);
    }

    public Bandit(String name, Weapon weapon, Armor armor) {
        super(100,15,5,name);
        setWeapon(weapon);
        setArmor(armor);
    }
}