package Entity;

import Items.Armor;
import Items.Armors.Clothes;
import Items.Weapon;
import Items.Weapons.BareHands;

public class Zombie extends Entity {

    public Zombie(String name) {
        super(125, 12, 0, name);
        Weapon weapon = new BareHands();
        setWeapon(weapon);
        Armor armor = new Clothes(0);
        setArmor(armor);
    }

    public Zombie(String name, Weapon weapon, Armor armor) {
        super(125,12,0,name);
        setWeapon(weapon);
        setArmor(armor);
    }
}