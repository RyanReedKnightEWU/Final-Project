package gameobjects.Items.Consumables;

import gameobjects.Items.Consumable;

public class throwingKnife extends Consumable {
    throwingKnife(){
        super("Throwing knife", 25);
        setDamage(35, 50);
        setDescription(String.format("Throwing knives are self explanatory."));
    }
}
