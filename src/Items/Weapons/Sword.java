package Items.Weapons;

import Items.Weapon;

public class Sword extends Weapon {
    public Sword(int luck){
        super("Sword", 25, 35, 175);
        setValueVaryDivider(2);
        setVary(20);
        setCondition(luck, "Magic ", "Broken ");
        if(getCondition() == 1){
            setDescription(String.format("%s is a sword that was made by a postwar blacksmith that believes in the power of the blade", getName()));
        }else if(getCondition() == -1){
            setDescription(String.format("%s is a sad sight to see, once a powerful blade but now reduce to garbage", getName()));
        }else {
            setDescription(String.format("%s is just a regular weapon that is unexciting.", getName()));
        }
    }
}
