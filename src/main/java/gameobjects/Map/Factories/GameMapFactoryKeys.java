package gameobjects.Map.Factories;

/**
 * Keys used to indicate to GameMapFactory what map and/or map sets it should make.
 * */
public enum GameMapFactoryKeys {

    FIRST_ARENA("first arena"), SECOND_ARENA("second arena"), HALL("hall"),
    DUNGEONS("dungeons"), STANDARD_MAP("full map"), STANDARD_MAP_SET("standard map set");


    GameMapFactoryKeys(String s) {
    }
}
