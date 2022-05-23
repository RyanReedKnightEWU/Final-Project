package gameobjects.Items.Weapons;

import gameobjects.Items.Weapon;

public class Rifle extends Weapon {
    public Rifle(){
        super("Rifle", 40, 50, 200);
        setDescription(String.format("%s is a rifle that was made before the Great War.\n", getName()));
    }
    public Rifle(int type){
        super("Rifle", 40, 50, 200);
        String[] name = {"New Rifle", "Rifle" , "Rusty Rifle"};
        int[] damage = {40,50};
        int value = 200;
        int vary = 10;
        if(type == -1){
            setName(name[0]);
            setValue(value - 50);
            setDamage(damage[0] - vary, damage[1] - vary);
        }else if(type == 1){
            setName(name[0]);
            setValue(value + 50);
            setDamage(damage[0] + vary, damage[1] + vary);
        }
        setDescription(String.format("%s is a rifle that was made before the Great War.\n", getName()));
    }
    // Fully defined constructor, necessary for load methods (Ryan Knight 21 May 2022)
    public Rifle (String name, int minDamage, int maxDamage, int value, String discription){
        super(name,minDamage,maxDamage,value,discription);
    }
}
