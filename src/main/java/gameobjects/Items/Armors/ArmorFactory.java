package gameobjects.Items.Armors;

import gameobjects.Items.Armor;

public class ArmorFactory extends ArmorFactoryBase{
    @Override
    public Armor createArmor(String subclassKey, String name, int armorValue, int value) {
        if (subclassKey.startsWith(Clothes.class.getName())) {
            return new Clothes(name,armorValue,value);
        }else if (subclassKey.startsWith(CombatArmor.class.getName())){
            return new CombatArmor(name,armorValue,value);
        } else if (subclassKey.startsWith(LeatherArmor.class.getName())) {
            return new LeatherArmor(name,armorValue,value);
        } else if (subclassKey.startsWith(PlateArmor.class.getName())) {
            return new PlateArmor(name,armorValue,value);
        }else{
            return null;
        }
    }
}
