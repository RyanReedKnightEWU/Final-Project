package gameobjects.SaveLoader;

import gameobjects.Map.GameMapFactory;
import gameobjects.Map.GameMapFactoryKeys;
import gameobjects.Map.MapBase;
import gameobjects.Entity.Entity;
import gameobjects.Entity.SaveLoad.EntityLoader;
import gameobjects.Entity.Goblin;
import gameobjects.Items.*;
import gameobjects.Items.Armors.ArmorFactory;
import gameobjects.Items.Consumables.ConsumableFactory;
import gameobjects.Items.Weapons.WeaponFactory;
import gameobjects.Items.Weapons.WeaponFactoryBase;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {


    private static class LeaveFunction extends Exception {

    }
    // Key to end array int saveArr method, used to indicate array has ended in load method
    private static final String endArrKey = "END-ARR";

    private static final WeaponFactoryBase weaponFactory = new WeaponFactory();
    protected File file = new File("Save.txt");

    public Main(){
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (Exception e){
                System.out.println(e);
            }
        }
    }

    private static void saveItem(Items item, FileWriter saveFile) throws IOException {
        /* Start by saving fields common to all subclasses of Items class*/

        // Result of getClass().getName(), will be used later to determine which fields need to be loaded.
        String subclass = item.getClass().getName() + "\n";
        saveFile.write(subclass);
        saveFile.write(item.save());
    }

    private static void saveItemArray(Items[] itemArr, FileWriter saveFile) throws IOException {
        for(Items item:itemArr) {
            saveFile.write(item.getClass().getName() + "\n");
            item.saveInstance(saveFile);
            /*saveItem(item,saveFile);*/
        }
        saveFile.write(Main.endArrKey);
    }


    public static Items loadItem(Scanner sc) throws LeaveFunction {

        // Read subclass and use it to determine what needs to be implemented.
        String subclass = sc.nextLine();

        if (subclass.equals(Main.endArrKey)) {
            throw new LeaveFunction();
        }

        String type;
        String name;
        int minDamage;
        int maxDamage;
        int value;
        String description;


        if (subclass.startsWith(Weapon.class.getName())) {

            sc.nextLine();
            name = sc.nextLine();
            minDamage = Integer.parseInt(sc.nextLine());;
            maxDamage = Integer.parseInt(sc.nextLine());;
            value = Integer.parseInt(sc.nextLine());;
            description = sc.nextLine();

            return (new WeaponFactory()).createWeapon(subclass,name,minDamage,
                    maxDamage,value,description);
        }else if(subclass.startsWith(Consumable.class.getName())){

            sc.nextLine();
            name = sc.nextLine();
            minDamage = Integer.parseInt(sc.nextLine());
            maxDamage = Integer.parseInt(sc.nextLine());
            int heal = Integer.parseInt(sc.nextLine());
            int amount = Integer.parseInt(sc.nextLine());
            value = Integer.parseInt(sc.nextLine());
            description = sc.nextLine();

           return (new ConsumableFactory()).createConsumable(subclass,name,minDamage,
                   maxDamage,heal,value,description,amount);
        }
        else if(subclass.startsWith(Armor.class.getName())){

            sc.nextLine();
            name = sc.nextLine();
            int armor = Integer.parseInt(sc.nextLine());;
            value = Integer.parseInt(sc.nextLine());;
            description = sc.nextLine();

            return (new ArmorFactory()).createArmor(subclass,name,armor,value);
        }
        else {
            throw new IllegalArgumentException("bad param loadItem method in SaveLoad class. \"" +
                    subclass + "\" is not a valid subclass.");
        }
    }

    public static Items[] loadItemArray(Scanner sc){

        LinkedList<Items> list = new LinkedList<>();
        boolean flag = true;
        while(sc.hasNext()&&flag) {
            try {
                list.add(loadItem(sc));
            }catch (LeaveFunction lf) {
                flag = false;
            }
        }

        Items[] ret = new Items[list.size()];

        for(int i = 0; i < list.size(); ++i) {
            ret[i] = list.get(i);
        }
        return ret;
    }

    public static void saveGame(String saveName) {

        String txtSfx = ".txt";
        if (!saveName.endsWith(txtSfx)) {
            saveName += txtSfx;
        }

        try (FileWriter saveFile = new FileWriter(saveName)) {
            //saveItemArray(new Items[]{ new throwingKnife(), new Clothes(4), new PlateArmor(6),
            // new Pistol()},saveFile);
            (new Goblin("Jacki")).saveInstance(saveFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String [] args) {

        String saveName = "test";

        String txtSfx = ".txt",goblinName="jack";
        if (!saveName.endsWith(txtSfx)) {
            saveName += txtSfx;
        }

        try (FileWriter saveFile = new FileWriter(saveName)) {
            //saveItemArray(new Items[]{ new throwingKnife(), new Clothes(4), new PlateArmor(6),
            // new Pistol()},saveFile);
            (new Goblin(goblinName)).saveInstance(saveFile);

        } catch (IOException e) {
            e.printStackTrace();
        }

        FileReader fileReader;
        try {
            fileReader = new FileReader("test.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Scanner sc = new Scanner(fileReader);

       testSaveLoad();

        sc.close();
    }

    public static void testSaveLoad(){

        SaveLoader<Entity> entitySaveLoader = new EntityLoader();

        String goblinName = "Jack", saveName = "test.txt";

        try (FileWriter saveFile = new FileWriter(saveName)) {
            //saveItemArray(new Items[]{ new throwingKnife(), new Clothes(4), new PlateArmor(6),
            // new Pistol()},saveFile);
            MapBase map =(new GameMapFactory()).createMap(GameMapFactoryKeys.FIRST_ARENA.toString());
            map.saveInstance(saveFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileReader fileReader;
        try {
            fileReader = new FileReader("test.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Scanner sc = new Scanner(fileReader);

        try {
            Entity alpha = entitySaveLoader.load(sc);
            Entity beta = new Goblin(goblinName);

            compare(alpha.getHealth(),beta.getHealth());
            compare(alpha.getDamage(),beta.getDamage());

            System.out.println(alpha + "\n\n" + beta);

        } catch (SaveLoader.LeaveFunction e) {
            throw new RuntimeException(e);
        }

        sc.close();



    }
    public static void compare(Object a, Object b){
        System.out.println( a + " = " + b +" " + a==b );
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
