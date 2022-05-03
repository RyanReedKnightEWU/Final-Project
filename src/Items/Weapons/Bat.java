package Items.Weapons;

import Items.Weapon;

public class Bat extends Weapon {
    public Bat(int luck){
        super("Bat", 8, 10, 10);
        setVary(3);
        setCondition(luck, "New ", "Worn out ");
        setDescription(String.format("%s is better than nothing.", getName()));
    }
    public Bat(String name, int minDamage, int maxDamage, int value, int condition){
        super(name, minDamage, maxDamage, value);
        setConditionValue(condition);
    }
}
