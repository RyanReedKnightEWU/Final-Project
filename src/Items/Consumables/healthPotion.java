package Items.Consumables;

import Items.Consumable;

import java.util.Random;

public class healthPotion extends Consumable {
    healthPotion(int luck){
        super("Health Potion", 30);
        setHeal(50);
        setCondition(luck, "Good ", "Weak ");
        setDescription(String.format("%s heals the user by %d", getName(), getHeal()));
    }

    private void old(String oldName){
        setHeal(getHeal()-25);
        setValue(getValue()-getValue()/4);
        String temp = getName();
        temp = oldName+temp.toLowerCase();
        setName(temp);
    }

    private void clean(String cleanName){
        setHeal(getHeal()+25);
        setValue(getValue()+getValue()/4);
        String temp = getName();
        temp = cleanName+temp.toLowerCase();
        setName(temp);
    }

    private void setCondition(int luck, String cleanName, String oldName){
        Random rand = new Random();
        int good = 20+luck*5;
        int bad = 45-luck*5;
        int value = rand.nextInt(101);
        if(value<bad){
            old(oldName);
        } else if(value>100-good){
            clean(cleanName);
        }
    }
}
