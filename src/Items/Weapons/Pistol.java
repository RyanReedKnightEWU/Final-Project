package Items.Weapons;

import Items.Weapon;

import java.util.Random;

public class Pistol extends Weapon{

    public Pistol(int luck){
        super("Pistol", 20, 25, 100);
        setVary(5);
        setCondition(luck, "New ", "Rusty ");
        setDescription(String.format("%s is a handgun that was made before the Great War.", getName()));
    }

}
