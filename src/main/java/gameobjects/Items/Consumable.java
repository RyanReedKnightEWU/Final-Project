package gameobjects.Items;

import gameobjects.Entity.Entity;

import java.util.Random;

public class Consumable extends Items{
    private int heal = 0;
    private int amount = 0;

    protected Consumable(String name, int value){
        setName(name);
        setValue(value);
    }

    public void use(Entity entity){
        if(amount > 0){
            entity.takeDamage(getDamage());
            entity.heal(getHeal());
            amount--;
        }else {
            System.out.println("You are out of "+getName());
        }
    }

    protected void setHeal(int heal){
        this.heal = heal;
    }

    protected int getHeal(){
        return heal;
    }

    protected void setDamage(int minDamage, int maxDamage){
        setMinDamage(minDamage);
        setMaxDamage(maxDamage);
    }

    public int getAmount(){
        return amount;
    }

    public int getDamage(){
        Random rand = new Random();
        return rand.nextInt(getMaxDamage()-getMinDamage()+1)+getMinDamage();
    }

    public Boolean equals(Consumable consumable){
        if(consumable.getName().equals(getName())){
            return true;
        }else {
            return false;
        }
    }

    public void stack(Consumable consumable){
        if(getName().equals(consumable)){
            amount = amount + consumable.getAmount();
        } else {
            System.out.println("These items do not match.");
        }
    }

    public String toString(){
        String value = "";
        if(getMaxDamage() != 0 && getHeal() == 0){
            if(getMinDamage() == getMaxDamage()){
                value = String.format("Name: %s\nDamage: %d\nAmount: %d\nValue of each: %d", getName(), getMaxDamage(), getAmount(), getValue());
                return value;
            }else {
                value = String.format("Name: %s\nDamage: %d-%d\nAmount: %d\nValue of each: %d", getName(), getMinDamage(), getMaxDamage(), getAmount(), getValue());
                return value;
            }
        }
        if(getMaxDamage() == 0 && getHeal() != 0){
            value = String.format("Name: %s\nHeals: %d\nAmount: %d\nValue of each: %d", getName(), getHeal(), getAmount(), getValue());
        }
        if(getMaxDamage() != 0 && getHeal() != 0){
            if(getMinDamage() == getMaxDamage()){
                value = String.format("Name: %s\nDamage: %d\nHeals: %d\nAmount: %d\nValue of each: %d", getName(), getMaxDamage(), getHeal(), getAmount(), getValue());
                return value;
            }else {
                value = String.format("Name: %s\nDamage: %d-%d\nHeals: %d\nAmount: %d\nValue of each: %d", getName(), getMinDamage(), getMaxDamage(), getHeal(), getAmount(), getValue());
                return value;
            }
        }
        return value;
    }

}