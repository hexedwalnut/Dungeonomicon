package Initiative;

import Persistance.StatusEffects;
import javafx.scene.layout.Pane;
import org.w3c.dom.*;

import java.util.ArrayList;

@SuppressWarnings({"unused", "WeakerAccess"})

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
        return super.toString();
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

    /**
     * Generates an XML Element from the PlayerCharacter using the given document
     * @param doc The document used to create the XML Element
     * @return An elemental version of the player
     */
    @Override
    public Element toXMLElement(Document doc) {

        //Create combatant element using the document
        Element combatant = doc.createElement("Combatant");
        doc.getFirstChild().appendChild(combatant);

        //Add attributes to the element
        combatant.setAttribute("type", "PlayerCharacter");
        combatant.setAttribute("initiative", Integer.toString(this.getInitiative()));
        combatant.setAttribute("name", this.getName());

        //Add status effects to the element
        for (StatusEffect statusEffect : getStatusEffects()) {
            //Create the status effect element
            Element xmlStatus = doc.createElement("StatusEffect");
            //Set the type to standard. This will be used when we allow custom status effect types
            xmlStatus.setAttribute("type", "standard");
            //Set the name. As long as it's standard, this is all we need.
            xmlStatus.setAttribute("name", statusEffect.getName());
            //Add the effect to the main element
            combatant.appendChild(xmlStatus);
        }

        //Return the element
        return combatant;
    }

    /**
     * Reinitialize values using a node
     * @param node the node to retrieve values from
     */
    @Override
    public void generateFromXMLNode(Node node) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element eNode = (Element) node;
            this.setInitiative(Integer.parseInt(eNode.getAttribute("initiative")));
            this.setName(eNode.getAttribute("name"));

            NodeList statusEffectNodes = eNode.getElementsByTagName("StatusEffect");

            StatusEffects allStatusEffects = new StatusEffects();

            for (int i = 0; i < statusEffectNodes.getLength(); i++) {
                if (statusEffectNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element effect = (Element) statusEffectNodes.item(i);
                    this.addStatusEffect(allStatusEffects.getEffectByName(effect.getAttribute("name")));
                } else {
                    throw new RuntimeException("YEET! Wasn't an element at PC!");
                }
            }
        }
    }
}
