package Entity;

import Items.Weapon;
import Items.Weapons.BareHands;

public class Goblin extends Entity {

    public Goblin(String name) {
        //Find a more proper way to let an entity subclass pass its stats to Entity.
        super(75, 10, 3, name);
        Weapon hands = new BareHands(0);
        setWeapon(hands);
    }

    public void heal(int heal) {
        if ((getHealth() + heal) >= getMaxHealth()) {
            setHealth(getMaxHealth());
        }else {
            setHealth(getHealth() + heal);
        }
        System.out.println(heal + " health restored and " + getHealth() + " health remaining");
    }

    public void takeDamage(int damage) {
        int damageTaken = damage - getDefense();

        if (damageTaken < 1) { damageTaken = 1; }

        setHealth(getHealth()-damageTaken);

        System.out.println(damageTaken + " damage taken and " + getHealth() + " health remaining");
    }

    public void basicAttack() {
        int damageDealt = getDamage();
        damageDealt += getWeapon().getDamage();
        System.out.println(getDamage() + " base damage + " + getWeapon().getDamage() + " weapon damage");
    }
}
