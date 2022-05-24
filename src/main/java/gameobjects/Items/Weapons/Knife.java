package gameobjects.Items.Weapons;

import gameobjects.Items.Weapon;

public class Knife extends Weapon {
    public Knife(){
        super("Knife", 20, 25, 100);
        setDescription(String.format("%s is a typical kitchen knife.\n", getName()));
    }
    public Knife(int type){
        super("Knife", 20, 25, 100);
        String[] name = {"Rust knife","Knife","New knife"};
        int[] damage = {20,25};
        int value = 100;
        int vary = 5;
        if(type == -1){
            setName(name[0]);
            setValue(value - 25);
            setDamage(damage[0] - vary, damage[1] - vary);
        }else if(type == 1){
            setName(name[2]);
            setValue(value + 25);
            setDamage(damage[0] + vary, damage[1] + vary);
        }
        setDescription(String.format("%s is a typical kitchen knife.\n", getName()));
    }
    // Fully defined constructor, necessary for load methods (Ryan Knight 21 May 2022)
    public Knife (String name, int minDamage, int maxDamage, int value, String discription){
        super(name,minDamage,maxDamage,value,discription);
    }
}
