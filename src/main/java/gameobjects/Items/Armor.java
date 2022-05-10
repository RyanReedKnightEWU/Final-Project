package gameobjects.Items;

import java.util.Random;

public class Armor extends Items{
    int armorValue = 0;
    int vary = 5;

    public Armor(String name, int armorValue, int value) {
        setArmorValue(armorValue);
        setValue(value);
        setName(name);
        setValue(value);
    }

    protected void setArmorValue(int armorValue){
        this.armorValue = armorValue;
    }

    public int getArmorValue(){
        return armorValue;
    }

    protected void old(String oldName){
        armorValue -= vary;
        setValue(getValue()-getValue()/4);
        String temp = getName();
        temp = oldName+temp.toLowerCase();
        setName(temp);
    }

    protected void clean(String cleanName){
        armorValue += vary;
        setValue(getValue()+getValue()/4);
        String temp = getName();
        temp = cleanName+temp.toLowerCase();
        setName(temp);
    }

    protected void setCondition(int luck, String cleanName, String oldName){
        Random rand = new Random();
        int good = 20+luck*5;
        int bad = 55-luck*5;
        int value = rand.nextInt(101);
        if(value<bad){
            old(oldName);
        } else if(value>100-good){
            clean(cleanName);
        }
    }

    protected void setVary(int vary){
        this.vary = vary;
    }

    public String toString(){
        String value;
        value = String.format("Name: %s\nArmor: %d\nValue: %d", getName(), getArmorValue(), getValue());
        return value;
    }

    public String save(){
        String value = "ARMOR\n";
        value += String.format("Name: %s\nArmor: %d\nValue: %d\nDescription: %n", getName(), getArmorValue(), getValue(), getDescription());
        return value;
    }

}
