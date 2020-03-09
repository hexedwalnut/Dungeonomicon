package Initiative;

import java.util.ArrayList;

public class InitiativeTracker {
    //Variables---------------------------------------------------------------------------------------------------------
    private ArrayList<Combatant> combatants = null; //list of combatants

    //Methods-----------------------------------------------------------------------------------------------------------

    /**
     * Moves to the next turn in initiative
     */
    public void nextTurn(){
        //TODO
    }

    /**
     * Removes a designated combatant from the List of Combatants
     * @param combatant
     */
    public void removeCombatant(Combatant combatant){
        try {
            combatants.remove(combatant);
        } catch(NullPointerException e){
            new errorWindow().errorWindow("No Combatant Was Selected to be Removed");
        }
    }

    public boolean hasCombatants(){
        return !combatants.isEmpty();
    }

    /**
     * Returns the combatants
     * @return combatants
     */
    public ArrayList<Combatant> getCombatants(){
        return combatants;
    }
    //Constructors------------------------------------------------------------------------------------------------------

    /**
     * Default Constructor
     */
    public InitiativeTracker(){
        this.combatants = null;
    }

    /**
     * Constructor
     * @param combatants list of combatants
     */
    public InitiativeTracker(ArrayList<Combatant> combatants) {
        this.combatants = combatants;
    }
}
