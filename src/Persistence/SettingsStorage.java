package Persistence;

import Initiative.Combatant;
import Initiative.NonPlayerCharacter;
import Initiative.PlayerCharacter;
import Initiative.StatusEffect;

import java.util.ArrayList;

public class SettingsStorage {
    private static final String fileLocation = "";
    private static boolean initialized = false;
    private static ArrayList<Combatant> combatants;
    private static ArrayList<StatusEffect> statusEffects;
    private static FileParser parser = new FileParser(fileLocation);
    private static boolean sumRollsEnabled = false;

    public static void Initialize() {
        parser.ParseFile();
    }

    public static void Load() {
        parser.ParseFile();
    }

    public static void Save() {
        //parser.SaveToFile();
    }

    public static ArrayList<Combatant> getCombatants() {
        ArrayList<Combatant> output = new ArrayList<>(combatants);
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

    public static boolean isInitialized() {
        return initialized;
    }

    public static boolean isSumRollsEnabled() {return sumRollsEnabled; }

    public static void setSumRollsEnabled(boolean enabled) {sumRollsEnabled = enabled;}

    private SettingsStorage(){
    }
}
