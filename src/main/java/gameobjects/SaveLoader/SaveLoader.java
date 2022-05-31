package gameobjects.SaveLoader;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public abstract class SaveLoader<E extends Savable> {
    private final static String endArrKey = "END-ARR";
    public static String getEndArrKey(){
        return SaveLoader.endArrKey;
    }

    public static class LeaveFunction extends Exception{

    }

    public void ThrowLeaveFunction() throws LeaveFunction {
        throw new LeaveFunction();
    }

    public abstract E load(Scanner sc) throws LeaveFunction;
    public abstract E[] loadArray(Scanner sc) throws LeaveFunction;

    public static void saveArray(Savable[] arr,FileWriter saveFile) throws IOException {
        for(Savable element : arr) {
            element.saveInstance(saveFile);
        }
        saveFile.write(SaveLoader.endArrKey + "\n");
    }

}
