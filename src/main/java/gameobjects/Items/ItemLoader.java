package gameobjects.Items;

import gameobjects.Items.Armors.ArmorFactory;
import gameobjects.Items.Consumables.ConsumableFactory;
import gameobjects.Items.Weapons.WeaponFactory;
import gameobjects.SaveLoader.SaveLoader;
import java.util.LinkedList;
import java.util.Scanner;

public class ItemLoader extends SaveLoader<Items> {

    public Items load(Scanner sc) throws LeaveFunction {
        return load(sc,sc.nextLine());
    }
    public Items load(Scanner sc, String subclass){

        String name;
        int minDamage;
        int maxDamage;
        int value;
        String description;

        if (subclass.startsWith(Weapon.class.getName())) {

            sc.nextLine();  // Read "WEAPON"
            name = sc.nextLine();   // Read name
            minDamage = Integer.parseInt(sc.nextLine());    // Read minDamage
            maxDamage = Integer.parseInt(sc.nextLine());    // Read maxDamage
            value = Integer.parseInt(sc.nextLine());    // Read value
            description = sc.nextLine();    // Read description

            return (new WeaponFactory()).createWeapon(subclass,name,minDamage,
                    maxDamage,value,description);
        }else if(subclass.startsWith(Consumable.class.getName())){

            sc.nextLine();
            name = sc.nextLine();
            minDamage = Integer.parseInt(sc.nextLine());
            maxDamage = Integer.parseInt(sc.nextLine());
            int heal = Integer.parseInt(sc.nextLine());
            int amount = Integer.parseInt(sc.nextLine());
            value = Integer.parseInt(sc.nextLine());
            description = sc.nextLine();

            return (new ConsumableFactory()).createConsumable(subclass,name,minDamage,
                    maxDamage,heal,value,description,amount);
        }
        else if(subclass.startsWith(Armor.class.getName())){

            sc.nextLine();
            name = sc.nextLine();
            int armor = Integer.parseInt(sc.nextLine());;
            value = Integer.parseInt(sc.nextLine());;
            description = sc.nextLine();

            return (new ArmorFactory()).createArmor(subclass,name,armor,value);
        }
        else {
            throw new IllegalArgumentException("bad param loadItem method in SaveLoad class. \"" +
                    subclass + "\" is not a valid subclass.");
        }
    }
    public Items[] loadArray(Scanner sc) {

        LinkedList<Items> list = new LinkedList<>();
        Items[] ret = null;

        String header = sc.nextLine();

        while(!header.equals(SaveLoader.getEndArrKey())) {

            list.add(load(sc,header));
            header = sc.nextLine();

        }

        return list.toArray(new Items[0]);


        /*
        try {
            // Loop until LeaveFunction is thrown.
            while(true) {
                list.add(this.load(sc));
            }
        }catch (LeaveFunction lf) {
             ret = list.toArray(new Items[0]);
        }*/
        //return ret;
    }
}