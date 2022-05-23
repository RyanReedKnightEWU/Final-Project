package gameobjects.Items;

import gameobjects.Entity.Entity;

import java.util.Random;

public class Consumable extends Items{
    private int heal = 0;
    private int amount = 1;

    protected Consumable(String name, int value){
        type = "Consumable";
        setName(name);
        setValue(value);
    }

    public Consumable(String name, int minDamage, int maxDamage, int heal, int value, String description, int amount){
        //used for loading consumables
        type = "Consumable";
        setName(name);
        setHeal(heal);
        setValue(value);
        setDescription(description);
        setAmount(amount);
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

    public int getHeal(){
        return heal;
    }

    protected void setDamage(int minDamage, int maxDamage){
        setMinDamage(minDamage);
        setMaxDamage(maxDamage);
    }

    public int getAmount(){
        return amount;
    }

    //METHOD ADDED BY BRENDAN
    public void setAmount(int amount) { this.amount = amount; }

    public int getDamage(){
        Random rand = new Random();
        return rand.nextInt(getMaxDamage()-getMinDamage()+1)+getMinDamage();
    }

    @Override
    public boolean equals(Object consumable){
        if (!(consumable instanceof Consumable)) {
            return false;
        }
        if(((Consumable)consumable).getName().equals(getName())){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }

    public void stack(Consumable consumable){
        if(getName().equals(consumable.getName())){
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

    public String save(){
        String value = "CONSUMABLE\n";
        //value += String.format("Name: %s\nDamage: %d-%d\nHeals: %d\nAmount: %d\nValue: %d\nDescription: %s", getName(), getMinDamage(), getMaxDamage(), getHeal(), getAmount(), getValue(), getDescription());
        value += String.format("%s\n%d\n%d\n%d\n%d\n%d\n%s", getName(), getMinDamage(), getMaxDamage(), getHeal(), getAmount(), getValue(), getDescription());
        return value;
    }

}
