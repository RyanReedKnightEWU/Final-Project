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
}
