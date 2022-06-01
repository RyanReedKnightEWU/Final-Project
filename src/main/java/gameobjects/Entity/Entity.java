package gameobjects.Entity;

import gameobjects.Items.Armor;
import gameobjects.Items.Armors.Clothes;
import gameobjects.Items.Consumable;
import gameobjects.Items.Items;
import gameobjects.Items.Weapon;
import gameobjects.Items.Weapons.BareHands;
import gameobjects.SaveLoader.Savable;
import gameobjects.SaveLoader.SaveLoader;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * The parent class which contains most methods and fields used by its concrete subclasses
 */
public abstract class Entity implements Savable, Comparable<Entity>{
    private int health;
    private int maxHealth;
    private int damage;
    private int defense;
    private String name;
    private boolean isAlive;
    private int gold = 25;

    private ArrayList<Items> inventory;
    private Weapon weapon = new BareHands();
    private Armor armor = new Clothes(0);

    /**
     * Main constructor used for entities
     * @param health Max health
     * @param damage Base damage
     * @param defense Base defense
     * @param name Name of the entity
     * @throws IllegalArgumentException if health, damage, or defense are less than 0, or if name is empty
     */
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

    /**
     * Entity constructor used when loading an entity from a save file
     * @param health Entity current health
     * @param maxHealth Entity maximum health
     * @param damage Base damage
     * @param defense Base defense
     * @param name Name of the entity
     * @param inventory The array list containing all Items owned by the Entity
     * @param weapon The weapon currently equipped by the Entity
     * @param armor The armor currently equipped by the Entity
     */
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
        addItem(weapon);
        setWeapon(0);
        addItem(armor);
        setArmor(armor);

