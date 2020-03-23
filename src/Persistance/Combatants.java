package Persistance;

import Initiative.Combatant;
import Initiative.NonPlayerCharacter;
import Initiative.PlayerCharacter;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class Combatants {
    private static String fileLocation = "/Dungeonomicon/Combatants.xml";
    private static ArrayList<Combatant> combatants = new ArrayList<>();
    private static boolean initialized = false;
    private static FileParser parser;
    private static Document doc;

    public Combatants() {
        if(!initialized) {
            Initialize();
        }
    }

    private void Initialize() {
        fileLocation = System.getenv("APPDATA") + fileLocation;
        parser = new FileParser(fileLocation, false);
        doc = parser.ParseFile();

        NodeList xmlCombatants = doc.getElementsByTagName("Combatant");
        for(int i = 0; i < xmlCombatants.getLength(); i++) {
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

    private void Save() {
        parser.SaveToFile(doc);
    }
}
