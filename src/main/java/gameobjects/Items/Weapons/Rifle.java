package gameobjects.Items.Weapons;

import gameobjects.Items.Weapon;

public class Rifle extends Weapon {
    public Rifle(){
        super("Bat", 40, 50, 200);
        setDescription(String.format("%s is a rifle that was made before the Great War.", getName()));
    }
    public Rifle(int luck, boolean set){
        String[] name = {"New Rifle", "Rifle" , "Rusty Rifle"};
        int[] damage = {40,50};
        int value = 200;
        WeaponCondition temp = new WeaponCondition();
        temp.setVary(10);
        temp.makeWeapon(name, damage, value, luck, set);
        setWeapon(temp.Weapon());
        setDescription(String.format("%s is a rifle that was made before the Great War.", getName()));
    }
}
