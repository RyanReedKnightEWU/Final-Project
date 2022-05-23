package gameobjects.Items.Armors;

import gameobjects.Items.Armor;

import java.util.Locale;

public class LeatherArmor extends Armor {
    private String name = "Leather armor";
    private int armorValue = 5;
    private int value = 75;
    public LeatherArmor(int type){
        super("Leather armor", 5, 75);
        if(type == -1){
            setName("Worn out "+name.toLowerCase());
            setArmorValue(armorValue - 2);
            setValue(value - 25);
        }else if(type == 1){
            setName("New "+name.toLowerCase());
            setArmorValue(armorValue + 2);
            setValue(value + 25);
        }
        setDescription(String.format("%s is a armor set made out of animal hide.", getName()));
    }
    public LeatherArmor(String name, int armorValue, int value) {
        super(name, armorValue, value);
    }
}
