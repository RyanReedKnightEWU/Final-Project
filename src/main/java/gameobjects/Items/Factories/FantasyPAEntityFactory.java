package gameobjects.Items.Factories;

import gameobjects.Entity.*;
import gameobjects.Items.Armor;
import gameobjects.Items.Weapon;

import java.util.ArrayList;

public class FantasyPAEntityFactory extends EntityFactory{

    public Entity createEntity(String entityName) {

        if (entityName.equals("bandit")) {
            return new Bandit(entityName);
        }else if (entityName.equals("goblin")) {
            return new Goblin(entityName);
        }else if (entityName.equals("murderbot")) {
            return new Murderbot(entityName);
        }else if (entityName.equals("mutant")) {
            return new Mutant(entityName);
        }else if (entityName.equals("zombie")) {
            return new Zombie(entityName);
        }else {
            throw new IllegalArgumentException("Bad parameter in constructor - Name does not match any entity type");
        }

    }
    public Entity createEntity(String entityName, Weapon weapon, Armor armor) {

        if (entityName.equals("bandit")) {
            return new Bandit(entityName, weapon, armor);
        }else if (entityName.equals("goblin")) {
            return new Goblin(entityName, weapon, armor);
        }else if (entityName.equals("murderbot")) {
            return new Murderbot(entityName, weapon, armor);
        }else if (entityName.equals("mutant")) {
            return new Mutant(entityName, weapon, armor);
        }else if (entityName.equals("zombie")) {
            return new Zombie(entityName, weapon, armor);
        }else {
            throw new IllegalArgumentException("Bad parameter in constructor - Name does not match any entity type");
        }

    }
    public Entity createEntity(int health, int maxHealth, int damage, int defense, String entityName,
                               ArrayList inventory, Weapon weapon, Armor armor) {

        if (entityName.equals("bandit")) {
            return new Bandit(health, maxHealth, damage, defense, entityName, inventory, weapon, armor);
        }else if (entityName.equals("goblin")) {
            return new Goblin(health, maxHealth, damage, defense, entityName, inventory, weapon, armor);
        }else if (entityName.equals("murderbot")) {
            return new Murderbot(health, maxHealth, damage, defense, entityName, inventory, weapon, armor);
        }else if (entityName.equals("mutant")) {
            return new Mutant(health, maxHealth, damage, defense, entityName, inventory, weapon, armor);
        }else if (entityName.equals("zombie")) {
            return new Zombie(health, maxHealth, damage, defense, entityName, inventory, weapon, armor);
        }else {
            throw new IllegalArgumentException("Bad parameter in constructor - Name does not match any entity type");
        }

    }
}