package Entity;

import Items.Armor;
import Items.Armors.Clothes;
import Items.Weapon;
import Items.Weapons.BareHands;

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