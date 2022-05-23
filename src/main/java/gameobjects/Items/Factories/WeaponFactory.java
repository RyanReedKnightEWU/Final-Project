package gameobjects.Items.Factories;

import gameobjects.Items.Weapon;

import gameobjects.Items.Weapons.*;

import java.util.Locale;
import java.util.Random;

public class WeaponFactory extends Factory{

    public Weapon createRandomWeapon(){
        Weapon weapon;
        Random rand = new Random();
        int type = rand.nextInt();
        int condition = rand.nextInt(3)-1;
        switch (type){
            default:
                weapon = new Bat(condition);
                break;
            case 1:
                weapon = new FaultyMagicRifle(condition);
                break;
            case 2:
                weapon = new Knife(condition);
                break;
            case 3:
                weapon = new Pistol(condition);
                break;
            case 4:
                weapon = new Rifle(condition);
                break;
            case 5:
                weapon = new Sword(condition);
                break;
        }
        return weapon;
    }

}