        if (health >= 0) {
            this.isAlive = true;
        }
    }

    public int getGold(){
        return gold;
    }

    public void setGold(int gold){
        this.gold = gold;
    }

    public void addGold(int gold){
        this.gold += gold;
    }

    /**
     * Prints the contents of the Entity inventory from first to last index
     */
    public void printInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty");
        }
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println("-----Inventory Index: " + i + "-----");
            System.out.println(inventory.get(i));
        }
    }

    public void addItem(Items item) {
        inventory.add(item);
    }

    /**
     * Removes items from the inventory array list
     * @param item Weapon or armor to be removed
     */
    public void removeItem(Items item) {
        if (inventory.contains(item)) {
            inventory.remove(item);
        } else {
            System.out.println("This item is not in the inventory");
        }
    }

    /**
     * Adds consumable(s) to the inventory array list. Stacks the consumables if adding more of a consumable which is
     * already stored in the inventory.
     * @param consumable Consumable to be added
     */
    public void addConsumable(final Consumable consumable) {
        if (inventory.contains(consumable)) {
            int index = inventory.indexOf(consumable);
            ((Consumable) inventory.get(index)).stack(consumable);
        } else {
            inventory.add(consumable);
        }
    }

    /**
     * Reduces amount of consumable in inventory, or removes the consumable if amount <= 1
     * @param consumable Consumable to be removed
     */
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

    public void setInventory(Items[] items) {
        inventory = new ArrayList<Items>(Arrays.asList(items));
    }

    public ArrayList<Items> getInventory() {
        return inventory;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setWeapon(int index) {
        this.weapon = ((Weapon)inventory.get(index));
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

    public void setArmor(int index) {
        this.armor = ((Armor)inventory.get(index));
    }

    // ADDED BY RYAN (PLEASE DO NOT ADD SETTER.)
    public String getName() { return this.name; }

    public int getHealth() { return health; }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() { return maxHealth; }

    public int getDamage() { return (damage + getWeapon().getDamage()); }

    public void setDamage(int damage) { this.damage = damage; }

    public int getDefense() { return (defense + getArmor().getArmorValue()); }

    /**
     * Heals the entity
     * @param heal Amount of health to be recovered
     */
    public void heal(int heal) {
        if ((getHealth() + heal) >= getMaxHealth()) {
            setHealth(getMaxHealth());
        }else {
            setHealth(getHealth() + heal);
        }
        System.out.println(heal + " health restored and " + getHealth() + " health remaining");
    }

    /**
     * Entity takes damage and loses health
     * @param damage Amount of damage to receive
     */
    public void takeDamage(int damage) {
        int damageTaken = damage - getDefense();
        if (damageTaken < 1) { damageTaken = 1; }
        setHealth(getHealth()-damageTaken);
        System.out.println(damageTaken + " damage taken and " + getHealth() + " health remaining");
        if(health <= 0){
            isAlive = false;
        }
    }

    /**
     * Basic attack method
     * @return Damage to be dealt
     */
    public int basicAttack() {
        System.out.println(getDamage() + " damage calculated");
        return getDamage();
    }

    public String toString() {
        String info = String.format("%s, Health: %d/%d, Defense: %d+%d, Damage: %d+(%s)\n Weapon: %s, Armor: %s",
                name, health, maxHealth, defense, getArmor().getArmorValue(), damage, weapon.damageRange(), weapon.getName(), armor.getName());
        return info;
    }

    private String checkIfItemNull(Items item) {
        if(item != null ) {
            return item.getName().toLowerCase();
        }
        else {
            return "null";
        }
    }

    /**
     * Saves all necessary information about the inventory and its contents for use in saving and loading
     * @return A string containing the contents of everything within the inventory
     */
    public String saveInventory() {
        String str = "INVENTORY\n";
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i) instanceof Weapon){
                str += ((Weapon)inventory.get(i)).save();
            }else if (inventory.get(i) instanceof Armor) {
                str += ((Armor)inventory.get(i)).save();
            }else {
                str += ((Consumable)inventory.get(i)).save();
            }
        }
        return str;
    }

    /**
     * Saves all necessary information about the Entity for use in saving and loading
     * @return A string containing the contents of everything within Entity
     */
    public String saveString() { //Prints every field that needs to be saved
        String str = "ENTITY\n";
        str += String.format("%d\n%d\n%d\n%d\n%s\n%s\n%s\n%s\n",
                health, maxHealth, damage, defense, name, saveInventory(),/*
                "EQUIPPED", */this.weapon.getName(), this.armor.getName());

        return str;
    }

    @Override
    public void saveInstance(FileWriter saveFile) throws IOException {

        String str = String.format("%d\n%d\n%d\n%d\n%s\n",
                health, maxHealth, damage, defense, name);
        saveFile.write(this.getClass().getName() + "\n");
        saveFile.write(str);
        SaveLoader.saveArray(this.inventory.toArray(new Items[0]),saveFile);
        this.weapon.saveInstance(saveFile);
        this.armor.saveInstance(saveFile);

    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    @Override
    public int hashCode(){
        return (this.getClass().getName().length()*this.getClass().getName().length()
                + this.defense + this.damage + this.health);
    }

    @Override
    public int compareTo(Entity entity) {

        if (this == entity) {
            return 0;
        } else if (entity == null) {
            return 1;
        }

        if (name.equals(entity.name)) {
            if (maxHealth == entity.maxHealth) {
                if (health == entity.health) {
                    if (damage == entity.damage) {
                        if (defense == entity.defense) {
                            if (isAlive == entity.isAlive) {
                                if(gold == entity.gold) {
                                    if(weapon.equals(entity.weapon)){
                                        if (armor.equals(entity.armor)) {
                                            if (inventory.size() == entity.inventory.size()) {
                                                for(int i = 0; i < inventory.size(); i++) {
                                                    if(!inventory.contains(entity.inventory.get(i))){
                                                        return 1;
                                                    }
                                                }
                                                return 0;
                                            }
                                            return Integer.compare(inventory.size(),entity.inventory.size());
                                        }
                                        return armor.compareTo(entity.armor);
                                    }
                                    return weapon.compareTo(entity.weapon);
                                }
                                return Integer.compare(gold,entity.gold);
                            }
                            return Boolean.compare(isAlive,entity.isAlive);
                        }
                        return Integer.compare(defense,entity.defense);
                    }
                    return Integer.compare(damage,entity.damage);
                }
                return Integer.compare(health,entity.health);
            }
            return Integer.compare(maxHealth,entity.maxHealth);
        }
        return name.compareTo(entity.name);
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Entity)) {
            return false;
        } else if (obj == this) {
            return true;
        } else {
            return compareTo((Entity) obj) == 0;
        }
    }
}
