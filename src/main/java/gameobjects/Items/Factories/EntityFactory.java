package gameobjects.Items.Factories;

import gameobjects.Entity.Entity;
import gameobjects.Items.Armor;
import gameobjects.Items.Weapon;

import java.util.ArrayList;

public abstract class EntityFactory {

    public abstract Entity createEntity(String entityName);
    public abstract Entity createEntity(String entityName, Weapon weapon, Armor armor);
    public abstract Entity createEntity(int health, int maxHealth, int damage, int defense, String entityName,
                                        ArrayList inventory, Weapon weapon, Armor armor);

}
