package Entity;

import Items.Armor;
import Items.Armors.Clothes;
import Items.Weapon;
import Items.Weapons.BareHands;

public class Goblin extends Entity {

    public Goblin(String name) {
        //Find a more proper way to let an entity subclass pass its stats to Entity.
        super(75, 10, 3, name);
        Weapon weapon = new BareHands();
        setWeapon(weapon);
        Armor armor = new Clothes(0);
        setArmor(armor);
    }
}
