package Initiative;

import java.util.ArrayList;

public class NonPlayerCharacter extends Combatant {
    //Variables---------------------------------------------------------------------------------------------------------
    private int hitPoints = 0; //the hit point total of a NonPlayerCharacter
    private int armorClass = 0; //the armor class of a NonPlayerCharacter
    private String name = ""; //the name of a NonPlayerCharacter

    //Methods-----------------------------------------------------------------------------------------------------------

    /**
     * The getter for hitPoints
     * @return hitPoints
     */
    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * The getter for armorClass
     * @return armorClass
     */
    public int getArmorClass() {
        return armorClass;
    }

    /**
     * The getter for name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * The setter for hitPoints
     * @param hitPoints the hitPoints to be set
     */
    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    /**
     * The setter for armorClass
     * @param armorClass the armorClass to be set
     */
    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }

    /**
     * The setter for name
     * @param name the name to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method increments the NonPlayerCharacter's hit points by an increment
     * @param increment how much the hit points are incremented by
     */
    public void incrementHitPoints(int increment){
        this.hitPoints+=increment;
    }

    //Constructors------------------------------------------------------------------------------------------------------

    /**
     * Default Constructor
     */
    public NonPlayerCharacter() {
        super();
        this.hitPoints = 0;
        this.armorClass = 0;
        this.name = "No Name";
    }

    /**
     * Constructor
     * @param initiative the initiative of a NonPlayerCharacter
     * @param statusEffects the status effects affecting a NonPlayerCharacter
     * @param hitPoints the hit point total of a NonPlayerCharacter
     * @param armorClass the armor class of a NonPlayerCharacter
     * @param name the name of a NonPlayerCharacter
     */
    public NonPlayerCharacter(int initiative, ArrayList<StatusEffect> statusEffects, int hitPoints, int armorClass, String name) {
        super(initiative, statusEffects);
        this.hitPoints = hitPoints;
        this.armorClass = armorClass;
        this.name = name;
    }
}
