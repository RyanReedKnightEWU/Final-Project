package Entity;

import Items.Armor;
import Items.Armors.Clothes;
import Items.Weapon;
import Items.Weapons.BareHands;

public class Goblin extends Entity {

    public Goblin(String name) {
        super(75, 10, 3, name);
        Weapon weapon = new BareHands();
        setWeapon(weapon);
        Armor armor = new Clothes(0);
        setArmor(armor);
    }

    public Goblin(String name, Weapon weapon, Armor armor) {
        super(75,10,3,name);
        setWeapon(weapon);
        setArmor(armor);
    }
}
