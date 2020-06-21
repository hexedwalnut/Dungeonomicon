package TestCases;

import Initiative.Combatant;
import Initiative.NonPlayerCharacter;
import Initiative.StatusEffect;
import Persistance.Combatants;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CombatantsTest {

    @Test
    public void GeneratedXMLTest() {
        Combatants combatants = new Combatants("../../XMLFiles/Combatants.xml");
        NonPlayerCharacter nonPlayerCharacter = new NonPlayerCharacter
        (10, new ArrayList<StatusEffect>(), 8, 12, "Jimmy");
        combatants.AddCombatant(nonPlayerCharacter);
        combatants.Save();
    }
}
