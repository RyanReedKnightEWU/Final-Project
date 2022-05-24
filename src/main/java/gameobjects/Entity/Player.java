package gameobjects.Entity;

import gameobjects.Items.Armor;
import gameobjects.Items.Weapon;

import java.util.ArrayList;

public class Player extends Entity{
    public Player(int health, int damage, int defense, String name) {
        super(health, damage, defense, name);
    }

    public Player(int health, int maxHealth, int damage, int defense, String name, ArrayList inventory,
                     Weapon weapon, Armor armor) {
        super(health, maxHealth, damage, defense, name, inventory, weapon, armor);
    }

    public int basicAttack() {

        return 0;
    }
}
