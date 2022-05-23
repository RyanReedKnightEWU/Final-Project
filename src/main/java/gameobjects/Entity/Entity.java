package gameobjects.Entity;

import gameobjects.Items.Armor;
import gameobjects.Items.Armors.Clothes;
import gameobjects.Items.Armors.PlateArmor;
import gameobjects.Items.Consumable;
import gameobjects.Items.Factories.ItemFactory;
import gameobjects.Items.Items;
import gameobjects.Items.Weapon;
import gameobjects.Items.Weapons.BareHands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Entity {
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
        addItem(weapon);
        setWeapon(0);
        addItem(armor);
        setArmor(1);

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

    public void removeItem(Items item) { //Consider merging with removeConsumable
        if (inventory.contains(item)) {
            inventory.remove(item);
        } else {
            System.out.println("This item is not in the inventory");
        }
    }

    public void addConsumable(final Consumable consumable) {
        if (inventory.contains(consumable)) {
            int index = inventory.indexOf(consumable);
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
        if(health <= 0){
            isAlive = false;
        }
    }

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

    public String saveString() { //Prints every field that needs to be saved
        String str = "ENTITY\n";
        str += String.format("%d\n%d\n%d\n%d\n%s\n%s\n%s\n%s\n%s\n",
                health, maxHealth, damage, defense, name, saveInventory(),
                "EQUIPPED", this.weapon.getName(), this.armor.getName());

        return str;
    }
}
