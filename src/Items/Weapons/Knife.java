package Items.Weapons;

import Items.Weapon;

public class Knife extends Weapon {
    public Knife(int luck){
        super("Knife", 15, 20, 20);
        setVary(5);
        setCondition(luck, "New ", "Rusty ");
        setDescription(String.format("%s is a typical kitchen knife.", getName()));
    }
    public Knife(String name, int minDamage, int maxDamage, int value, int condition){
        super(name, minDamage, maxDamage, value);
        setConditionValue(condition);
    }
}
