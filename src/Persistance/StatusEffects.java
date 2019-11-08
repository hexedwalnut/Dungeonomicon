package Persistance;

import Initiative.StatusEffect;
import com.sun.org.apache.xpath.internal.objects.XString;
import com.sun.org.apache.xpath.internal.objects.XStringForChars;

import java.util.ArrayList;

/**
 * StatusEffects parses all the possible status effects from xml and stores their descriptions
 *
 * Date Last Modified: 11/7/2019
 * @author DaDarkWizard
 */
public class StatusEffects {
    private static String fileLocation = "/Dungeonomicon/StatusEffects.xml";
    private static ArrayList<StatusEffect> effects = new ArrayList<>();

    private StatusEffects() {

    }

    public void Initialize() {
        if(!SettingsStorage.isInitialized()) {
            SettingsStorage.Initialize();
        }
        fileLocation = System.getenv("APPDATA") + fileLocation;
    }
}
