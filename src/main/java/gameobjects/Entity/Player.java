package gameobjects.Entity;

public class Player extends Entity{
    public Player(int health, int damage, int defense, String name) {
        super(health, damage, defense, name);
    }

    public int basicAttack() {

        return 0;
    }
}
