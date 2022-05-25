package gameobjects.SaveLoader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public interface Savable {
    void saveInstance(FileWriter saveFile) throws IOException;

}
