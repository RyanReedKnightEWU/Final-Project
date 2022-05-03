package Items.Weapons;

import Items.Weapon;

public class BareHands extends Weapon {
    public BareHands(int luck){ //Make sure luck is 0 when passed in
        super("Bare hands", 1, 3, 0);
        setDescription(String.format("%s is better than nothing.", getName()));
    }
    public BareHands(){
        super("Bare hands", 1, 3, 0);
        setDescription(String.format("%s is better than nothing.", getName()));
    }
    public BareHands(String name, int minDamage, int maxDamage, int value, int condition){
        super(name, minDamage, maxDamage, value);
        setConditionValue(condition);
    }
}
