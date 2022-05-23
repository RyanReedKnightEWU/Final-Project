package gameobjects.Items.Armors;

import gameobjects.Items.Armor;
import gameobjects.Items.ItemFactoryBase;

public abstract class ArmorFactoryBase extends ItemFactoryBase {
    public abstract Armor createArmor(String subclassKey, String name, int armorValue, int value);
}
