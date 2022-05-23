package gameobjects.Items.Weapons;

import gameobjects.Items.Weapon;

public class faultyMagicRifle extends Weapon {
    public faultyMagicRifle(int type){
        super("Faulty Magic Rifle", 25, 80, 175);
        setDescription(String.format("%s is a hit or miss kind of weapon, ether will do a lot of damage or not much.\n", getName()));
    }
    // Fully defined constructor, necessary for load methods (Ryan Knight 21 May 2022)
    public faultyMagicRifle (String name, int minDamage, int maxDamage, int value, String discription){
        super(name,minDamage,maxDamage,value,discription);
    }
}
