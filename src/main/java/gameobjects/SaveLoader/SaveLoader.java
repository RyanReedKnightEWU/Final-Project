package gameobjects.SaveLoader;

import java.io.File;

public class SaveLoader {

    protected File file = new File("Save.txt");

    public SaveLoader(){
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (Exception e){
                System.out.println(e);
            }
        }
    }

}
