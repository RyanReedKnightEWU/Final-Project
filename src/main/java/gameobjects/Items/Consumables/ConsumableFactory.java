package gameobjects.Items.Consumables;

import gameobjects.Items.Consumable;

public class ConsumableFactory extends ConsumableFactoryBase{
    @Override
    public Consumable createConsumable(String subclassKey, String name, int minDamage, int maxDamage,
                                       int heal, int value, String description, int amount) {

        if(subclassKey.startsWith(AttackBottle.class.getName())){
            return new AttackBottle(name, minDamage, maxDamage,heal,value,description,amount);
        }else if(subclassKey.startsWith(healthPotion.class.getName())) {
            return new healthPotion(name, minDamage, maxDamage,heal,value,description,amount);
        }else if(subclassKey.startsWith(throwingKnife.class.getName())) {
            return new throwingKnife(name, minDamage, maxDamage,heal,value,description,amount);
        }else{
            return null;
        }

    }
}
