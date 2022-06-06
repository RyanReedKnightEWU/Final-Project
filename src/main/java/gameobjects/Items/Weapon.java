package gameobjects.Items;

import java.util.Random;

public class Weapon extends Items{

    /**
     * Allows for an empty weapon to be made.
     */
    public Weapon(){
        type = "Weapon";
    }

    /**
     * Allows for a basic weapon to be made.
     */
    public Weapon(String name, int minDamage, int maxDamage, int value){
        type = "Weapon";
        setName(name);
        setDamage(minDamage, maxDamage);
        setValue(value);
        setDescription("Weapon");
    }

    /**
     * Allows one to fully define a weapon. Meant for loading weapons from a save file.
     */
    public Weapon(String name, int minDamage, int maxDamage, int value, String discription){
        setName(name);
        setDamage(minDamage, maxDamage);
        setValue(value);
        type = "Weapon";
        setDescription(discription);
    }

    /**
     * Allows for weapon duplication.
     */
    public void setWeapon(Weapon weapon){
        setName(weapon.getName());
        setDamage(weapon.getMinDamage(), weapon.getMaxDamage());
        setValue(weapon.getValue());
        setDescription(weapon.getDescription());
        type = "Weapon";
    }

    public void setDamage(int minDamage, int maxDamage){
        setMinDamage(minDamage);
        setMaxDamage(maxDamage);
    }

    /**
     * Gets a random value between the weapons maximum damage and min damage.
     */
    public int getDamage(){
        Random rand = new Random();
        return rand.nextInt(getMaxDamage()-getMinDamage()+1)+getMinDamage();
    }

    /**
     * @return the damage range as a string.
     */
    public String damageRange(){
        return String.format("%d-%d", getMinDamage(), getMaxDamage());
    }

    @Override
    public String toString(){
        String value;
        if(getMinDamage() == getMaxDamage()){
            value = String.format("Name: %s\nDamage: %d\nValue: %d", getName(), getMaxDamage(), getValue());
            return value;
        }else {
            value = String.format("Name: %s\nDamage: %d-%d\nValue: %d", getName(), getMinDamage(), getMaxDamage(), getValue());
            return value;
        }
    }

    /**
     * Makes a string for saving purposes.
     * @return the weapons properties with no flavor text.
     */
    public String save(){
        String value = "WEAPON\n";
        //value += String.format("Name: %s\nDamage: %d-%d\nValue: %d\nDescription: %s", getName(), getMinDamage(), getMaxDamage(), getValue(), getDescription());
        value += String.format("%s\n%d\n%d\n%d\n%s\n", getName(), getMinDamage(), getMaxDamage(), getValue(), getDescription());
        return value;
    }

}
