package Initiative;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

public abstract class Combatant {
    //Variables---------------------------------------------------------------------------------------------------------
    private int initiative = 0; //the initiative number for a combatant
    private ArrayList<StatusEffect> statusEffects = null; //the status effect affecting a combatant

    //Constructors------------------------------------------------------------------------------------------------------

    /**
     * Default constructor
     */
    Combatant() {
        this.initiative = 0;
    }

    /**
     * Constructor
     * @param initiative the initiative of the combatant
     * @param statusEffects the status effects of the combatant
     */
    public Combatant(int initiative, ArrayList<StatusEffect> statusEffects) {
        this.initiative = initiative;
        this.statusEffects = statusEffects;
    }

    //Methods-----------------------------------------------------------------------------------------------------------

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
     * @param statusEffect
     */
    public void removeStatusEffect(StatusEffect statusEffect){
        statusEffects.remove(statusEffect);
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
}
