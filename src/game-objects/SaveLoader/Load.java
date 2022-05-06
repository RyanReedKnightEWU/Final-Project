package SaveLoader;

import java.util.Scanner;

public class Load extends SaveLoader{

    Scanner scanner = null;

    public Load(){
        try {
            scanner = new Scanner(file);
        } catch (Exception e){
            System.out.println(e);
        }

    }

}
