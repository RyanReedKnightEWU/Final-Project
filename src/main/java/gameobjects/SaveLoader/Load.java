package gameobjects.SaveLoader;

import gameobjects.Items.Armor;
import gameobjects.Items.Consumable;
import gameobjects.Items.Weapon;

import java.util.Scanner;

public class Load extends SaveLoader{

    Scanner scanner = null;

    public Load(){
        try {
            scanner = new Scanner(file);
        } catch (Exception e){
            System.out.println(e);
        }

        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
        }

    }

    private Weapon loadWeapon(){
        String temp = scanner.nextLine();
        String name = temp;
        temp = scanner.nextLine();
        int minDamage = Integer.parseInt(temp);
        temp = scanner.nextLine();
        int maxDamage = Integer.parseInt(temp);
        temp = scanner.nextLine();
        int value = Integer.parseInt(temp);
        temp = scanner.nextLine();
        String description = temp;
        Weapon weapon = new Weapon(name, minDamage, maxDamage, value, description);
        return weapon;
    }

    private Consumable loadConsumable(){
        String temp = scanner.nextLine();
        String name = temp;
        temp = scanner.nextLine();
        int minDamage = Integer.parseInt(temp);
        temp = scanner.nextLine();
        int maxDamage = Integer.parseInt(temp);
        temp = scanner.nextLine();
        int heal = Integer.parseInt(temp);
        temp = scanner.nextLine();
        int amount = Integer.parseInt(temp);
        temp = scanner.nextLine();
        int value = Integer.parseInt(temp);
        temp = scanner.nextLine();
        String description = temp;
        Consumable consumable = new Consumable( name, minDamage, maxDamage, heal, value, description, amount);
        return consumable;
    }

    private Armor loadArmor(){
        String temp = scanner.nextLine();
        String name = temp;
        temp = scanner.nextLine();
        int armorValue = Integer.parseInt(temp);
        temp = scanner.nextLine();
        int value = Integer.parseInt(temp);
        temp = scanner.nextLine();
        String description = temp;
        Armor armor = new Armor(name, armorValue, value, description);
        return armor;
    }

}
