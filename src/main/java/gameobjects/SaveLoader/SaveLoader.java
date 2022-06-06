package gameobjects.SaveLoader;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Base class for Loader classes. SaveLoader subclasses each correspond to a class that implements the Savable
 * interface. It is used to
 * */
public abstract class SaveLoader<E extends Savable> {

    /**
     * Static string used to indicate the end of an array when an array is being loaded.
     * Needs to be used when saving
     * */
    private final static String endArrKey = "END-ARR";

    /**
     * @return endArrayKey
     * */
    public static String getEndArrKey(){
        return SaveLoader.endArrKey;
    }

    /**
     * @return a rebuilt instance of E which as been saved via its saveInstance method
     * @param sc scanner, not closed in this method.
     * */
    public abstract E load(Scanner sc);

    /**
     * Load an array of rebuilt instances of E, end of array is indicated by end array key.
     * @param sc scanner, not closed in this method.
     * @return array of instances of E.
     * */
    public abstract E[] loadArray(Scanner sc);

    /**
     * Saves an array of classes via there saveInstance
     * @param arr array of Savables to be saved.
     * @param saveFile FileWriter to save state of game, not closed in method.
     * @throws IOException due to FileWriter.
     * */
    public static void saveArray(Savable[] arr,FileWriter saveFile) throws IOException {
        for(Savable element : arr) {
            element.saveInstance(saveFile);
        }
        saveFile.write(SaveLoader.endArrKey + "\n");
    }

}
