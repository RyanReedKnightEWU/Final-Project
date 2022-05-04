package Tile;

import Entity.Entity;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Tile {

    // List of entities occupying Tile, mapped to by their unique names.
    private HashMap<String, Entity> entityMap = new HashMap<String, Entity>();

    // List of connected tiles, mapped to by the tile label.
    private HashMap<String, Tile> linkedTiles = new HashMap<String, Tile>();

    // A label for the tile, must be unique, do not add setter.
    final private String tileLabel;

    // List of taken tile labels, for new tile label to be assigned,
    // it must be confirmed that the label is not taken.
    private static ArrayList<String> takenTileLables;

    public Tile(String label) {

        if (Tile.takenTileLables == null) {

            Tile.takenTileLables = new ArrayList<>();

        }
        else if (Tile.takenTileLables.contains(label)) {

            throw new IllegalArgumentException(label + " is in use, label param must be unique to this tile");

        }

        // Add label to list of taken labels
        Tile.takenTileLables.add(label);

        this.tileLabel = label;

    }

    public String getLabel() {

        return this.tileLabel;

    }

    /* Add a new tile to the map of linked tiles.
    * @param newLink, Tile to be added to map, newLink's label is used as the map key to the tile.
    * @return true if newLink added, false if item is already in map.
    * */
    public boolean addLinkedTile(Tile newLink) {

        // If tile is not already in linkedTiles map, add it using its label as the key.
        if (!this.linkedTiles.containsKey(newLink.tileLabel)) {

            this.linkedTiles.put(newLink.tileLabel, newLink);
            return true;

        }
        else {

            return false;

        }

    }

    /* Add array of Tile instances to map*/
    public void addLinkedTile(Tile[] tileArr) {

        for (Tile t:tileArr) {

            this.addLinkedTile(t);

        }

    }

    // Get Linked Tile
    public Tile getLinkedTile(String key) throws IllegalArgumentException {

        if (!this.linkedTiles.containsKey(key)) {
            throw new IllegalArgumentException("Key not found in Tile hashmap.");
        }

        return this.linkedTiles.get(key);

    }



    /* Add a new tile to the map of linked tiles.
     * @param newEntity, Entity instance to be added to map, newLink's label is used as the map key to the Entity.
     * @return true if Entity added, false if item is already in map.
     * */
    public boolean addEntity(Entity newEntity) {

        if (!this.entityMap.containsKey(newEntity.getName())) {

            this.entityMap.put(newEntity.getName(), newEntity);
            return true;

        }
        else {
            return false;
        }

    }

    // Add array of Entity instances to hashmap, skips over entities which are already included.
    public void addEntity(Entity[] entityArr) {

        for (Entity e : entityArr) {
            this.addEntity(e);
        }

    }

    // Pass in key to get Entity, throws exception of entity is not in hashMap,
    // This should be reconciled elsewhere. User should see Entity keys before giving input.
    public Entity getEntity(String key) throws IllegalArgumentException {

        if (!this.entityMap.containsKey(key)) {

            throw new IllegalArgumentException(" Key not found, valid Entity keys listed below.\n" +
                    this.getEntityKeys());

        }

        return this.entityMap.get(key);

    }


    // get keys
    private<T> String getKeys (HashMap<String,T> map) {

        StringBuilder ret = new StringBuilder();

        for (String s : map.keySet() ) {
            ret.append(s).append("\n");
        }

        return ret.toString();

    }
    // get linked tile, return string of linked tiles, will likely be used to indicate to player places they can go.
    public String getLinkedTileKeys() {
        return getKeys(this.linkedTiles);
    }
    // Get keys to entities
    public String getEntityKeys() {
        return getKeys(this.entityMap);
    }

    // Get map methods
    public HashMap<String, Entity> getEntityMap() {
        return this.entityMap;
    }

    public HashMap<String, Tile> getLinkedTiles() {
        return this.linkedTiles;
    }

    public abstract void attackEntity();
    public abstract void barterWithEntity(String key);

    // See if a key is in the Tile or Entity hashmap
    public boolean containsTileKey(String key) {
        return this.linkedTiles.containsKey(key);
    }

    public boolean containsEntityKey(String key) {
        return this.entityMap.containsKey(key);
    }

}
