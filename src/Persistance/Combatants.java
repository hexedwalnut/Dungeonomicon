package Persistance;

import Initiative.Combatant;
import Initiative.NonPlayerCharacter;
import Initiative.PlayerCharacter;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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

    public void AddCombatant(Combatant combatant) {
        combatants.add(combatant);
    }

    public void Save() {
        for(int i = 0; doc.getDocumentElement().hasChildNodes(); ) {
            doc.getDocumentElement().removeChild(doc.getDocumentElement().getFirstChild());
        }
        for(Combatant combatant : combatants) {
            if (combatant instanceof NonPlayerCharacter) {
                combatant.toXMLElement(doc);
            } else if (combatant instanceof PlayerCharacter) {
                throw new NotImplementedException();
            } else {
                throw new NotImplementedException();
            }
        }
        parser.SaveToFile(doc);
    }
}
