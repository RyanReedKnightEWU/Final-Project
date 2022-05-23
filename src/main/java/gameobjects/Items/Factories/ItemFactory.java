package gameobjects.Items.Factories;

import gameobjects.Items.*;

import java.util.ArrayList;

public class ItemFactory {
    String AllWeapons[] = {"Bat", "FaultyMagicRifle"};

    public Items[] makeItems(int Weapons, int Armors, int Consumables){
        ArrayList<Items> stuff = new ArrayList<Items>();
        for(int i = 0; i < Weapons; i++){
            //
        }
        for(int i = 0; i < Armors; i++){

        }
        for (int i = 0; i < Consumables; i++){

        }
        return (Items[]) stuff.toArray();
    }

}
