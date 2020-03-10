package Initiative;

import java.util.ArrayList;
import java.util.Arrays;

public class InitiativeTracker {
    //Variables---------------------------------------------------------------------------------------------------------
    private ArrayList<Combatant> combatants = null; //list of combatants
    private int cursor = -1; //must be negitive one so that when the button is hit for the first time, it pulls up
    //the first combatant, not the second one

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
        int i = 0;
        while(i < combatants.size() && combatant.compareTo(combatants.get(i)) < 0){
            i++;
        }
        combatants.add(i,combatant);
    }

    /**
     * Returns if the list contains combatants
     * @return True if it contains combatants
     */
    public boolean hasCombatants(){
        if(combatants == null)
            return false;
        return !combatants.isEmpty();
    }

    /**
     * Returns the combatants
     * @return combatants
     */
    public ArrayList<Combatant> getCombatants(){
        return combatants;
    }

    /**
     * This method removes a combatant from the ArrayList of "Combatant"s
     * @param combatant the combatant to be removed
     * @return If the Combatant "combatant" was removed
     */
    public boolean removeCombatant(Combatant combatant){
        try {
            if (combatant == null) {
                new errorWindow().errorWindow("No Combatant Selected to be Removed");
            }
            return combatants.remove(combatant);
        }catch(NullPointerException e){
            return false;
        }
    }

    //Constructors------------------------------------------------------------------------------------------------------
    /**
     * Default Constructor
     */
    public InitiativeTracker(){
        this.combatants = new ArrayList<Combatant>();
    }

    /**
     * Constructor
     * @param combatants list of combatants
     */
    public InitiativeTracker(ArrayList<Combatant> combatants) {
        this.combatants = combatants;
    }
}
