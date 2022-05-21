package gameobjects.Items.Weapons;

import gameobjects.Items.Weapon;

public class BareHands extends Weapon {
    public BareHands(int luck){ //Make sure luck is 0 when passed in
        super("Bare hands", 1, 3, 0);
        setDescription(String.format("%s is better than nothing.\n", getName()));
    }
    public BareHands(){
        super("Bare hands", 1, 3, 0);
        setDescription(String.format("%s is better than nothing.\n", getName()));
    }
    // Fully defined constructor, necessary for load methods (Ryan Knight 21 May 2022)
    public BareHands (String name, int minDamage, int maxDamage, int value, String discription){
        super(name,minDamage,maxDamage,value,discription);
    }
}
