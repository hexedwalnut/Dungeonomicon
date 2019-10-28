package Initiative;

import java.util.ArrayList;

public class PlayerCharacter extends Combatant {
    //Variables---------------------------------------------------------------------------------------------------------
    private String name = ""; //the name of the character

    //Methods-----------------------------------------------------------------------------------------------------------

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
    //Constructors------------------------------------------------------------------------------------------------------

    /**
     * Default Constructor
     */
    public PlayerCharacter(){
        super();
        this.name = "No Name";
    }

    /**
     * Name Constructor
     * @param name the name of the character
     */
    public PlayerCharacter(String name, int initiative, ArrayList<StatusEffect> statusEffects) {
        super(initiative, statusEffects);
        this.name = name;
    }
}
