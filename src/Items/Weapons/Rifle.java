package Items.Weapons;

import Items.Weapon;

public class Rifle extends Weapon {
    public Rifle(int luck){
        super("Bat", 40, 50, 200);
        setVary(10);
        setCondition(luck, "New ", "Rusty ");
        setDescription(String.format("%s is a rifle that was made before the Great War.", getName()));
    }
}