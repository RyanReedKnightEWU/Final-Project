package gameobjects.Entity;

import gameobjects.Items.Armor;
import gameobjects.Items.Armors.Clothes;
import gameobjects.Items.Consumable;
import gameobjects.Items.Items;
import gameobjects.Items.Weapon;
import gameobjects.Items.Weapons.BareHands;

import java.util.ArrayList;

/**
 * A concrete subclass of Entity
 */
public class Zombie extends Entity {

    /**
     * Constructor for Zombie which gives it the default BareHands weapon and Clothes armor
     * @param name Entity name
     */
    public Zombie(String name) {
        super(125, 12, 0, name);
        Weapon weapon = new BareHands();
        addItem(weapon);
        setWeapon(0);
        Armor armor = new Clothes(0);
        addItem(armor);
        setArmor(1);
    }

    /**
     * Constructor for Zombie which gives it whatever weapon and armor is passed in
     * @param name Entity name
     * @param weapon The Zombie's starting weapon
     * @param armor The Zombie's starting armor
     */
    public Zombie(String name, Weapon weapon, Armor armor) {
        super(125,12,0,name);
        addItem(weapon);
        setWeapon(0);
        addItem(armor);
        setArmor(1);
    }

    /**
     * The constructor for loading the Zombie from the save file. Contains all information necessary to recreate it
     * from where the player left off.
     * @param health Current health
     * @param maxHealth Max health
     * @param damage Base damage
     * @param defense Base defense
     * @param name Name
     * @param inventory Zombie's inventory
     * @param weapon Zombie's equipped weapon
     * @param armor Zombie's equipped armor
     */
    public Zombie(int health, int maxHealth, int damage, int defense, String name, ArrayList inventory,
                  Weapon weapon, Armor armor) {
        super(health, maxHealth, damage, defense, name, inventory, weapon, armor);
    }
}