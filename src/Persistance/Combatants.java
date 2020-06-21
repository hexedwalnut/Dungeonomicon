package Persistance;

import Initiative.Combatant;
import Initiative.NonPlayerCharacter;
import Initiative.PlayerCharacter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;


public class Combatants {
    private ArrayList<Combatant> combatants = new ArrayList<>();
    private FileParser parser;
    private Document doc;

    private String fileLocation;

    /**
     * Create a Combatants object with save file at the given location.
     * @param fileLocation Location to save/read combatants.
     */
    public Combatants(String fileLocation) {
        this.fileLocation = fileLocation;
        parser = new FileParser(fileLocation);
    }

    /**
     * Adds a combatant to the list to be saved.
     * @param combatant Combatant to be added
     */
    public void AddCombatant(Combatant combatant) {
        combatants.add(combatant);
    }

    /**
     * Save the combatants to the selected file location;
     */
    public void Save() {
        try {
            //Create the document
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            doc = builder.newDocument();

            //Create the root node
            Element rootElement = doc.createElement("Combatants");
            doc.appendChild(rootElement);

            //Add each combatant to the root
            for (Combatant combatant : combatants) {
                if (combatant instanceof NonPlayerCharacter) {
                    combatant.toXMLElement(doc);
                } else if (combatant instanceof PlayerCharacter) {
                    combatant.toXMLElement(doc);
                } else {
                    throw new NotImplementedException();
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        parser.SaveToFile(doc);
    }

    /**
     * Loads combatants from the selected file.
     */
    public void Load() {
        parser = new FileParser(fileLocation, false);
        doc = parser.ParseFile();

        NodeList xmlCombatants = doc.getElementsByTagName("Combatant");
        System.out.println(xmlCombatants.item(0).toString());
        for(int i = 0; i < xmlCombatants.getLength(); i++) {
            if(xmlCombatants.item(i).toString().equals("[Combatant: null]")) {
                continue;
            }
            Node xmlCombatant = xmlCombatants.item(i);
            if(xmlCombatant.getAttributes().getNamedItem("type").getNodeValue().equals("PlayerCharacter")) {
                PlayerCharacter playerCharacter = new PlayerCharacter();
                playerCharacter.generateFromXMLNode(xmlCombatant);
                combatants.add(playerCharacter);
            } else {
                NonPlayerCharacter nonPlayerCharacter = new NonPlayerCharacter();
                nonPlayerCharacter.generateFromXMLNode(xmlCombatant);
                combatants.add(nonPlayerCharacter);
            }
        }
    }

    /**
     * Change the location where the file is read and saved from.
     *
     * @param fileLocation The location of the file as a string.
     */
    public void SetFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
        parser = new FileParser(fileLocation);
    }
}
