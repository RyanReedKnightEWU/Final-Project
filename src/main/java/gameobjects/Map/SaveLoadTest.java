package gameobjects.Map;

import gameobjects.Entity.Goblin;
import gameobjects.Entity.Player;
import gameobjects.Items.Armor;
import gameobjects.Items.Armors.Clothes;
import gameobjects.Items.Armors.PlateArmor;
import gameobjects.Items.Weapons.Knife;
import gameobjects.Items.Weapons.Pistol;
import gameobjects.Map.Factories.MapLoader;
import gameobjects.SaveLoader.SaveLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SaveLoadTest {

    public static void main(String[] args) throws IOException, SaveLoader.LeaveFunction {
        File mapFile = new File("map.txt");
        FileWriter fileWriter = new FileWriter(mapFile);

        MapBase map = new RectangularMap(5,"test map");
        MapBase map2 = new RectangularMap(5,"test map");

        System.out.println(map.equals(map2));

        map.addEntity(new Goblin("Azog",new Pistol(),new PlateArmor("dd",2,3)),1,2);
        map.addEntity(new Goblin("Marduk",new Knife(),new Clothes("dd",2,3)),0,0);
        map.addEntity(new Player(45,45,45,"Joao"),1,1);
        map.saveInstance(fileWriter);
        fileWriter.close();

        Scanner sc = new Scanner(mapFile);
        MapBase map3 = (new MapLoader()).load(sc);

        System.out.println(map.equals(map3));
        sc.close();
    }

}
