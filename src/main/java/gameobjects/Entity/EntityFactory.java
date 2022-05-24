package gameobjects.Entity;

import gameobjects.Items.Armor;
import gameobjects.Items.Items;
import gameobjects.Items.Weapon;

import java.util.ArrayList;

public class EntityFactory extends EntityFactoryBase{

    public EntityFactory() {
        super();
        super.keys.add(Bandit.class.getName());
        super.keys.add(Goblin.class.getName());
        super.keys.add(Murderbot.class.getName());
        super.keys.add(Mutant.class.getName());
        super.keys.add(Zombie.class.getName());
        super.keys.add(Player.class.getName());
    }

    /**
     * Creates entity, @param key indicates the specific subclass to return, and must be
     * retrieved from <Target Class>.class.getName(). All other params correspond directly to the fields of
     * the Entity class (and its subclasses).
     * **/
    @Override
    public Entity createEntity(String key, int health, int maxHealth, int damage, int defense, String name,
                               ArrayList<Items> inventory, Weapon weapon, Armor armor) {

        if (key.equals(Bandit.class.getName())) {
            return new Bandit(health, maxHealth, damage, defense, name, inventory, weapon, armor);
        }else if(key.equals(Goblin.class.getName())) {
            return new Goblin(health, maxHealth, damage, defense, name, inventory, weapon, armor);
        }else if(key.equals(Murderbot.class.getName())) {
            return new Murderbot(health, maxHealth, damage, defense, name, inventory, weapon, armor);
        }else if(key.equals(Mutant.class.getName())) {
            return new Mutant(health, maxHealth, damage, defense, name, inventory, weapon, armor);
        }else if(key.equals(Zombie.class.getName())) {
            return new Zombie(health, maxHealth, damage, defense, name, inventory, weapon, armor);
        }else if(key.equals(Player.class.getName())){
            return new Player(health, maxHealth, damage, defense, name, inventory, weapon, armor);
        }else {
            throw new IllegalArgumentException(key + " is not a valid key for createItem method in EntityFactory." +
                    " Valid keys consists of " + super.keys);
        }
    }
}
