package gameobjects.Items.Weapons;

import gameobjects.Items.Items;
import gameobjects.Items.Weapon;

public class WeaponFactory extends WeaponFactoryBase{

    /**
     * Create Weapon - given key, which contains the class name, returns instance of class.
     *
     * @param key         - string which contains the items' subclass,
     *                    this string must be built by object.getClass().getName().
     * @param name        - name field in Weapon instance.
     * @param minDamage   - minDamage field in Weapon instance.
     * @param maxDamage   - maxDamage field in Weapon instance.
     * @param value       - value field in Weapon instance.
     * @param discription - discription field in Weapon instance.
     **/
    @Override
    public Weapon createWeapon(String key, String name, int minDamage, int maxDamage,
                               int value, String discription){
        if(key.equals(Pistol.class.getName())){
            return new Pistol(name,minDamage,maxDamage,value,discription);
        }else if (key.equals(BareHands.class.getName())) {
            return new BareHands(name, minDamage, maxDamage, value, discription);
        }else if(key.equals(Bat.class.getName())) {
            return new Bat(name, minDamage, maxDamage, value, discription);
        } else if (key.startsWith(Weapon.class.getName())){
            return new Weapon(name, minDamage, maxDamage, value, discription);
        }else {
            throw new IllegalArgumentException(
                    "Bad param createWeapon in WeaponFactory, " + key + " is not a valid key");
        }
    }

    @Override
    public Items createItem() {
        return null;
    }
}
