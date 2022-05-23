package gameobjects.Items.Armors;

import gameobjects.Items.Armor;

public class Clothes extends Armor {
    public Clothes(int luck){
        super("Clothes", 0, 0);
        setDescription(String.format("Don't leave the house without them."));
    }
    public Clothes(String name, int armorValue, int value) {
        super(name,armorValue,value);
    }
}
