package gameobjects.Items;

import java.util.Random;

public abstract class Items {
    private String description = "";
    private String name = "";
    private int value = 0;
    private int minDamage = 0;
    private int maxDamage = 0;

    protected void setValue(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    protected void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    protected void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    protected int getMaxDamage(){
        return maxDamage;
    }

    protected void setMaxDamage(int maxDamage){
        this.maxDamage = maxDamage;
    }

    protected int getMinDamage(){
        return minDamage;
    }

    protected void setMinDamage(int minDamage){
        this.minDamage = minDamage;
    }

    public String save() {
        return null;
    }

}
