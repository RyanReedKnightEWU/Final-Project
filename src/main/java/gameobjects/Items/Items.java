package gameobjects.Items;

import gameobjects.SaveLoader.Savable;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Items is the parent class to items that the entities can wear and use.
 */
public abstract class Items implements Comparable<Items>, Savable {
    private String description = "";
    private String name = "";
    /**
     * The value in gold coins
     */
    private Integer value = 0;
    private Integer minDamage = 0;
    private Integer maxDamage = 0;
    /**
     * Is the item armor, a weapon, or a consumable?
     */
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

    public Integer getMaxDamage(){
        return maxDamage;
    }

    protected void setMaxDamage(int maxDamage){
        this.maxDamage = maxDamage;
    }

    public Integer getMinDamage(){
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

    public void setMaxDamage(Integer maxDamage) {
        this.maxDamage = maxDamage;
    }

    public void setMinDamage(Integer minDamage) {
        this.minDamage = minDamage;
    }

    /**
     * Saves the state of this instance of Items to a text file, which can be reloaded by ItemLoader.load(Scanner)
     * @param saveFile FileWriter used to write file to text file. Not closed in this method.
     * @throws IOException due to FileWriter.
     */
    @Override
    public void saveInstance(FileWriter saveFile) throws IOException {
        saveFile.write(this.getClass().getName() + "\n");
        saveFile.write(this.save());
    }

    /**
     * Compares by class name, name, value, and then minDamage.
     * @param item item to be compared.
     * @return integer.
     * */
    @Override
    public int compareTo(Items item) {
        if (item == null) {
            return -1;
        } else if (item == this) {
            return 0;
        }

        if (!getClass().getName().equals(item.getClass().getName())) {
            return getClass().getName().compareTo(item.getClass().getName());
        }
        if (!getName().equals(item.getName())) {
            return getName().compareTo(item.getName());
        }
        if (!value.equals(item.getValue())) {
            return getValue().compareTo(item.getValue());
        }
        if(!minDamage.equals(item.minDamage)) {
            return minDamage.compareTo(item.minDamage);
        }
        return 0;
    }

    /**
     * @param obj object being compared.
     * @return boolean indicating whether obj is equal to this item.
     * */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Items)) {
            return false;
        }
        return this.compareTo((Items)obj) == 0;
    }
}