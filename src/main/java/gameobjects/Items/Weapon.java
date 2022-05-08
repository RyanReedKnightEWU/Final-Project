package gameobjects.Items;

import java.util.Random;

public class Weapon extends Items{

    public Weapon(){

    }

    public Weapon(String name, int minDamage, int maxDamage, int value){
        setName(name);
        setDamage(minDamage, maxDamage);
        setValue(value);
    }

    public void setWeapon(Weapon weapon){
        setName(weapon.getName());
        setDamage(weapon.getMinDamage(), weapon.getMaxDamage());
        setValue(weapon.getValue());
    }

    public void setDamage(int minDamage, int maxDamage){
        setMinDamage(minDamage);
        setMaxDamage(maxDamage);
    }

    public int getDamage(){
        Random rand = new Random();
        return rand.nextInt(getMaxDamage()-getMinDamage()+1)+getMinDamage();
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

}
