package gameobjects.Items.Weapons;

import gameobjects.Items.Weapon;

public class BareHands extends Weapon {
    /**
     * Makes the default version of the weapon.
     */
    public BareHands(int type){
        super("Bare hands", 1, 3, 0);
        setDescription(String.format("%s is better than nothing.", getName()));
    }
    /**
     * Makes the default version of the weapon.
     */
    public BareHands(){
        super("Bare hands", 1, 3, 0);
        setDescription(String.format("%s is better than nothing.", getName()));
    }
    /**
     * Fully defined constructor, necessary for load methods.
     */
    public BareHands (String name, int minDamage, int maxDamage, int value, String discription){
        super(name,minDamage,maxDamage,value,discription);
    }
}
