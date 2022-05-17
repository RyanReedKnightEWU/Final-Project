package gameobjects.Items.Weapons;

import gameobjects.Items.Weapon;

public class Sword extends Weapon {
    public Sword(){
        super("Sword", 25, 35, 175);
        setDescription(String.format("%s is just a regular weapon that is unexciting.\n", getName()));
    }
    public Sword(int luck, boolean set){
        String[] name = {"Magic sword", "Sword", "Broken sword"};
        int[] damage = {20,25};
        int value = 175;
        WeaponCondition temp = new WeaponCondition();

        temp.setVary(20);
        temp.setValueVaryDivider(2);
        temp.makeWeapon(name, damage, value, luck, set);
        setWeapon(temp.Weapon());

        if(temp.getCondition() == 1){
            setDescription(String.format("%s is a sword that was made by a postwar blacksmith that believes in the power of the blade\n", getName()));
        }else if(temp.getCondition() == -1){
            setDescription(String.format("%s is a sad sight to see, once a powerful blade but now reduce to garbage\n", getName()));
        }else {
            setDescription(String.format("%s is just a regular weapon that is unexciting.\n", getName()));
        }
    }
}
