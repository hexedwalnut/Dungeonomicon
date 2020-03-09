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
    //Constructors------------------------------------------------------------------------------------------------------

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
