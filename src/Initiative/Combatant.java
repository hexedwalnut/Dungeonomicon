package Initiative;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public abstract class Combatant implements Comparable<Combatant> {
    //Variables---------------------------------------------------------------------------------------------------------
    private int initiative = 0; //the initiative number for a combatant
    private String name = "";
    private ArrayList<StatusEffect> statusEffects = null; //the status effect affecting a combatant

    //Constructors------------------------------------------------------------------------------------------------------

    /**
     * Default constructor
     */
    Combatant() {
        this.initiative = 0;
        this.name = "NoName";
        this.statusEffects = new ArrayList<>();
    }

    /**
     *
     * @param initiative the initiative of the combatant
     * @param name the name of the combatant
     */
    public Combatant(int initiative, String name){
        this.initiative = initiative;
        this.name = name;
        this.statusEffects = new ArrayList<>();
    }

    /**
     * Constructor for status effects between battles
     * @param initiative the initiative of the combatant
     * @param statusEffects the status effects of the combatant
     * @param name the name of the combatant
     */
    public Combatant(int initiative, ArrayList<StatusEffect> statusEffects, String name) {
        this.initiative = initiative;
        this.statusEffects = statusEffects;
        this.name = name;
    }

    //Methods-----------------------------------------------------------------------------------------------------------


    @Override
    public String toString(){
        return String.format("Init:%03d Name: %-10.30s",getInitiative(), getName());
    }

    public abstract Pane getPane();

    /**
     * Adds a status effect to the combatant
     * @param statusEffect the status effect to be added
     */
    public void addStatusEffect(StatusEffect statusEffect){
        statusEffects.add(statusEffect);
    }

    /**
     * Removes a status effect from the combatant
     * @param statusEffect the status effect to remove from the combatant
     */
    public void removeStatusEffect(StatusEffect statusEffect){
        statusEffects.remove(statusEffect);
    }

    /**
     * Allow access to what status effects the combatant has
     * @return An array list of status effects
     */
    public ArrayList<StatusEffect> getStatusEffects() {return this.statusEffects;}

    /**
     * Compare method for comparing initiative
     * return > 0 - This combatant's initiative is greater
     * return 0 - The initiatives are equal
     * return < 0 - This combatant's initiative is less
     * @param o the combatant to compare initiative with
     */
    @Override
    public int compareTo(Combatant o) {
        return this.initiative - o.getInitiative();
    }

    /**
     * The getter for initiative
     * @return the initiative number
     */
    public int getInitiative() {
        return initiative;
    }

    /**
     * The setter for initiative
     * @param initiative the number to be set
     */
    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    /**
     * The getter for the name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * The setter for the name
     * @param name the name to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Generates an XML Element for the Combatant
     *
     * @param doc The document
     * @return
     */
    public abstract Element toXMLElement(Document doc);

    public abstract void generateFromXMLNode(Node node);
}
