package gameobjects.SaveLoader;

import java.io.FileWriter;
import java.io.Writer;

public class Save extends SaveLoader{

    Writer writer = null;

    public Save() {
        try {
            writer = new FileWriter(file);
        } catch (Exception e){
            System.out.println(e);
        }

    }




}
