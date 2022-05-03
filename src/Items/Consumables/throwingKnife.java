package Items.Consumables;

import Items.Consumable;

public class throwingKnife extends Consumable {
    throwingKnife(){
        super("Throwing knife", 25);
        setDamage(20, 25);
        setDescription(String.format("Throwing knives are self explanatory."));
    }

    public String toString(){
        return String.format("Name: %s\nDamage %d-%d\nUses: %d", getName(), getMinDamage(), getMaxDamage(), getAmount());
    }
}
