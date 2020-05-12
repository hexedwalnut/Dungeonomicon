package Persistance;

import Initiative.StatusEffect;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

/**
 * StatusEffects parses all the possible status effects from xml and stores their descriptions
 *
 * Date Last Modified: 11/8/2019
 * @author Daniel Masker
 */

@SuppressWarnings("unused")

public class StatusEffects {
    private static String fileLocation = "/Dungeonomicon/StatusEffects.xml";
    private static ArrayList<StatusEffect> effects = new ArrayList<>();
    private static boolean initialized = false;
    private static FileParser parser;
    private static Document doc;

    public StatusEffects() {
        if(!initialized) {
                Initialize();
        }
    }

    /**
     * Loads all Status Effects from XML into Memory
     */
    private void Initialize() {

        //Use FileParser to get XML Document
        fileLocation = System.getenv("APPDATA") + fileLocation;
        parser = new FileParser(fileLocation, false);
        doc = parser.ParseFile();

        //Get all effects as a list, then add them to the effects array
        NodeList xmlEffects = doc.getDocumentElement().getElementsByTagName("statusEffect");
        for(int i = 0; i < xmlEffects.getLength(); i++) {
            String name = xmlEffects.item(i).getAttributes().getNamedItem("name").getNodeValue();
            String desc = xmlEffects.item(i).getTextContent();
            StatusEffect effect = new StatusEffect(name, desc);
            effects.add(effect);
        }
        initialized = true;
    }

    /**
     * Overwrites all saved status effects with what is stored in memory.
     * //ToDo: Make this actually save what is in memory (the doc never gets updated);
     */
    public void Save() {
        parser.SaveToFile(doc);
    }

    /**
     * Returns all status effects
     *
     * @return an ArrayList of all the effects
     */
    public ArrayList<StatusEffect> getAllEffects() {
        return new ArrayList<>(effects);
    }

    /**
     * Gets the names of all the conditions
     *
     * @return All names in an ArrayList
     */
    public ArrayList<String> getAllEffectNames() {
        ArrayList<String> output = new ArrayList<>();
        for(StatusEffect effect : effects) {
            output.add(effect.getName());
        }
        return output;
    }

    /**
     * Gets the status effect with the matching name
     *
     * @param name Name of the status effect
     *
     * @return the matching StatusEffect, or null if not found
     */
    public StatusEffect getEffectByName(String name) {
        for(StatusEffect effect : effects) {
            if(effect.getName().equals(name)) {
                return effect;
            }
        }
        return null;
    }

    /**
     * Converts all status effects to a string
     *
     * @return all status effects as a string
     */
    public String toString() {
        StringBuilder output = new StringBuilder("Status Effects:{");
        for(int i = 0; i < effects.size(); i++) {
            output.append("[").append(effects.get(i).toString()).append("]");
            if(i < effects.size() - 1) {
                output.append(",");
            }
        }
        output.append("}");
        return output.toString();
    }
}
