package gameobjects.Items;

import java.util.Random;

public class Weapon extends Items{

    public Weapon(){
        type = "Weapon";
    }

    public Weapon(String name, int minDamage, int maxDamage, int value){
        type = "Weapon";
        setName(name);
        setDamage(minDamage, maxDamage);
        setValue(value);
        setDescription("Weapon");
    }

    public Weapon(String name, int minDamage, int maxDamage, int value, String discription){
        setName(name);
        setDamage(minDamage, maxDamage);
        setValue(value);
        type = "Weapon";
    }

    public void setWeapon(Weapon weapon){
        setName(weapon.getName());
        setDamage(weapon.getMinDamage(), weapon.getMaxDamage());
        setValue(weapon.getValue());
        type = "Weapon";
    }

    public void setDamage(int minDamage, int maxDamage){
        setMinDamage(minDamage);
        setMaxDamage(maxDamage);
    }

    public int getDamage(){
        Random rand = new Random();
        return rand.nextInt(getMaxDamage()-getMinDamage()+1)+getMinDamage();
    }

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

    public String save(){
        String value = "WEAPON\n";
        //value += String.format("Name: %s\nDamage: %d-%d\nValue: %d\nDescription: %s", getName(), getMinDamage(), getMaxDamage(), getValue(), getDescription());
        value += String.format("%s\n%d\n%d\n%d\n%s", getName(), getMinDamage(), getMaxDamage(), getValue(), getDescription());
        return value;
    }

}
