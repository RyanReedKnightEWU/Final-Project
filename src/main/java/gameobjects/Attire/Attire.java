package gameobjects.Attire;

public abstract class Attire {

    // Defense if armor is in perfect condition.
    private Integer defaultDefense;
    // WeaponCondition: number between 0 and 100 which holds the condition of the armor.
    private Integer condition;


    public Attire(int defense, int condition) {

        if (defense < 0) {
            this.defaultDefense = 0;
        }

        this.condition = condition;

    }

    // Defense bonus is a factor of the condition*defaultdefense/100
    public int getDefenseBoost() {

        return this.defaultDefense*this.condition/100;

    }

    // Getters (Note: no setter for default defense)
    public Integer getDefaultDefense() {
        return defaultDefense;
    }

    public Integer getCondition() {
        return condition;
    }

    // Setters
    public void setCondition(Integer condition) {
        this.condition = condition;
    }
}
