package Navigator;

import Entity.Entity;
import Tile.Tile;

import java.util.Scanner;

public class Navigator {

    private static Navigator uniqueInstance;
    private Tile currentTile;
    private Entity player;


    // Private constructor.
    private Navigator(Entity player, Tile currentTile) {

        this.player = player;
        this.currentTile = currentTile;

    }

    // Getters and setters.
    public static Navigator getInstance(Entity player, Tile currentTile) {

        if (Navigator.uniqueInstance == null) {

            Navigator.uniqueInstance = new Navigator(player, currentTile);

        }

        return Navigator.uniqueInstance;

    }

    public Entity getPlayer() {
        return player;
    }

    public void setPlayer(Entity player) {
        this.player = player;
    }

    public Tile getCurrentTile() {
        return currentTile;
    }

    public void setCurrentTile(Tile currentTile) {
        this.currentTile = currentTile;
    }



    // Uses command from command prompt to move to a linked tile.
    public void moveTile() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Where do you want to move.\n" +
                this.currentTile.getLinkedTileKeys());

        String key = sc.nextLine();

        while ( !this.currentTile.containsTileKey(key)) {

            System.out.println("This is not one of the places you can move to, " +
                    "select one of the following options.\n" + this.currentTile.getLinkedTileKeys());

            key = sc.nextLine();

        }

        sc.close();

        this.currentTile = this.currentTile.getLinkedTile(key);
        System.out.println("Moved to " + key);

    }

    public  void attackEntity() {

        // Prompt user for name of entity they want to attack and show them the entities on the tile.
        Scanner sc = new Scanner(System.in);
        System.out.println("Who are you going to attack?"
            + this.currentTile.getEntityKeys());
        String key = sc.nextLine();

        // Loop until user gives a valid key.
        while (!this.currentTile.containsEntityKey(key)) {

            System.out.println("This entity is not here, select one of the listed entities. "
                + this.currentTile.getEntityKeys());
            key = sc.nextLine();

        }

        Entity defender = this.currentTile.getEntity(key);
        Attack.attack(this.player, defender);

    }

}