package gameobjects.Items.Consumables;

import gameobjects.Items.Consumable;
import gameobjects.Items.Weapon;
import gameobjects.Items.Weapons.*;

import java.util.Random;

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
        } else if (subclassKey.startsWith(Consumable.class.getName())) {
            return new Consumable(name, minDamage, maxDamage,heal,value,description,amount);
        } else{
            throw new IllegalArgumentException("Bad param fo createConsumable in ConsumableFactory.\t"
                    + subclassKey + " is not a valid subclass.");
        }

    }

    public Consumable createRandomConsumable(){
        Consumable consumable;
        Random rand = new Random();
        int type = rand.nextInt(3);
        int condition = rand.nextInt(3)-1;
        int amount = rand.nextInt(5)+1;
        switch (type){
            default:
                consumable = new AttackBottle(condition);
                break;
            case 1:
                consumable = new healthPotion(condition);
                break;
            case 2:
                consumable = new throwingKnife();
                break;
        }
        consumable.setAmount(amount);
        return consumable;
    }

    public Consumable createRandomConsumable(int limit){
        Consumable consumable;
        Random rand = new Random();
        int type = rand.nextInt(3);
        int condition = rand.nextInt(3)-1;
        int amount = rand.nextInt(limit)+1;
        switch (type){
            default:
                consumable = new AttackBottle(condition);
                break;
            case 1:
                consumable = new healthPotion(condition);
                break;
            case 2:
                consumable = new throwingKnife();
                break;
        }
        consumable.setAmount(amount);
        return consumable;
    }

}
