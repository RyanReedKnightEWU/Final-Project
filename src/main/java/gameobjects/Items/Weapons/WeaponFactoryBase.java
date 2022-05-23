package gameobjects.Items.Weapons;

import gameobjects.Items.ItemFactoryBase;
import gameobjects.Items.Items;
import gameobjects.Items.Weapon;

public abstract class WeaponFactoryBase extends ItemFactoryBase {
    public abstract Weapon createWeapon(String key, String name, int minDamage,
                                        int maxDamage, int value, String discription);
    public abstract Items createItem();
}
