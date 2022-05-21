package gameobjects.Items.Weapons;

import gameobjects.Items.Weapon;

public class Knife extends Weapon {
    public Knife(){
        super("Knife", 20, 25, 100);
        setDescription(String.format("%s is a typical kitchen knife.\n", getName()));
    }
    public Knife(int luck, boolean set){
        String[] name = {"Rust knife","Knife","New knife"};
        int[] damage = {15,20};
        int value = 20;
        WeaponCondition temp = new WeaponCondition();
        temp.setVary(5);
        temp.makeWeapon(name, damage, value, luck, set);
        setWeapon(temp.Weapon());
        setDescription(String.format("%s is a typical kitchen knife.\n", getName()));
    }
    // Fully defined constructor, necessary for load methods (Ryan Knight 21 May 2022)
    public Knife (String name, int minDamage, int maxDamage, int value, String discription){
        super(name,minDamage,maxDamage,value,discription);
    }
}
