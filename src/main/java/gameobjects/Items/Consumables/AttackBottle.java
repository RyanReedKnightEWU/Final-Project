package gameobjects.Items.Consumables;

import gameobjects.Items.Consumable;

import java.util.Random;

public class AttackBottle extends Consumable {
    public AttackBottle(int type){
        super("Bottled Grenade", 250);
        setDamage(200,250);
        setDescription(String.format("Why would they put a grenade in a bottle?"));
        if(type == -1){
            old();
        }else if(type == 1){
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
