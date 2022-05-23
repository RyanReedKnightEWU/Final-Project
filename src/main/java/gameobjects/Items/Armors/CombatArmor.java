package gameobjects.Items.Armors;

import gameobjects.Items.Armor;

public class CombatArmor extends Armor {
    public CombatArmor(int type){
        super("Combat armor", 15, 150);
        if(type == -1){
            setName("Used combat armor");
            setArmorValue(10);
            setValue(100);
        }else if(type == 1){
            setName("Used combat armor");
            setArmorValue(20);
            setValue(200);
        }
        setDescription(String.format("%s is a light armor set was used during the great war.", getName()));
    }
    public CombatArmor(String name, int armorValue, int value){
        super(name,armorValue,value);
    }
}
