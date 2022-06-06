package gameobjects.Items.Factories;

import gameobjects.Items.*;
import gameobjects.Items.Consumables.ConsumableFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ItemFactory {
    WeaponFactory weaponFactory = new WeaponFactory();
    ConsumableFactory consumableFactory = new ConsumableFactory();
    ArmorFactory armorFactory = new ArmorFactory();

    /**
     * Creates a random items with random versions of those items.
     * @param Weapons the amount of weapons you want.
     * @param Armors the amount of armors you want.
     * @param Consumables the amount of consumables you want
     * @return an array of the items.
     */
    public Items[] makeRandomItems(int Weapons, int Armors, int Consumables){
        ArrayList<Items> stuff = new ArrayList<Items>();
        for(int i = 0; i < Weapons; i++){
            stuff.add(weaponFactory.createRandomWeapon());
        }
        for(int i = 0; i < Armors; i++){
            stuff.add(armorFactory.createRandomArmor());
        }
        for (int i = 0; i < Consumables; i++){
            stuff.add(consumableFactory.createRandomConsumable());
        }
        Items[] items = stuff.toArray(new Items[0]);
        return items;
    }

    /**
     * @return a random item such as an armor set, a weapon, or a consumable.
     */
    public Items createRandomItem(){
        Items items;
        Random rand = new Random();
        int type = rand.nextInt(6);
        int condition = rand.nextInt(3)-1;
        switch (type){
            default:
                items = weaponFactory.createRandomWeapon();
                break;
            case 1:
                items = armorFactory.createRandomArmor();
                break;
            case 2:
                items = consumableFactory.createRandomConsumable();
                break;
        }
        return items;
    }

    /**
     * Removes duplicates from the passed in item array.
     * @param items the array of items you want to remove duplicates from.
     * @return the new array of no duplicated items.
     */
    public Items[] noDup(Items[] items){
        ArrayList<Consumable> stuff = new ArrayList<Consumable>();
        ArrayList<Items> newItems = new ArrayList<Items>();
        for (Items i: items) {
            if(i.getType().equals("Consumable")){
                stuff.add((Consumable) i);
            } else {
                newItems.add(i);
            }
        }
        for (int j = 0; j < newItems.size(); j++){
            for (int i = newItems.size()-1; i >= 0; i--){
                if(newItems.get(j).equals(newItems.get(i)) && j != i){
                    newItems.remove(i);
                }
            }
        }
        while (stuff.size()!=0){
            Consumable con = stuff.get(0);
            int amount = 1;
            stuff.remove(0);
            for (int i = stuff.size()-1; i >= 0; i--){
                if(con.equals(stuff.get(i))){
                    stuff.remove(i);
                }
            }
            con.setAmount(amount);
            newItems.add(con);
        }
        Items[] back = newItems.toArray(new Items[0]);
        return back;
    }

    /**
     * Stacks all the consumables in the array that are the same.
     * @return the new version of the array.
     */
    public Items[] Stacker(Items[] items){
        ArrayList<Consumable> stuff = new ArrayList<Consumable>();
        ArrayList<Items> newItems = new ArrayList<Items>();
        for (Items i: items) {
            if(i.getType().equals("Consumable")){
                stuff.add((Consumable) i);
            } else {
                newItems.add(i);
            }
        }
        while (stuff.size()!=0){
            Consumable con = stuff.get(0);
            int amount = con.getAmount();
            stuff.remove(0);
            for (int i = stuff.size()-1; i >= 0; i--){
                if(con.equals(stuff.get(i))){
                    amount += stuff.get(i).getAmount();
                    stuff.remove(i);
                }
            }
            con.setAmount(amount);
            newItems.add(con);
        }
        Items[] back = newItems.toArray(new Items[0]);
        return back;
    }
}

    /*
    public Items[] Stacker(Items[] items){
        ArrayList<Items> stuff = new ArrayList<Items>(Arrays.asList(items));
        int count = 0;
        do {
            for(int i = 0; i < stuff.size(); i++){
                if(stuff.get(count).getName().equals(stuff.get(i).getName())&& i != count){
                    if(stuff.get(i).getType().equals("Consumable")){
                        stuff.set(count, ((Consumable)stuff.get(i)).stack((Consumable) stuff.get(count)));
                        stuff.remove(i);
                        count = 0;
                    }
                    break;
                }
            }
            count++;
        } while (count < stuff.size());
        Items[] back = stuff.toArray(new Items[0]);
        System.out.println("Done stacking");
        return back;
    }
     */
