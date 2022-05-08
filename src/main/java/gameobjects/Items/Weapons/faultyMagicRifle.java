package gameobjects.Items.Weapons;

import gameobjects.Items.Weapon;

public class faultyMagicRifle extends Weapon {
    public faultyMagicRifle(int luck){
        super("Faulty Magic Rifle", 25, 80, 175);
        setDescription(String.format("%s is a hit or miss kind of weapon, ether will do a lot of damage or not much.", getName()));
    }
}
