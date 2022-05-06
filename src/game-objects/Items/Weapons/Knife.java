package Items.Weapons;

import Items.Weapon;

public class Knife extends Weapon {
    public Knife(){
        super("Knife", 20, 25, 100);
        setDescription(String.format("%s is a typical kitchen knife.", getName()));
    }
    public Knife(int luck, boolean set){
        String[] name = {"Rust knife","Knife","New knife"};
        int[] damage = {15,20};
        int value = 20;
        WeaponCondition temp = new WeaponCondition();
        temp.setVary(5);
        temp.makeWeapon(name, damage, value, luck, set);
        setWeapon(temp.Weapon());
        setDescription(String.format("%s is a typical kitchen knife.", getName()));
    }
}
