package Items;

import java.util.Random;

public class Weapon extends Items{
    private int vary = 10;
    private int valueVaryDivider = 4;
    private int condition = 0;

    protected Weapon(String name, int minDamage, int maxDamage, int value){
        setValue(value);
        setName(name);
        setDamage(minDamage, maxDamage);
        setValue(value);
    }

    public Weapon(String name, int minDamage, int maxDamage, int value, int condition){
        setValue(value);
        setName(name);
        setDamage(minDamage, maxDamage);
        setValue(value);
        setConditionValue(condition);
    }

    protected void setValueVaryDivider(int valueVaryDivider){
        this.valueVaryDivider = valueVaryDivider;
    }

    protected void setConditionValue(int condition){
        this.condition = condition;
    }

    protected int getCondition(){
        return condition;
    }

    protected void old(String oldName){
        int minDamage = getMinDamage() - vary;
        int maxDamage = getMaxDamage() - vary;
        setDamage(minDamage, maxDamage);
        setValue(getValue()-getValue()/valueVaryDivider);
        String temp = getName();
        temp = oldName+temp.toLowerCase();
        setName(temp);
    }

    protected void clean(String cleanName){
        int minDamage = getMinDamage() + vary;
        int maxDamage = getMaxDamage() + vary;
        setDamage(minDamage, maxDamage);
        setValue(getValue()+getValue()/valueVaryDivider);
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
            condition = -1;
            old(oldName);
        } else if(value>100-good){
            condition = 1;
            clean(cleanName);
        }
    }

    //Vary is how the damage varies if it is new or old
    protected void setVary(int vary){
        this.vary = vary;
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
