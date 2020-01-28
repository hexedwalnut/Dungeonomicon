package TestCases;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import Persistance.FileParser;
import Persistance.SettingsStorage;
import Persistance.StatusEffects;


class PersistanceTest {

    @Test
    public void testInit() {
        StatusEffects statusEffects = new StatusEffects();
        System.out.println(statusEffects.toString());
        statusEffects.Save();
        Assert.assertEquals(1,1);
    }
}
