package gameobjects.SaveLoader;

import gameobjects.Items.Items;
import gameobjects.Items.Weapon;
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
            saveItem(new Pistol(),saveFile);
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static void saveItem(Items item, FileWriter saveFile) throws IOException {

        String ifNull = "\nNULL";

        if(item !=null) {
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
        }else{
            for(int i = 0; i < 6; i++) {
                saveFile.write(ifNull);
            }
        }


    }

    public static Items loadItem(Scanner sc) {

        String ifNull = "NULL";

        String subclass, name, description;
        int minDamage, maxDamage, value;

        if (sc.hasNext()){
            subclass = sc.nextLine();
        }else{
            subclass = ifNull;
        }

        if (sc.hasNext()){
            name = sc.nextLine();
        }else{
            name = ifNull;
        }

        if (sc.hasNext()){
            minDamage = sc.nextInt();
        }else{
            minDamage = -1;
        }

        if (sc.hasNext()){
            maxDamage = sc.nextInt();
        }else{
            maxDamage = -1;
        }

        if (sc.hasNext()){
            value = sc.nextInt();
        }else{
            value = -1;
        }

        if (sc.hasNext()){
            description = sc.nextLine();
        }else{
            description = ifNull;
        }

        if(subclass.substring(0, Weapon.class.getName().length()).equals(Weapon.class.getName())) {
            return SaveLoader.weaponFactory.createItem(subclass,name,minDamage,maxDamage,
                    value, description);
        } else {
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
