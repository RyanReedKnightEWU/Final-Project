package gameobjects.Items.Weapons;

import gameobjects.Items.Weapon;

public class FaultyMagicRifle extends Weapon {
    /**
     * Makes a faulty magic rifle.
     */
    public FaultyMagicRifle(int type){
        super("Faulty Magic Rifle", 25, 80, 175);
        setDescription(String.format("%s is a hit or miss kind of weapon, ether will do a lot of damage or not much.", getName()));
    }
    // Fully defined constructor, necessary for load methods (Ryan Knight 21 May 2022)
    /**
     * Fully defined constructor, necessary for load methods.
     */
    public FaultyMagicRifle (String name, int minDamage, int maxDamage, int value, String discription){
        super(name,minDamage,maxDamage,value,discription);
    }
}
