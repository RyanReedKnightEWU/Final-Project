package Entity;

import Items.Armor;
import Items.Armors.Clothes;
import Items.Weapon;
import Items.Weapons.BareHands;

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