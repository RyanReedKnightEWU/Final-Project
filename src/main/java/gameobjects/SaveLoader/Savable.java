package gameobjects.SaveLoader;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public interface Savable {
    public abstract void saveInstance(FileWriter saveFile) throws IOException;
}
