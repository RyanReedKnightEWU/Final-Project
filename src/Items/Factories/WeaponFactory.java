package Items.Factories;

import Items.Weapon;
import Items.Weapons.Bat;
import Items.Weapons.Knife;
import Items.Weapons.Pistol;

import java.util.Locale;
import java.util.Random;

public class WeaponFactory extends Factory{

    public Weapon randomWeapon(int luck, int level){
        if(level == 1){
            Random rand = new Random();
            int r = rand.nextInt(101);
            if(r > 50){
                return new Pistol(luck);
            }else {
                return new Bat(luck);
            }
        }else {
            throw new IllegalArgumentException(String.format("Level: %d, is not a valid level", level));
        }
    }

    public Weapon createWeapon(String name, int luck){
        name = name.toLowerCase();
        if(name.equals("pistol")){
            return new Pistol(luck);
        }else if(name.equals("bat")){
            return new Bat(luck);
        }else if(name.equals("knife")){
            return new Knife(luck);
        }
        else {
            System.out.println(String.format("%s does not exist.", name));
            return null;
        }
    }

}
