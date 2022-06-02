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
public class Murderbot extends Entity {

    /**
     * Constructor for Murderbot which gives it the default BareHands weapon and Clothes armor
     * @param name Entity name
     */
    public Murderbot(String name) {
        super(150, 20, 10, name);
        Weapon weapon = new BareHands();
        addItem(weapon);
        setWeapon(0);
        Armor armor = new Clothes(0);
        addItem(armor);
        setArmor(1);
    }

    /**
     * Constructor for Murderbot which gives it whatever weapon and armor is passed in
     * @param name Entity name
     * @param weapon The Murderbot's starting weapon
     * @param armor The Murderbot's starting armor
     */
    public Murderbot(String name, Weapon weapon, Armor armor) {
        super(150,20,10,name);
        addItem(weapon);
        setWeapon(0);
        addItem(armor);
        setArmor(1);
    }

    /**
     * The constructor for loading the Murderbot from the save file. Contains all information necessary to recreate it
     * from where the player left off.
     * @param health Current health
     * @param maxHealth Max health
     * @param damage Base damage
     * @param defense Base defense
     * @param name Name
     * @param inventory Murderbot's inventory
     * @param weapon Murderbot's equipped weapon
     * @param armor Murderbot's equipped armor
     */
    public Murderbot(int health, int maxHealth, int damage, int defense, String name, ArrayList inventory,
                  Weapon weapon, Armor armor) {
        super(health, maxHealth, damage, defense, name, inventory, weapon, armor);
    }
}