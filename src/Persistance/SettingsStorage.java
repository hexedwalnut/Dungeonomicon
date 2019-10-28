package Persistance;

import Initiative.Combatant;
import Initiative.NonPlayerCharacter;
import Initiative.PlayerCharacter;
import Initiative.StatusEffect;

import java.util.ArrayList;
import java.util.Set;

public class SettingsStorage {
    private static final String fileLocation = "";
    private static ArrayList<Combatant> combatants;
    private static ArrayList<StatusEffect> statusEffects;
    private static FileParser parser = new FileParser(fileLocation);

    public static void Load() {
        parser.ParseFile();
    }

    public static void Save() {
        parser.SaveToFile();
    }

    public static ArrayList<Combatant> getCombatants() {
        ArrayList<Combatant> output = new ArrayList<>();
        output.addAll(combatants);
        return output;
    }

    public static ArrayList<PlayerCharacter> getPlayerCharacters() {
        ArrayList<PlayerCharacter> output = new ArrayList<>();
        for(Combatant i : combatants) {
            if(i instanceof PlayerCharacter) {
                output.add((PlayerCharacter)i);
            }
        }
        return output;
    }

    public static ArrayList<NonPlayerCharacter> getNonPlayerCharacters() {
        ArrayList<NonPlayerCharacter> output  = new ArrayList<>();
        for(Combatant i : combatants) {
            if(i instanceof  NonPlayerCharacter) {
                output.add((NonPlayerCharacter)i);
            }
        }
        return output;
    }

    private SettingsStorage(){
    }
}
