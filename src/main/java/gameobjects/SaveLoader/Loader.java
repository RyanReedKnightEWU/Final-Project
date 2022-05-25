package gameobjects.SaveLoader;

import gameobjects.Items.Items;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public abstract class Loader<E extends Savable> {
    private final static String endArrKey = "END-ARR";
    public static String getEndArrKey(){
        return Loader.endArrKey;
    }

    public static class LeaveFunction extends Exception{

    }

    public void ThrowLeaveFunction() throws LeaveFunction {
        throw new LeaveFunction();
    }

    public abstract Savable load(Scanner sc) throws LeaveFunction;
    public abstract E[] loadArray(Scanner sc);

    public void saveArray(Savable[] arr,FileWriter saveFile) throws IOException {
        for(Savable element : arr) {
            element.saveInstance(saveFile);
        }
        saveFile.write(Loader.endArrKey + "\n");
    }

}
