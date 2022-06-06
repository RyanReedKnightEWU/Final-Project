package gameobjects.Items;

import gameobjects.Entity.Entity;

import java.util.Random;

public class Consumable extends Items{
    /**
     * How much the consumable will heal the entity
     */
    private int heal = 0;
    /**
     * How many uses the consumable has.
     */
    private int amount = 1;

    /**
     * Allows for a consumable to copy the properties of another consumable.
     * @param consumable
     */
    public Consumable(Consumable consumable){
        type = "Consumable";
        setName(consumable.getName());
        setHeal(consumable.getHeal());
        setValue(consumable.getValue());
        setDamage(consumable.getMinDamage(), consumable.getMaxDamage());
        setDescription(consumable.getDescription());
        setAmount(consumable.getAmount());
    }

    /**
     * Allows for a nearly empty consumable to be made.
     */
    protected Consumable(String name, int value){
        type = "Consumable";
        setName(name);
        setValue(value);
    }

    /**
     * Allows a consumable to be fully defined. Meant for loading a consumable from a save file.
     */
    public Consumable(String name, int minDamage, int maxDamage, int heal, int value, String description, int amount){
        //used for loading consumables
        type = "Consumable";
        setName(name);
        setHeal(heal);
        setValue(value);
        setDamage(minDamage, maxDamage);
        setDescription(description);
        setAmount(amount);
    }

    /**
     * Allows for an entity to use the consumable on entity.
     * Will try to deal damage to the entity, and then it will try to heal the entity.
     */
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

    /**
     * Allows for to items to add their uses together if they are the same consumable.
     */
    public Consumable stack(Consumable consumable){
        if(getName().equals(consumable.getName())){
            amount = amount + consumable.getAmount();
        } else {
            System.out.println("These items do not match.");
        }
        return this;
    }

    /**
     * Makes a string based off of the effects of the consumable.
     */
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

    /**
     * Creates a save string for saving.
     */
    public String save(){
        String value = "CONSUMABLE\n";
        /*value += String.format("Name: %s\nDamage: %d-%d\nHeals: %d\nAmount: %d\nValue: %d\nDescription: %s",
         getName(), getMinDamage(), getMaxDamage(), getHeal(), getAmount(), getValue(), getDescription());*/
        value += String.format("%s\n%d\n%d\n%d\n%d\n%d\n%s\n", getName(), getMinDamage(),
                getMaxDamage(), getHeal(), getAmount(), getValue(), getDescription());
        return value;
    }

}
