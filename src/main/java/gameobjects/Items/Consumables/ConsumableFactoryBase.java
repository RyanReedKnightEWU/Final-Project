package gameobjects.Items.Consumables;

import gameobjects.Items.Consumable;
import gameobjects.Items.ItemFactoryBase;

public abstract class ConsumableFactoryBase extends ItemFactoryBase {
    public abstract Consumable createConsumable(String subclassKey, String name, int minDamage, int maxDamage,
                                                int heal, int value, String description, int amount);
}
