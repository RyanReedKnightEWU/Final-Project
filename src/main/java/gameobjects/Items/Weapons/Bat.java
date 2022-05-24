package gameobjects.Items.Weapons;

import gameobjects.Items.Weapon;

public class Bat extends Weapon {
    public Bat(){
        super("Bat", 10, 15, 10);
        setDescription(String.format("%s is better than nothing.\n", getName()));
    }

    public Bat(int type){
        super("Bat", 10, 15, 10);
        String[] name = {"Old bat","Bat","New bat"};
        int[] damage = {10,15};
        int value = 10;
        if(type == -1){
            setName(name[0]);
            setValue(value - 2);
            setDamage(damage[0] - 2, damage[1] - 2);
        }else if(type == 1){
            setName(name[2]);
            setValue(value + 2);
            setDamage(damage[0] + 2, damage[1] + 2);
        }
        setDescription(String.format("%s is better than nothing.\n", getName()));
    }
    // Fully defined constructor, necessary for load methods (Ryan Knight 21 May 2022)
    public Bat (String name, int minDamage, int maxDamage, int value, String discription){
        super(name,minDamage,maxDamage,value,discription);
    }
}
