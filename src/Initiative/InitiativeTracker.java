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
