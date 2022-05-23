package gameobjects.SaveLoader;

import gameobjects.Items.*;
import gameobjects.Items.Armors.ArmorFactory;
import gameobjects.Items.Armors.Clothes;
import gameobjects.Items.Armors.PlateArmor;
import gameobjects.Items.Consumables.ConsumableFactory;
import gameobjects.Items.Consumables.throwingKnife;
import gameobjects.Items.Weapons.Pistol;
import gameobjects.Items.Weapons.WeaponFactory;
import gameobjects.Items.Weapons.WeaponFactoryBase;
import org.controlsfx.control.PropertySheet;

import java.io.*;
import java.util.Scanner;

public class SaveLoader {


    private static final WeaponFactoryBase weaponFactory = new WeaponFactory();
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

    public static void saveGame(String saveName) {

        String txtSfx = ".txt";
        if (!saveName.substring(saveName.length()-txtSfx.length()).equals(txtSfx)) {
            saveName += txtSfx;
        }

        try(FileWriter saveFile = new FileWriter(saveName)) {
            Items[] arr = {new Pistol(),new throwingKnife(),new Clothes(4),new PlateArmor(6) };

            for (Items item : arr) {
                System.out.println(item.getClass().getName());
                saveItem(item, saveFile);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }



    }
    private static void saveItem(Items item, FileWriter saveFile) throws IOException {

        String ifNull = "\nNULL";


        /* Start by saving fields common to all subclasses of Items class*/

        // Result of getClass().getName(), will be used later to determine which fields need to be loaded.
        String subclass = item.getClass().getName() + "\n";
        saveFile.write(subclass);
        saveFile.write(item.save());
    }

    public static Items loadItem(Scanner sc) {

        String ifNull = "NULL";
        // Read subclass and use it to determine what needs to be implemented.
        String subclass = sc.nextLine();
        String name;
        int minDamage;
        int maxDamage;
        int value;
        String description;


        if (subclass.startsWith(Weapon.class.getName())) {

            name = sc.nextLine();
            minDamage = sc.nextInt();
            maxDamage = sc.nextInt();
            value = sc.nextInt();
            description = sc.nextLine();

            return (new WeaponFactory()).createWeapon(subclass,name,minDamage,
                    maxDamage,value,description);
        }else if(subclass.startsWith(Consumable.class.getName())){

            name = sc.nextLine();
            minDamage = sc.nextInt();
            maxDamage = sc.nextInt();
            int heal = sc.nextInt();
            int amount = sc.nextInt();
            value = sc.nextInt();
            description = sc.nextLine();

           return (new ConsumableFactory()).createConsumable(subclass,name,minDamage,
                   maxDamage,heal,value,description,amount);
        }
        else if(subclass.startsWith(Armor.class.getName())){

            name = sc.nextLine();
            int armor = sc.nextInt();
            int vary = sc.nextInt();
            value = sc.nextInt();
            description = sc.nextLine();

            return (new ArmorFactory()).createArmor(subclass,name,armor,value);
        }
        else {
            return null;
        }
    }

    public static void main(String [] args) {
        saveGame("test");

        FileReader fileReader;
        try {
            fileReader = new FileReader("test.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Scanner sc = new Scanner(fileReader);


        Weapon pistol = (Weapon)loadItem(sc);
        System.out.println(pistol);
        sc.close();
    }
/*public abstract class Items {
    private String description = "";
    private String name = "";
    private Integer value = 0;
    private Integer minDamage = 0;
    private Integer maxDamage = 0;
    protected String type = "Item";

    protected void setValue(int value){
        this.value = value;
    }

    public Integer getValue(){
        return value;
    }

    protected void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    protected void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    protected Integer getMaxDamage(){
        return maxDamage;
    }

    protected void setMaxDamage(int maxDamage){
        this.maxDamage = maxDamage;
    }

    protected Integer getMinDamage(){
        return minDamage;
    }

    protected void setMinDamage(int minDamage){
        this.minDamage = minDamage;
    }

    public String save() {
        return null;
    }

    public String getType(){
        return type;
    }
}*/
}
