package gameobjects.Items.Armors;

import gameobjects.Items.Armor;

public class PlateArmor extends Armor {
    private String name = "Plate armor";
    private int armorValue = 10;
    private int value = 150;
    /**
     * @param type the version of the armor.
     */
    public PlateArmor(int type){
        super("Magic plate armor", 10, 150);
        if(type == -1){
            setName("Rusty "+name.toLowerCase());
            setArmorValue(armorValue - 8);
            setValue(value - 25);
        }else if(type == 1){
            setName("New "+name.toLowerCase());
            setArmorValue(armorValue + 8);
            setValue(value + 25);
        }
        setDescription(String.format("%s is what we call actual armor. It can even stop bullets.", getName()));
    }
    public PlateArmor(String name, int armorValue, int value){
        super(name,armorValue,value);
    }
}
