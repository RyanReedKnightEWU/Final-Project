package gameobjects.Items;

import gameobjects.SaveLoader.Savable;

import java.io.FileWriter;
import java.io.IOException;

public abstract class Items implements Comparable<Items>, Savable {
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

    @Override
    public void saveInstance(FileWriter saveFile) throws IOException {
        saveFile.write(this.getClass().getName() + "\n");
        saveFile.write(this.save());

        System.out.println("SAVED: " + name);
    }



    @Override
    public int compareTo(Items item) {
        if (item == null) {
            return -1;
        } else if (item == this) {
            return 0;
        }

        if(this.getClass().getName().equals(item.getClass().getName())) {

            if (this.getName().equals(item.getName())) {

                if (this.value.equals(item.getValue())) {

                    return this.minDamage.compareTo(item.minDamage);

                }else {
                    return this.getValue().compareTo(item.getValue());
                }

            }else{
                return this.getName().compareTo(item.getName());
            }

        }else{
            return this.getClass().getName().compareTo(item.getClass().getName());
        }

    }
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Items)) {
            return false;
        }
        return this.compareTo((Items)obj) == 0;
    }
}
