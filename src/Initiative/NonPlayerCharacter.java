package Initiative;

import Persistance.StatusEffects;
import javafx.scene.layout.Pane;
import org.w3c.dom.*;

import java.util.ArrayList;

public class NonPlayerCharacter extends Combatant {
    //Variables---------------------------------------------------------------------------------------------------------
    private int hitPoints = 0; //the hit point total of a NonPlayerCharacter
    private int armorClass = 0; //the armor class of a NonPlayerCharacter

    //Methods-----------------------------------------------------------------------------------------------------------

    /**
     * Gets the pane to display the character's stats
     * @return custom display pane
     */
    @Override
    public Pane getPane(){
        return null;
    }

    /**
     * The getter for hitPoints
     * @return hitPoints
     */
    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * The getter for armorClass
     * @return armorClass
     */
    public int getArmorClass() {
        return armorClass;
    }

    /**
     * The setter for hitPoints
     * @param hitPoints the hitPoints to be set
     */
    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    /**
     * The setter for armorClass
     * @param armorClass the armorClass to be set
     */
    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }

    /**
     * This method increments the NonPlayerCharacter's hit points by an increment
     * @param increment how much the hit points are incremented by
     */
    public void incrementHitPoints(int increment){
        this.hitPoints+=increment;
    }

    public String toString(){
        String output = super.toString();
        output += String.format("HP:%03d AC:%03d",getHitPoints(), getArmorClass());
        return output;
    }

    /**
     * Fills in the NPC data from an XML node
     * @param node The node to retrieve data from
     */
    @Override
    public void generateFromXMLNode(Node node) {
        if(node.getNodeType() == Node.ELEMENT_NODE) {
            Element eNode = (Element) node;
            this.setInitiative(Integer.parseInt(eNode.getAttribute("initiative")));
            this.setName(eNode.getAttribute("name"));

            NodeList statusEffectNodes = eNode.getElementsByTagName("StatusEffect");

            StatusEffects allStatusEffects = new StatusEffects();

            for(int i = 0; i < statusEffectNodes.getLength(); i++) {
                if(statusEffectNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element effect = (Element) statusEffectNodes.item(i);
                    this.addStatusEffect(allStatusEffects.getEffectByName(effect.getAttribute("name")));
                } else {
                    System.out.println("YEET! Wasn't an Element at NPC 134");
                }
            }

            this.setHitPoints(Integer.parseInt(eNode.getElementsByTagName("HitPoints").item(0).getNodeValue()));
            this.setArmorClass(Integer.parseInt(eNode.getElementsByTagName("ArmorClass").item(0).getNodeValue()));
        }
    }

    /**
     * Converts the NPC to an XML Element using the given doc
     * @param doc The document to use when creating items
     * @return Element created with this NPC and the given doc
     */
    @Override
    public Element toXMLElement(Document doc) {

        //Set up basic header
        Element combatant = doc.createElement("Combatant");
        doc.getFirstChild().appendChild(combatant);
        combatant.setAttribute("type", "NonPlayerCharacter");
        combatant.setAttribute("initiative", Integer.toString(getInitiative()));
        combatant.setAttribute("name", getName());

        //Add all status effects
        for(StatusEffect statusEffect : getStatusEffects()) {
            Element xmlStatus = doc.createElement("StatusEffect");
            xmlStatus.setAttribute("type", "standard");
            xmlStatus.setAttribute("name", statusEffect.getName());
            combatant.appendChild(xmlStatus);
        }

        //Add health
        Element health = doc.createElement("HitPoints");
        health.appendChild(doc.createTextNode(Integer.toString(hitPoints)));
        combatant.appendChild(health);

        //Add Armor class
        Element armorClass = doc.createElement("ArmorClass");
        armorClass.appendChild(doc.createTextNode(Integer.toString(this.armorClass)));
        combatant.appendChild(armorClass);

        return combatant;
    }

    //Constructors------------------------------------------------------------------------------------------------------

    /**
     * Default Constructor
     */
    public NonPlayerCharacter() {
        super();
        this.hitPoints = 0;
        this.armorClass = 0;
    }

    public NonPlayerCharacter(int initiative, String name, int hitPoints, int armorClass){
        super(initiative, name);
        this.hitPoints = hitPoints;
        this.armorClass = armorClass;
    }

    /**
     * Constructor for persistent status effects
     * @param initiative the initiative of a NonPlayerCharacter
     * @param statusEffects the status effects affecting a NonPlayerCharacter
     * @param hitPoints the hit point total of a NonPlayerCharacter
     * @param armorClass the armor class of a NonPlayerCharacter
     * @param name the name of a NonPlayerCharacter
     */
    public NonPlayerCharacter(int initiative, ArrayList<StatusEffect> statusEffects, int hitPoints, int armorClass, String name) {
        super(initiative, statusEffects, name);
        this.hitPoints = hitPoints;
        this.armorClass = armorClass;
    }
}
