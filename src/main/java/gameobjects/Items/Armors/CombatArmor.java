package gameobjects.Items.Armors;

import gameobjects.Items.Armor;

public class CombatArmor extends Armor {
    public CombatArmor(int luck){
        super("Combat armor", 15, 150);
        setVary(5);
        setCondition(luck, "New ", "Used ");
        setDescription(String.format("%s is a light armor set was used during the great war.", getName()));
    }
    public CombatArmor(String name, int armorValue, int value){
        super(name,armorValue,value);
    }
}
