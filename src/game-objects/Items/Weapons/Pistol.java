package Items.Weapons;

import Items.Weapon;

public class Pistol extends Weapon{
    public Pistol(){
        super("Pistol", 20, 25, 100);
        setDescription(String.format("%s is a handgun that was made before the Great War.", getName()));
    }
    public Pistol(int luck, boolean set){
        String[] name = {"Old pistol","Pistol","New pistol"};
        int[] damage = {20,25};
        int value = 100;
        setWeapon(new WeaponCondition(name, damage, value, luck, set).Weapon());
        setDescription(String.format("%s is a handgun that was made before the Great War.", getName()));
    }
}
