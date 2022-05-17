package gameobjects.Items.Weapons;

import gameobjects.Items.Weapon;

public class Bat extends Weapon {
    public Bat(){
        super("Bat", 10, 15, 10);
        setDescription(String.format("%s is better than nothing.\n", getName()));
    }
    public Bat(int luck, boolean set){
        String[] name = {"Old bat","Bat","New bat"};
        int[] damage = {10,15};
        int value = 10;
        setWeapon(new WeaponCondition(name, damage, value, luck, set).Weapon());
        setDescription(String.format("%s is better than nothing.\n", getName()));
    }
}
