package gameobjects.Navigator;

/**
 * Keys returned from Navigators move method (Navigator.moveTile())
 * Keys should be read in mapScene and used to determine what actions should be taken.
 *  - MOVE_SUCCESSFUL indicates that player moved to an empty tile without issue, i.e.,
 *      the new tile was empty and within range, the primary occupant of the new tile
 *      been set to navigatorInstance.getPlayer() and the primary occupant of the previous
 *      tile has been set to null.
 *  - BAD_COORDINATES indicates the tile is out of range, and nothing has been done.
 *  - FAI
 * **/
public enum MoveKey {

    MOVE_SUCCESSFUL("move successful"), BAD_COORDINATES("bad coordinates"), FAILED_ATTACK("failed attack"),
    SUCCESSFUL_ATTACK("successful attack"),TILE_OCCUPIED("tile occupied"), LINK_TO_MAP("link to map"),
    CURRENT_TILE("current tile");
    MoveKey(String s) {
    }
}
