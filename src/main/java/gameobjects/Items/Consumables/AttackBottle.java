package gameobjects.Items.Consumables;

import gameobjects.Items.Consumable;

import java.util.Random;

public class AttackBottle extends Consumable {
    AttackBottle(int luck){
        super("Bottled Grenade", 250);
        setDamage(200,250);
        setDescription(String.format("Why would they put a grenade in a bottle?"));
        setCondition(luck);
    }

    private void setCondition(int luck){
        Random rand = new Random();
        int good = 20+luck*5;
        int bad = 45-luck*5;
        int value = rand.nextInt(101);
        if(value<bad){
            old();
        } else if(value>100-good){
            clean();
        }
    }

    private void old(){
        setDamage(100,150);
        setValue(100);
        setName("Bottled fireball");
        setDescription(String.format("A fireball preserved in a bottle"));
    }

    private void clean(){
        setDamage(350,500);
        setValue(500);
        setName("Bottled sun");
        setDescription(String.format("Burn you enemies to ashes with the power of a sun!"));
    }
}
