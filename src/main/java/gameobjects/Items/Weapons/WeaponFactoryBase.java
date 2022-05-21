package gameobjects.Items.Weapons;

import gameobjects.Items.Items;

public abstract class WeaponFactoryBase {
    public abstract Items createItem(String key, String name, int minDamage,
                                     int maxDamage, int value, String discription);
    public abstract Items createItem();
}
