package gameobjects.Navigator;

import gameobjects.Entity.Entity;
import gameobjects.Tile.MapBase;
import gameobjects.Tile.TileBase;

import java.util.Scanner;

public class Navigator {/*

    private static Navigator uniqueInstance;
    private MapBase currentMap;
    private TileBase currentTile;
    private Entity player;


    // Private constructor.
    private Navigator(Entity player, TileBase currentTile) {

        this.player = player;
        this.currentTile = currentTile;

    }

    // Getters and setters.
    public static Navigator getInstance(Entity player, TileBase currentTile) {

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

    public TileBase getCurrentTile() {
        return currentTile;
    }

    public void setCurrentTile(TileBase currentTile) {
        this.currentTile = currentTile;
    }


    public void startTurn() {

        // Option keys
        final String attack = "attack", move = "move", quit = "quit",
                // Prompt user for input.
        userDecisionPrompt = "Make a decision.\n" +
                "1) Move to another tile (type \"" + move + "\").\n" +
                "2) Attack an entity (type \"" + attack +"\").\n" +
                "3) Quit (type \"" + quit + "\").\n";

        Scanner sc = new Scanner(System.in);
        String key;

        boolean exitLoop = false;
        do {

            System.out.println(userDecisionPrompt);
            key = sc.nextLine();

            switch (key) {

                case move:
                    this.moveTile(sc);
                    break;
                case attack:
                    // If there is no-one to attack, go through loop again.
                    exitLoop = this.attackEntity(sc);
                    break;
                case quit:
                    exitLoop = this.quit(sc);
                    break;
                default:
                    System.out.println("Invalid option\n" + userDecisionPrompt);
                    sc.nextLine();
            }

        } while(!exitLoop);

        sc.close();
    }

    // Uses command from command prompt to move to a linked tile.
    public void moveTile(Scanner sc) {

        System.out.println("Where do you want to move.\n" +
                this.currentTile.getLinkedTileKeys());

        String key = sc.nextLine();

        while ( !this.currentTile.containsTileKey(key)) {

            System.out.println("This is not one of the places you can move to, " +
                    "select one of the following options.\n" + this.currentTile.getLinkedTileKeys());

            key = sc.nextLine();

        }

        this.currentTile = this.currentTile.getLinkedTile(key);
        System.out.println("Moved to " + key);

    }

    public boolean attackEntity(Scanner sc) {


        if (this.currentTile.getEntityKeys().isEmpty()) {
            System.out.println("There is no one here to attack.");
            return false;
        }

        // Prompt user for name of entity they want to attack and show them the entities on the tile.
        System.out.println("Who are you going to attack?\n");

        // Show user entities and there stats
        for (Entity ent : this.currentTile.getEntityMap().values()) {

            System.out.println(ent.getName() + " " + ent + "\n");

        }

        String key = sc.nextLine();

        // Loop until user gives a valid key.
        while (!this.currentTile.containsEntityKey(key)) {

            System.out.println("This entity is not here, select one of the listed entities. "
                + this.currentTile.getEntityKeys());
            key = sc.nextLine();

        }

        Entity defender = this.currentTile.getEntity(key);
        Attack.attack(this.player, defender);

        return true;
    }

    public boolean quit(Scanner sc) {
        System.out.println("Are you sure you want to quite? [Y/n]");

        String userInput = sc.nextLine().toLowerCase();

        if (userInput.equals("y")) {
            return true;
        }
        else if (userInput.equals("n")) {
            return false;
        }
        else {

            System.out.println("Invalid input.");
            return quit(sc);

        }

    }
*/
}