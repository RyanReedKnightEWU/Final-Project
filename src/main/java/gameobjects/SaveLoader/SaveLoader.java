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
            Items[] arr = {new Pistol(),new Clothes(4), new throwingKnife()};

            for (Items items : arr) {
                saveItem(items, saveFile);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }



    }
    private static void saveItem(Items item, FileWriter saveFile) throws IOException {

        String ifNull = "\nNULL";


        /* Start by saving fields common to all subclasses of Items class*/
        saveFile.write(item.getClass().getName());
        // Save Name
        if (item.getName() != null) {
            saveFile.write("\n"+item.getName());
        }else{
            saveFile.write(ifNull);
        }
        // Save minDamage
        if(item.getMinDamage() != null) {
            saveFile.write("\n" + item.getMinDamage());
        }else {
            saveFile.write(ifNull);
        }
        // Save maxDamage
        if(item.getMaxDamage() != null) {
            saveFile.write("\n" + item.getMaxDamage());
        }else {
            saveFile.write(ifNull);
        }
        // Save value
        if (item.getValue()!=null) {
            saveFile.write("\n" + item.getValue());
        }else {
            saveFile.write(ifNull);
        }
        // Save description
        if(item.getDescription()!=null) {
            saveFile.write("\n"+item.getDescription());
        }else{
            saveFile.write(ifNull);
        }

        // Save Fields specific to Consumable if class name indicates the class is Consumable
        if (item.getClass().getName().startsWith(Consumable.class.getName())) {
            saveFile.write(((Consumable) item).getHeal());
            saveFile.write(((Consumable) item).getAmount());
        }
        // Save Fields specific to Armor if class name indicates the class is Armor
        else if (item.getClass().getName().startsWith(Armor.class.getName())) {
            saveFile.write(((Armor)item).getArmorValue());
            saveFile.write(((Armor)item).getVary());
        }
    }

    public static Items loadItem(Scanner sc) {

        String ifNull = "NULL";

        String subclass = sc.nextLine();
        String name = sc.nextLine();
        int minDamage = sc.nextInt();
        int maxDamage = sc.nextInt();
        int value = sc.nextInt();
        String description = sc.nextLine();


        if (subclass.startsWith(Weapon.class.getName())) {
            return (new WeaponFactory()).createWeapon(subclass,name,minDamage,
                    maxDamage,value,description);
        }else if(subclass.startsWith(Consumable.class.getName())){
           int heal = sc.nextInt();
           int amount = sc.nextInt();

           return (new ConsumableFactory()).createConsumable(subclass,name,minDamage,
                   maxDamage,heal,value,description,amount);
        }
        else if(subclass.startsWith(Armor.class.getName())){
            int armor = sc.nextInt();
            int vary = sc.nextInt(); // ASK ABOUT VARY


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
