package Items.Weapons;

import Items.Weapon;

import java.util.Random;

public class WeaponCondition {

    private int condition = 0;
    private int minDamage;
    private int maxDamage;
    private int vary = 5;
    private int value;
    private int valueVaryDivider = 4;
    private int luck;
    private String[] names;
    private String name;

    public WeaponCondition(){
        //empty
    }
    public WeaponCondition(String[] name, int[] damage, int value, int luck, Boolean set){
        makeWeapon(name, damage, value, luck, set);
    }
    public void makeWeapon(String[] name, int[] damage, int value, int luck, Boolean set){
        this.minDamage = damage[0];
        this.maxDamage = damage[1];
        this.value = value;
        this.luck = luck;
        if(set){
            setCondition(luck);
        }else {
            setCondition();
        }
    }

    public void setVary(int vary){
        this.vary = vary;
    }

    public void setValueVaryDivider(int valueVaryDivider){
        this.valueVaryDivider = valueVaryDivider;
    }

    public int getCondition() {
        return condition;
    }

    public Weapon Weapon(){
        return new Weapon(this.name, this.minDamage, this.maxDamage, this.value);
    }

    private void old(){
        minDamage -= vary;
        maxDamage -= vary;
        value -= value/valueVaryDivider;
        name = names[0];
    }

    private void clean(){
        minDamage += vary;
        maxDamage += vary;
        value += value/valueVaryDivider;
        name = names[2];
    }

    private void setCondition(){
        Random rand = new Random();
        int good = 20+luck*5;
        int bad = 55-luck*5;
        int value = rand.nextInt(101);
        if(value<bad){
            old();
            condition = -1;
        } else if(value>100-good){
            clean();
            condition = 1;
        }else {
            name = names[1];
        }
    }

    private void setCondition(int luck){
        if(luck == -1){
            condition = -1;
            old();
        } else if(luck == 1){
            condition = 1;
            clean();
        }else {
            name = names[1];
        }
    }
}
