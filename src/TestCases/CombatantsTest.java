package TestCases;

import Initiative.Combatant;
import Initiative.NonPlayerCharacter;
import Initiative.PlayerCharacter;
import Initiative.StatusEffect;
import Persistence.Combatants;
import Persistence.StatusEffects;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CombatantsTest {

    @Test
    public void SaveCombatantsTest() {
        Combatants combatants = new Combatants("./XMLFiles/Combatants.xml");
        NonPlayerCharacter nonPlayerCharacter = new NonPlayerCharacter
        (10, new ArrayList<StatusEffect>(), 8, 12, "Jimmy");
        nonPlayerCharacter.addStatusEffect(new StatusEffects().getAllEffects().get(0));
        combatants.AddCombatant(nonPlayerCharacter);
        PlayerCharacter playerCharacter = new PlayerCharacter(17, "George");
        playerCharacter.addStatusEffect(new StatusEffects().getAllEffects().get(1));
        combatants.AddCombatant(playerCharacter);
        combatants.Save();
    }

    @Test
    public void LoadCombatantsTest() {
        Combatants combatants = new Combatants("./XMLFiles/Combatants.xml");
        combatants.Load();
        for (Combatant combatant : combatants.getCombatants()) {
            System.out.println(combatant.toString());
        }
    }
}
