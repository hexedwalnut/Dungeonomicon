package Initiative;

import com.sun.javafx.binding.StringFormatter;
import javafx.scene.layout.Pane;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

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

    @Override
    public String toString(){
        String output = String.format("Name: %-10.30sInit:%03d", getName(),getInitiative());
        return output;
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

    @Override
    public Element toXMLElement(Document doc) {
        //Todo write this
        return null;
    }

    @Override
    public void generateFromXMLNode(Node node) {
        //Todo get name
        //Todo get Status effects
        //Todo get initiative
        //Todo figure out why stuff isn't done
    }
}
