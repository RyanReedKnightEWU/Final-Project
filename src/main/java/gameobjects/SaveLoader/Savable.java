package gameobjects.SaveLoader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Interface for classes which can be saved to a text file. Classes that implement this interface need
 * a corresponding loader class. The corresponding loader class extends SaveLoader.
 * */
public interface Savable {
    /**
     * Save an instance of the class implementing Savable to a text file. Note this method should not close
     * the FileWriter it takes as an argument.
     * @param saveFile FileWriter used to write to text file.
     * @throws IOException due to FileWriter
     * */
    void saveInstance(FileWriter saveFile) throws IOException;

}
