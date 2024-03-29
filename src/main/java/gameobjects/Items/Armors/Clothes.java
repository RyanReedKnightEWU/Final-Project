package gameobjects.Items.Armors;

import gameobjects.Items.Armor;

public class Clothes extends Armor {
    /**
     * Creates the default clothes.
     * @param luck
     */
    public Clothes(int luck){
        super("Clothes", 0, 10);
        setDescription(String.format("Don't leave the house without them."));
    }
    public Clothes(String name, int armorValue, int value) {
        super(name,armorValue,value);
    }
}
