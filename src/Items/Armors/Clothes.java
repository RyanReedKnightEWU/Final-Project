package Items.Armors;

import Items.Armor;

public class Clothes extends Armor {
    public Clothes(int luck){
        super("Clothes", 0, 20);
        setDescription(String.format("Don't leave the house without them."));
    }
}