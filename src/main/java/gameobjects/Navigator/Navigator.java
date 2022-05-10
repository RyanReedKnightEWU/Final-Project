package gameobjects.Navigator;

import Map.MapBase;
import gameobjects.Entity.Entity;
import gameobjects.Tile.TileBase;

public class Navigator {

    private static Navigator uniqueInstance;
    private MapBase currentMap;
    private TileBase currentTile;
    private int[] position;
    private Entity player;

    /**
     * Defines Navigator, called in getInstance method if uniqueInstance is not null.
     * @param player - Entity to represent user.
     * @param map - the current map the player is on.
     * @param currentTile - tile player occupies.
     * @param xCoordinate - horizontal coordinate of tile on tile matrix
     * @param yCoordinate - vertical coordinate of tile on tile matrix
     * **/
    private Navigator(Entity player, MapBase map, TileBase currentTile, int xCoordinate, int yCoordinate) {

        this.player = player;
        this.currentMap = map;
        this.currentTile = currentTile;
        this.position = new int[]{xCoordinate, yCoordinate};
    }

    // Getters and setters.
    public static Navigator getInstance(Entity player, MapBase map, TileBase currentTile, int xCoordinate, int yCoordinate) {

        if (Navigator.uniqueInstance == null) {

            Navigator.uniqueInstance = new Navigator(player, map, currentTile,xCoordinate,yCoordinate);

        }

        return Navigator.uniqueInstance;

    }
    //
    public static Navigator getInstance() {
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


    public int[] getPosition() {
        return position;
    }

    public void setPosition(int xCoordinte, int yCoordinate) {
        this.position[0] = xCoordinte;
        this.position[1] = yCoordinate;
    }

    /**
     * Move to tile at new coordinates, returns string which is processed by other functions,
     * (e.g., if it returns "tile-occupied", another method will determine is that occupant is an enemy
     * and respond accordingly)
     * @param newXCoordinate - new x coordinate, cannot be more than one tile away from current x coordinate.
     * @param newYCoordinate - new y coordinate, cannot be more than one tile away from current y coordinate.
     * **/
    public String moveTile(int newXCoordinate, int newYCoordinate) {

        int distanceFromX = Math.abs(this.position[0] - newXCoordinate),
                distanceFromY = Math.abs(this.position[1] - newYCoordinate);

        if (newYCoordinate >= this.currentMap.getRows() || newXCoordinate >= this.currentMap.getColumns()
                || newYCoordinate < 0 || newXCoordinate < 0 || (distanceFromY + distanceFromY)!=2) {
            // Bad coordinates, do nothing.
            return "bad-coordinates";
        }

        TileBase toMove = this.currentMap.getTile(newYCoordinate, newXCoordinate);

        if (toMove.getPrimaryOccupant() == null) {
            this.currentTile = toMove;
            toMove.setPrimaryOccupant(this.player);
            // move successful, map needs to be updated.
            return "move-successful";
        }
        else {

            return "tile-occupied";
        }

    }

/*
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