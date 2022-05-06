package Items.Armors;

import Items.Armor;

public class PlateArmor extends Armor {
    public PlateArmor(int luck){
        super("Magic plate armor", 10, 150);
        setVary(8);
        setCondition(luck, "New ", "Rusty ");
        setDescription(String.format("%s is what we call actual armor. It can even stop bullets.", getName()));
    }
}
