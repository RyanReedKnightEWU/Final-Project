package gameobjects.Entity;

import gameobjects.Items.Armor;
import gameobjects.Items.Items;
import gameobjects.Items.Weapon;

import java.util.ArrayList;

public abstract class EntityFactoryBase {

    ArrayList<String>keys;

    public EntityFactoryBase() {
        this.keys = new ArrayList<>();
    }

    public ArrayList<String> getKeys() {
        return this.keys;
    }

    public boolean isValidKey(String key) {
        if(key == null) {
            return false;
        }
        return this.keys.contains(key);
    }

    public abstract Entity createEntity(String key, int health, int maxHealth, int damage, int defense, String name, ArrayList<Items> inventory,
                                        Weapon weapon, Armor armor);
}
