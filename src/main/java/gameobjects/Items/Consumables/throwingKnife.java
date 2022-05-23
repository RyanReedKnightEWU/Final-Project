package gameobjects.Items.Consumables;

import gameobjects.Items.Consumable;

public class throwingKnife extends Consumable {
    public throwingKnife(String name,int minDamage,int maxDamage,int heal,int value,String description,int amount){
        super(name, minDamage,maxDamage,heal,value,description,amount);
    }
    public throwingKnife(){
        super("Throwing knife", 25);
        setDamage(35, 50);
        setDescription(String.format("Throwing knives are self explanatory."));
    }
}
