package gameobjects.Items.Consumables;

import gameobjects.Items.Consumable;

import java.util.Random;

public class healthPotion extends Consumable {
    public healthPotion(int type){
        super("Health Potion", 30);
        setHeal(100);
        if(type == -1){
            clean("Good ");
        }else if(type == 1){
            old("Weak ");
        }
        setDescription(String.format("%s heals the user by %d", getName(), getHeal()));
    }

    public healthPotion(String name, int minDamage, int maxDamage,int heal,int value,String description,int amount) {
        super(name,minDamage,maxDamage,heal,value,description,amount);
    }

    private void old(String oldName){
        setHeal(getHeal()-35);
        setValue(getValue()-10);
        String temp = getName();
        temp = oldName+temp.toLowerCase();
        setName(temp);
    }

    private void clean(String cleanName){
        setHeal(getHeal()+35);
        setValue(getValue()+10);
        String temp = getName();
        temp = cleanName+temp.toLowerCase();
        setName(temp);
    }
}
