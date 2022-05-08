package gameobjects.Entity;

import gameobjects.Items.Armor;
import gameobjects.Items.Armors.Clothes;
import gameobjects.Items.Consumable;
import gameobjects.Items.Items;
import gameobjects.Items.Weapon;
import gameobjects.Items.Weapons.BareHands;

public class Murderbot extends Entity {

    public Murderbot(String name) {
        super(150, 20, 10, name);
        Weapon weapon = new BareHands();
        setWeapon(weapon);
        Armor armor = new Clothes(0);
        setArmor(armor);
    }

    public Murderbot(String name, Weapon weapon, Armor armor) {
        super(150,20,10,name);
        setWeapon(weapon);
        setArmor(armor);
    }
}