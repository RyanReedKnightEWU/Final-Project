package gameobjects.Items.Weapons;

import gameobjects.Items.ItemLoader;
import gameobjects.Items.Items;
import gameobjects.Items.Weapon;
import gameobjects.SaveLoader.SaveLoader;

import java.io.*;
import java.util.Scanner;

public class TestSaveLoad {

    public static void main(String[] args) throws IOException {

        String fileName = "saveItemTests.txt";
        File file = new File(fileName);

        Weapon weaponA = new Pistol();
        FileWriter fileWriter = new FileWriter(fileName,true);

        weaponA.saveInstance(fileWriter);
        fileWriter.close();




        Scanner sc;
        try {
            sc = new Scanner(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            Weapon weaponB = (Weapon) (new ItemLoader()).load(sc);

            System.out.println("Result: " + weaponA.equals(weaponB));
        } catch (SaveLoader.LeaveFunction e) {
            throw new RuntimeException(e);
        }
        sc.close();
    }

}
