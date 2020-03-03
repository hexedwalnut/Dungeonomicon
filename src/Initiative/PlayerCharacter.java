package Initiative;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class PlayerCharacter extends Combatant {
    //Variables---------------------------------------------------------------------------------------------------------
    //Methods-----------------------------------------------------------------------------------------------------------
    //This section mostly blank because persistence isn't implemented yet

    /**
     * Gets the pane to display the character's stats
     * @return custom display pane
     */
    @Override
    public Pane getPane(){
        return null;
    }
    //Constructors------------------------------------------------------------------------------------------------------

    /**
     * Default Constructor
     */
    public PlayerCharacter(){
        super();
    }

    public PlayerCharacter(int initiative, String name){
        super(initiative, name);
    }

    /**
     * Constructor for persistent status effects
     * @param name the name of the character
     */
    public PlayerCharacter(int initiative, ArrayList<StatusEffect> statusEffects, String name) {
        super(initiative, statusEffects, name);
    }
}
