package gameobjects.Entity;

import gameobjects.Items.Armor;
import gameobjects.Items.Armors.Clothes;
import gameobjects.Items.Consumable;
import gameobjects.Items.Items;
import gameobjects.Items.Weapon;
import gameobjects.Items.Weapons.BareHands;

public class Goblin extends Entity {

    public Goblin(String name) {
        super(75, 15, 3, name);
        Weapon weapon = new BareHands();
        setWeapon(weapon);
        Armor armor = new Clothes(0);
        setArmor(armor);
    }

    public Goblin(String name, Weapon weapon, Armor armor) {
        super(75,15,3,name);
        setWeapon(weapon);
        setArmor(armor);
    }
}
