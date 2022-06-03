package gameobjects.Items;

import java.util.Random;

public class Armor extends Items{
    /**
     * The amount of protection the armor provides.
     */
    int armorValue = 0;
    /**
     * The amount of armor the armor can vary when the armor is made.
     */
    int vary = 5;

    /**
     * Allows for basic construction of armor to be made.
     * @param name
     * @param armorValue
     * @param value
     */
    protected Armor(String name, int armorValue, int value) {
        type = "Armor";
        setArmorValue(armorValue);
        setValue(value);
        setName(name);
        setDescription("armor");
    }

    /**
     * Allows one to fully define the armor. For loading purposes.
     * @param name
     * @param armorValue
     * @param value
     * @param description
     */
    public Armor(String name, int armorValue, int value, String description) {
        //Used for loading armor
        type = "Armor";
        setArmorValue(armorValue);
        setValue(value);
        setName(name);
        setDescription(description);
    }

    protected void setArmorValue(int armorValue){
        this.armorValue = armorValue;
    }

    public int getArmorValue(){
        return armorValue;
    }

    /**
     * If the armor is the old version then it will have a decreased armor value, value, and a slightly different name.
     * @param oldName
     */
    protected void old(String oldName){
        armorValue -= vary;
        setValue(getValue()-getValue()/4);
        String temp = getName();
        temp = oldName+temp.toLowerCase();
        setName(temp);
    }

    /**
     * If the armor is the new version then it will have an increased armor value, value, and a slightly different name.
     * @param cleanName
     */
    protected void clean(String cleanName){
        armorValue += vary;
        setValue(getValue()+getValue()/4);
        String temp = getName();
        temp = cleanName+temp.toLowerCase();
        setName(temp);
    }

    /*
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

    public int getVary() {
        return vary;
    }
    protected void setVary(int vary){
        this.vary = vary;
    }
     */

    public String toString(){
        String value;
        value = String.format("Name: %s\nArmor: %d\nValue: %d", getName(), getArmorValue(), getValue());
        return value;
    }

    /**
     * Creates a save string for saving.
     * @return
     */
    public String save(){
        String value = "ARMOR\n";
        //value += String.format("Name: %s\nArmor: %d\nValue: %d\nDescription: %n", getName(), getArmorValue(), getValue(), getDescription());
        value += String.format("%s\n%d\n%d\n%s\n", getName(), getArmorValue(), getValue(), getDescription());
        return value;
    }

}
