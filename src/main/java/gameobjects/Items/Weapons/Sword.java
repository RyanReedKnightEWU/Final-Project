package gameobjects.Items.Weapons;

import gameobjects.Items.Weapon;

public class Sword extends Weapon {
    /**
     * Makes the default version of the weapon.
     */
    public Sword(){
        super("Sword", 25, 35, 175);
        setDescription(String.format("%s is just a regular weapon that is unexciting.", getName()));
    }
    /**
     * @param type the version of the weapon.
     */
    public Sword(int type){
        super("Sword", 25, 35, 175);
        String[] name = {"Magic sword", "Sword", "Broken sword"};
        int[] damage = {20,25};
        int value = 175;
        int vary = 10;
        setDescription(String.format("%s is just a regular weapon that is unexciting.", getName()));
        if(type == -1){
            setName(name[2]);
            setValue(value - 50);
            setDamage(damage[0] - vary, damage[1] - vary);
            setDescription(String.format("%s is a sad sight to see, once a powerful blade but now reduce to garbage", getName()));
        }else if(type == 1){
            setName(name[0]);
            setValue(value + 50);
            setDamage(damage[0] + vary, damage[1] + vary);
            setDescription(String.format("%s is a sword that was made by a postwar blacksmith that believes in the power of the blade", getName()));
        }
    }
}
