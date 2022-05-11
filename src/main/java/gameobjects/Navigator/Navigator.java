package gameobjects.Navigator;

import Map.MapBase;
import gameobjects.Entity.Entity;
import gameobjects.Tile.TileBase;

import java.util.HashMap;

public class Navigator {

    private static Navigator uniqueInstance;
    private MapBase currentMap;
    private TileBase currentTile;
    private HashMap<String,Integer> position;
    private String rowKey, columnKey;
    private Entity player;

    /**
     * Defines Navigator, called in getInstance method if uniqueInstance is not null.
     * @param player - Entity to represent user.
     * @param map - the current map the player is on.
     * @param playerColumnPosition - horizontal coordinate of tile on tile matrix
     * @param playerRowPosition - vertical coordinate of tile on tile matrix
     * **/
    private Navigator(Entity player, MapBase map, int playerRowPosition, int playerColumnPosition) {

        this.player = player;
        this.currentMap = map;
        this.position = new HashMap<String, Integer>();
        this.rowKey = "row";
        this.columnKey = "column";
        this.position.put(this.rowKey,playerRowPosition);
        this.position.put(this.columnKey,playerColumnPosition);
        this.currentMap.addEntity(player,playerRowPosition,playerColumnPosition);
    }

    // Getters and setters.
    public static Navigator getInstance(Entity player, MapBase map, int xCoordinate, int yCoordinate) {

        if (Navigator.uniqueInstance == null) {

            Navigator.uniqueInstance = new Navigator(player, map,xCoordinate,yCoordinate);

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
        return this.currentMap.getTile(this.position.get(this.rowKey),
                this.position.get(this.columnKey));
    }

    public void setCurrentTile(TileBase currentTile) {
        this.currentTile = currentTile;
    }


    public void setCurrentMap(MapBase map) {
        this.currentMap = map;
    }
    public MapBase getCurrentMap() {
        return this.currentMap;
    }

    public int[] getPosition() {
        return new int[] {this.position.get(this.rowKey),
                this.position.get(this.columnKey)};
    }
    public int getCurrentRow() {
        return this.position.get(this.rowKey);
    }
    public int getCurrentColumn() {
        return this.position.get(this.columnKey);
    }

    public void setPosition(int newRow, int newColumn) {
        this.position.put(this.rowKey, newRow);
        this.position.put(this.columnKey, newColumn);
    }

    /**
     * Move to tile at new coordinates, returns string which is processed by other functions,
     * (e.g., if it returns "tile-occupied", another method will determine is that occupant is an enemy
     * and respond accordingly)
     * @param row - new row position, cannot be more than one tile away from current row.
     * @param column - new column position, cannot be more than one tile away from current column.
     * returns MoveKey, which is handled by the scene.
     * **/
    public MoveKey moveTile(int row, int column) {

        int verticalDistance = Math.abs(this.getCurrentRow() - row),
                horizontalDistance = Math.abs(this.getCurrentColumn() - column);

        if (row >= this.currentMap.getRows() || column >= this.currentMap.getColumns()
                || column < 0 || row < 0 || horizontalDistance > 1 || verticalDistance > 1) {
            // Bad coordinates, do nothing.
            return MoveKey.BAD_COORDINATES;
        }

        TileBase toMove = this.currentMap.getTile(row, column);

        if (toMove.getPrimaryOccupant() == null) {
            toMove.setPrimaryOccupant(this.player);
            this.currentMap.getTile(this.getCurrentRow(),this.getCurrentColumn()).setPrimaryOccupant(null);
            this.setPosition(row,column);
            // move successful, map needs to be updated.
            return MoveKey.MOVE_SUCCESSFUL;
        }
        else if(toMove.getLinkToMap() != null) {
            return MoveKey.LINK_TO_MAP;
        }
        else {
            return MoveKey.BAD_COORDINATES;
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