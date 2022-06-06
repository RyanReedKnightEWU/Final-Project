package gameobjects.Items.Weapons;

import gameobjects.Items.Weapon;

public class Pistol extends Weapon{
    /**
     * Makes the default version of the weapon.
     */
    public Pistol(){
        super("Pistol", 20, 25, 100);
        setDescription(String.format("%s is a handgun that was made before the Great War.", getName()));
    }
    /**
     * @param type the version of the weapon.
     */
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
            setName(name[2]);
            setValue(value + 25);
            setDamage(damage[0] + vary, damage[1] + vary);
        }
        setDescription(String.format("%s is a handgun that was made before the Great War.", getName()));
    }

    // Fully defined constructor, necessary for load methods (Ryan Knight 21 May 2022)
    /**
     * Fully defined constructor, necessary for load methods.
     */
    public Pistol (String name, int minDamage, int maxDamage, int value, String discription){
        super(name,minDamage,maxDamage,value,discription);
    }
}
