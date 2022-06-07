package gameobjects.Items;

import gameobjects.Items.Armors.ArmorFactory;
import gameobjects.Items.Consumables.ConsumableFactory;
import gameobjects.Items.Weapons.WeaponFactory;
import gameobjects.SaveLoader.SaveLoader;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Rebuilds saved items, can also save an array of items.
 * */
public class ItemLoader extends SaveLoader<Items> {

    /**
     * Reads, rebuilds and returns an Item object.
     * @param sc Scanner used to read file, not closed within this method.
     * @return rebuilt Item.
     * */
    public Items load(Scanner sc) {
        return load(sc,sc.nextLine());
    }

    /**
     * Reads, rebuilds and returns an Item object.
     * @param sc Scanner used to read file, not closed within this method.
     * @param subclass string which identifies the class, comes from getClass.getName() method when saved.
     * @return rebuilt Item.
     * */
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

    /**
     * Rebuild and return an array of items saved to a text file via saveInstance method.
     * @param sc scanner used to read text file, not closed within this method.
     * @return array of Items.
     * */
    public Items[] loadArray(Scanner sc) {

        LinkedList<Items> list = new LinkedList<>();
        String header = sc.nextLine();

        while(!header.equals(SaveLoader.getEndArrKey())) {

            list.add(load(sc,header));
            header = sc.nextLine();

        }

        return list.toArray(new Items[0]);
    }
}