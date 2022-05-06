package Entity;

import Items.Armor;
import Items.Consumable;
import Items.Items;
import Items.Weapon;

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
    private Consumable consumable;

    public Entity(int health, int damage, int defense, String name)
    {
        if(health <= 0 || damage < 0 || defense < 0 || name.isEmpty())
            throw new IllegalArgumentException("Bad Parameter(s) --- Entity Parent Constructor");

        this.health = health;
        this.maxHealth = health;
        this.damage = damage;
        this.defense = defense;
        this.name = name;
        this.isAlive = true;
    }//end Entity Constructor

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public boolean isAlive() {
        return this.isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public Consumable getConsumable() {
        return consumable;
    }

    public void setConsumable(Consumable consumable) {
        this.consumable = consumable;
    }

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

}
