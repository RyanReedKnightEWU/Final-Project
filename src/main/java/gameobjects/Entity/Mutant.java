package gameobjects.Entity;

import gameobjects.Items.Armor;
import gameobjects.Items.Armors.Clothes;
import gameobjects.Items.Consumable;
import gameobjects.Items.Items;
import gameobjects.Items.Weapon;
import gameobjects.Items.Weapons.BareHands;

public class Mutant extends Entity {

    public Mutant(String name) {
        super(200, 25, 3, name);
        Weapon weapon = new BareHands();
        setWeapon(weapon);
        Armor armor = new Clothes(0);
        setArmor(armor);
    }

    public Mutant(String name, Weapon weapon, Armor armor) {
        super(200,25,3,name);
        setWeapon(weapon);
        setArmor(armor);
    }
}