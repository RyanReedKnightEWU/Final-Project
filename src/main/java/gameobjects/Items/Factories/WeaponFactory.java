package gameobjects.Items.Factories;

import gameobjects.Items.Weapon;

import gameobjects.Items.Weapons.*;

import java.util.Locale;
import java.util.Random;

public class WeaponFactory extends Factory{
    /**
     * @return a random weapon, with a random version of that weapon.
     */
    public Weapon createRandomWeapon(){
        Weapon weapon;
        Random rand = new Random();
        int type = rand.nextInt(6);
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

    /**
     * @return a random weak weapon.
     */
    public Weapon getWeakWeapon(){
        Weapon weapon;
        Random rand = new Random();
        int type = rand.nextInt(4);
        int condition = rand.nextInt(3)-1;
        switch (type){
            default:
                weapon = new Bat(condition);
                break;
            case 1:
                weapon = new Knife(condition);
                break;
            case 2:
                weapon = new Pistol(condition);
                break;
            case 3:
                weapon = new Sword(condition-1);
                break;
        }
        return weapon;
    }

    /**
     * @return a random medium weapon.
     */
    public Weapon getRegularWeapon(){
        Weapon weapon;
        Random rand = new Random();
        int type = rand.nextInt(5);
        int condition = rand.nextInt(3)-1;
        switch (type){
            default:
                weapon = new FaultyMagicRifle(condition);
                break;
            case 1:
                weapon = new Knife(condition+1);
                break;
            case 2:
                weapon = new Pistol(condition);
                break;
            case 3:
                weapon = new Rifle(condition);
                break;
            case 4:
                weapon = new Sword(condition);
                break;
        }
        return weapon;
    }

    /**
     * @return a random strong weapon.
     */
    public Weapon getStrongWeapon(){
        Weapon weapon;
        Random rand = new Random();
        int type = rand.nextInt(4);
        int condition = rand.nextInt(3)-1;
        switch (type){
            default:
                weapon = new FaultyMagicRifle(condition);
                break;
            case 1:
                weapon = new Knife(condition+1);
                break;
            case 2:
                weapon = new Rifle(condition);
                break;
            case 3:
                weapon = new Sword(condition+1);
                break;
        }
        return weapon;
    }

}
