package gameobjects.Entity;

import gameobjects.Items.Armor;
import gameobjects.Items.Consumable;
import gameobjects.Items.Items;
import gameobjects.Items.Weapon;

import java.util.ArrayList;

public abstract class Entity {
    private int health;
    private int maxHealth;
    private int damage;
    private int defense;
    private String name;

    private boolean isAlive;
    private ArrayList<Items> inventory;
    private Weapon weapon;
    private Armor armor;
    private Consumable consumable; //I probably don't need this

    public Entity(int health, int damage, int defense, String name)
    {
        if(health <= 0 || damage < 0 || defense < 0 || name.isEmpty())
            throw new IllegalArgumentException("Bad Parameter(s) --- Entity Parent Constructor");

        if (inventory == null) {
            inventory = new ArrayList<>();
        }

        this.health = health;
        this.maxHealth = health;
        this.damage = damage;
        this.defense = defense;
        this.name = name;
        this.isAlive = true;
    }//end Entity Constructor

    public Entity(int health, int maxHealth, int damage, int defense, String name,
                  ArrayList<Items> inventory, Weapon weapon, Armor armor) {

        if (inventory == null) {
            inventory = new ArrayList<>();
        }

        this.health = health;
        this.maxHealth = maxHealth;
        this.damage = damage;
        this.defense = defense;
        this.name = name;
        this.inventory = inventory;
        this.weapon = weapon;
        this.armor = armor;

        if (health >= 0) {
            this.isAlive = true;
        }
    }

    public void printInventory() {
        if (inventory.isEmpty()) { //Delete the if statement before project submission
            System.out.println("Inventory is empty");
        }
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println(inventory.get(i));
        }
    }

    public void addItem(Items item) {
        inventory.add(item);
    }

    public void removeItem(Items item) { //Consider merging with removeConsumable
        if (inventory.contains(item)) {
            inventory.remove(item);
        } else {
            System.out.println("This item is not in the inventory");
        }
    }

    public void addConsumable(Consumable consumable) {
        if (inventory.contains(consumable)) {
            int index = inventory.indexOf(consumable);
            //((Consumable) inventory.get(index)).setAmount(((Consumable) inventory.get(index)).getAmount() + 1);
            ((Consumable) inventory.get(index)).stack(consumable);
        } else {
            inventory.add(consumable);
        }
    }

    public void removeConsumable(Consumable consumable) {
        if (inventory.contains(consumable)) {
            int index = inventory.indexOf(consumable);

            //Removes consumable if amount is 1 or lower,or otherwise decreases amount by 1
            if (((Consumable) inventory.get(index)).getAmount() <= 1) {
                inventory.remove(inventory.get(index));
            } else {
                ((Consumable) inventory.get(index)).setAmount(((Consumable) inventory.get(index)).getAmount() - 1);
            }
        }else {
            System.out.println("This consumable is not in the inventory");
        }
    }



    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public boolean isAlive() {
        return this.isAlive;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    //public Consumable getConsumable() { return consumable; }

    //public void setConsumable(Consumable consumable) { this.consumable = consumable; }

    // ADDED BY RYAN (PLEASE DO NOT ADD SETTER.)
    public String getName() { return this.name; }

    public int getHealth() { return health; }

    public void setHealth(int health) {
        this.health = health;
        if (this.health >= 0) { this.isAlive = false; }
    }

    public int getMaxHealth() { return maxHealth; }

    public void setMaxHealth(int maxHealth) { this.maxHealth = maxHealth; }

    public int getDamage() { return (damage + getWeapon().getDamage()); }

    public void setDamage(int damage) { this.damage = damage; }

    public int getDefense() { return (defense + getArmor().getArmorValue()); }

    public void setDefense(int defense) { this.defense = defense; }

    public void heal(int heal) {
        if ((getHealth() + heal) >= getMaxHealth()) {
            setHealth(getMaxHealth());
        }else {
            setHealth(getHealth() + heal);
        }
        System.out.println(heal + " health restored and " + getHealth() + " health remaining");
    }

    public void takeDamage(int damage) {
        int damageTaken = damage - getDefense();
        if (damageTaken < 1) { damageTaken = 1; }
        setHealth(getHealth()-damageTaken);
        System.out.println(damageTaken + " damage taken and " + getHealth() + " health remaining");
    }

    public int basicAttack() {
        System.out.println(getDamage() + " damage calculated");
        return getDamage();
    }

    public String toString() {
        String info = this.name + " has ";
        info += this.health + " health, ";
        info += this.damage + " damage, and ";
        info += this.defense + " defense.\n";
        info += this.name + " wields " + this.weapon.getName().toLowerCase() +
                " and wears " + this.armor.getName().toLowerCase() + ".";
        return info;
    }

    public String saveInventory() {
        String str = null;

        return str;
    }

    public String saveString() { //Prints every field that needs to be saved
        String str;
        str = String.format("%d\n,%d\n,%d\n,%d\n,%s\n,%s\n,%s\n,%s\n",
                health, maxHealth, damage, defense, name, inventory, weapon.save(), armor.save());
        //Add inventorySave method which uses consumable save method.
        //Flag for
        //consumables stack using the amount field.
        //Call Hunter's toString methods for weapons and armor

        return null;
    }
}
