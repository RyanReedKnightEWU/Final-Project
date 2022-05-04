package Entity;

import Items.Armor;
import Items.Armors.Clothes;
import Items.Weapon;
import Items.Weapons.BareHands;

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