package gameobjects.Items.Weapons;

import gameobjects.Items.Weapon;

public class Pistol extends Weapon{
    public Pistol(){
        super("Pistol", 20, 25, 100);
        setDescription(String.format("%s is a handgun that was made before the Great War.\n", getName()));
    }
    public Pistol(int type){
        super("Pistol", 20, 25, 100);
        String[] name = {"Old pistol","Pistol","New pistol"};
        int[] damage = {20,25};
        int value = 100;
        int vary = 5;
        if(type == -1){
            setName(name[0]);
            setValue(value - 25);
            setDamage(damage[0] - vary, damage[1] - vary);
        }else if(type == 1){
            setName(name[0]);
            setValue(value + 25);
            setDamage(damage[0] + vary, damage[1] + vary);
        }
        setDescription(String.format("%s is a handgun that was made before the Great War.\n", getName()));
    }

    // Fully defined constructor, necessary for load methods (Ryan Knight 21 May 2022)
    public Pistol (String name, int minDamage, int maxDamage, int value, String discription){
        super(name,minDamage,maxDamage,value,discription);
    }



}
