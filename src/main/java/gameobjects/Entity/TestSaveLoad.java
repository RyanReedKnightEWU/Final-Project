package gameobjects.Entity;

import gameobjects.Entity.SaveLoad.EntityLoader;
import gameobjects.Items.Items;
import gameobjects.SaveLoader.SaveLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TestSaveLoad {

    public static void main(String[] args) throws IOException, SaveLoader.LeaveFunction {

        String name = "Azog", file = "goblin.txt";
        Entity entA = new Goblin(name);

        System.out.println(entA.equals((new Goblin(name))));

        FileWriter fileWriter = new FileWriter(new File(file));
        entA.saveInstance(fileWriter);
        fileWriter.close();

        Scanner sc = new Scanner(new File(file));

        Entity entB = (new EntityLoader()).load(sc);
        sc.close();

        System.out.println("RESULT: " + entB.equals(entA));

        System.out.println("EntA\t" + entA.getInventory().size() + "\tEntb\t" + entB.getInventory().size());



    }

}
