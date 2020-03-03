package Initiative;

import java.util.ArrayList;
import java.util.Arrays;

public class InitiativeTracker {
    //Variables---------------------------------------------------------------------------------------------------------
    private ArrayList<Combatant> combatants = null; //list of combatants
    private int cursor = 0;

    //Methods-----------------------------------------------------------------------------------------------------------
    /**
     * Moves to the next turn in initiative
     */
    public Combatant nextTurn(){
        cursor++;
        if(cursor > combatants.size()-1){
            cursor = 0;
        }
        return combatants.get(cursor);
    }

    /**
     * This method adds a combatant to the ArrayList of "Combatant"s
     * @param combatant the Combatant to be added
     */
    public void addCombatant(Combatant combatant){
        combatants.add(combatant);
        Arrays.sort(combatants.toArray());
    }

    /**
     * This method removes a combatant from the ArrayList of "Combatant"s
     * @param combatant the combatant to be removed
     * @return If the Combatant "combatant" was removed
     */
    public boolean removeCombatant(Combatant combatant){
        return combatants.remove(combatant);
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
