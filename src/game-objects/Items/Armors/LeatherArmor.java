package Items.Armors;

import Items.Armor;

public class LeatherArmor extends Armor {
    public LeatherArmor(int luck){
        super("Leather armor", 5, 75);
        setVary(2);
        setCondition(luck, "New ", "Worn out ");
        setDescription(String.format("%s is a armor set made out of animal hide.", getName()));
    }
}
