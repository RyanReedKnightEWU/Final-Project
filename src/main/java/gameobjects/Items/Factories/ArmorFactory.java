package gameobjects.Items.Factories;

import gameobjects.Items.Armor;
import gameobjects.Items.Armors.CombatArmor;
import gameobjects.Items.Armors.LeatherArmor;
import gameobjects.Items.Armors.PlateArmor;

import java.util.Random;

public class ArmorFactory {

    public Armor createRandomArmor(){
        Armor armor;
        Random rand = new Random();
        int type = rand.nextInt(3);
        int condition = rand.nextInt(3)-1;
        switch (type){
            default:
                armor = new LeatherArmor(condition);
                break;
            case 1:
                armor = new CombatArmor(condition);
                break;
            case 2:
                armor = new PlateArmor(condition);
                break;
        }
        return armor;
    }


}
