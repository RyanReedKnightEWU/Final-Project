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
public class Goblin extends Entity {

    /**
     * Constructor for Goblin which gives it the default BareHands weapon and Clothes armor
     * @param name Entity name
     */
    public Goblin(String name) {
        super(75, 15, 3, name);
        Weapon weapon = new BareHands();
        addItem(weapon);
        setWeapon(0);
        Armor armor = new Clothes(0);
        addItem(armor);
        setArmor(1);
    }

    /**
     * Constructor for Goblin which gives it whatever weapon and armor is passed in
     * @param name Entity name
     * @param weapon The Goblin's starting weapon
     * @param armor The Goblin's starting armor
     */
    public Goblin(String name, Weapon weapon, Armor armor) {
        super(75,15,3,name);
        addItem(weapon);
        setWeapon(0);
        addItem(armor);
        setArmor(1);
    }


    /**
     * The constructor for loading the Goblin from the save file. Contains all information necessary to recreate it
     * from where the player left off.
     * @param health Current health
     * @param maxHealth Max health
     * @param damage Base damage
     * @param defense Base defense
     * @param name Name
     * @param inventory Goblin's inventory
     * @param weapon Goblin's equipped weapon
     * @param armor Goblin's equipped armor
     */
    public Goblin(int health, int maxHealth, int damage, int defense, String name, ArrayList inventory,
                  Weapon weapon, Armor armor) {
        super(health, maxHealth, damage, defense, name, inventory, weapon, armor);
    }


}
