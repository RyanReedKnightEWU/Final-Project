package Entity;

public class Player extends Entity{
    public Player(int health, int damage, int defense, String name) {
        super(health, damage, defense, name);
    }

    public void heal(int heal) {

    }

    public int takeDamage(int damage) {

        return 0;
    }

    public int basicAttack() {

        return 0;
    }
}
