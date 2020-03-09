package TestCases;

import DiceRoller.DiceRoller;
import DiceRoller.RollFormatException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

/**
 * This class tests the DiceRoller class.
 *
 * Date Last Modified: 1/7/2020
 * @author Joseph Teahen
 */
class DiceRollerTest {

    //Basic Dice Rolling Test Cases No Sum------------------------------------------------------------------------------
    @Test
    public void testRollCustomNoSum() {
        DiceRoller roller = new DiceRoller(false);
        for (int i = 0; i < 20; i++) {
            int result = roller.rollCustom(100);
            Assert.assertTrue(result > 0 && result < 101);
        }
    }

    @Test
    public void testRollCustomWithMultipleDiceNoSum() {
        DiceRoller roller = new DiceRoller(false);
        for (int i = 0; i < 20; i++) {
            int result = roller.rollCustom(100, 2);
            Assert.assertTrue(result > 1 && result < 201);
        }
    }

    @Test
    public void testRollCustomWithMultipleDiceAndModifierNoSum() {
        DiceRoller roller = new DiceRoller(false);
        for (int i = 0; i < 20; i++) {
            int result = roller.rollCustom(100, 2, 1);
            Assert.assertTrue(result > 2 && result < 202);
        }
    }

    //Basic Dice Rolling Test Cases With Sum----------------------------------------------------------------------------
    @Test
    public void testRollCustomWithSum() {
        DiceRoller roller = new DiceRoller(true);
        for (int j = 0; j < 20; j++) {
            for (int i = 0; i < 20; i++) {
                roller.rollCustom(100);
            }
            int total = roller.getRollTotal();
            System.out.println(total);
            Assert.assertTrue(total > 19 && total < 2001);
            roller.clearTotal();
        }
    }

    @Test
    public void testRollCustomWithMultipleDiceWithSum() {
        DiceRoller roller = new DiceRoller(true);
        for (int j = 0; j < 20; j++) {
            for (int i = 0; i < 20; i++) {
                roller.rollCustom(100, 2);
            }
            int total = roller.getRollTotal();
            Assert.assertTrue(total > 39 && total < 4001);
            roller.clearTotal();
        }
    }

    @Test
    public void testRollCustomWithMultipleDiceAndModifierWithSum() {
        DiceRoller roller = new DiceRoller(true);
        for (int j = 0; j < 20; j++) {
            for (int i = 0; i < 20; i++) {
                roller.rollCustom(100, 2, 1);
            }
            int total = roller.getRollTotal();
            Assert.assertTrue(total > 59 && total < 4021);
            roller.clearTotal();
        }
    }

    //RollCalc Test Cases General Test Nonsense-------------------------------------------------------------------------
    @Test
    public void rollCalcExceptionThrown() {
        DiceRoller roller = new DiceRoller(false);
        boolean exceptionThrown = false;
        try {
            roller.rollCalc("This should throw an exception");
        } catch (RollFormatException e) {
            exceptionThrown = true;
        }
        Assert.assertTrue(exceptionThrown);
    }

    //RollCalc Test Cases No Sum----------------------------------------------------------------------------------------
    @Test
    public void rollCalcOneDiceInputNoSum() {
        DiceRoller roller = new DiceRoller(false);
        for (int i = 0; i < 20; i++) {
            try {
                int result = roller.rollCalc("1d4");
                Assert.assertTrue(result > 0 && result < 5);
            } catch (RollFormatException e) {
                Assert.fail("The roller threw a RollFormatException");
            }
        }
    }

    @Test
    public void rollCalcMultipleOfTheSameDiceInputNoSum() {
        DiceRoller roller = new DiceRoller(false);
        for (int i = 0; i < 20; i++) {
            try {
                int result = roller.rollCalc("8d4");
                Assert.assertTrue(result > 7 && result < 33);
            } catch (RollFormatException e) {
                Assert.fail("The roller threw a RollFormatException");
            }
        }
    }

    @Test
    public void rollCalcMultipleDiceOfTheSameDicePlusModifierInputNoSum() {
        DiceRoller roller = new DiceRoller(false);
        for (int i = 0; i < 20; i++) {
            try {
                int result = roller.rollCalc("8d4+1");
                Assert.assertTrue(result > 8 && result < 34);
            } catch (RollFormatException e) {
                Assert.fail("The roller threw a RollFormatException");
            }
        }
    }

    @Test
    public void rollCalcMultipleDiceDifferentDiceInputNoSum() {
        DiceRoller roller = new DiceRoller(false);
        for (int i = 0; i < 20; i++) {
            try {
                int result = roller.rollCalc("8d4+12d12");
                Assert.assertTrue(result > 19 && result < 177);
            } catch (RollFormatException e) {
                Assert.fail("The roller threw a RollFormatException");
            }
        }
    }

    @Test
    public void rollCalcMultipleDiceDifferentDicePlusModifierInputNoSum() {
        DiceRoller roller = new DiceRoller(false);
        for (int i = 0; i < 20; i++) {
            try {
                int result = roller.rollCalc("8d4+1+12d12+1");
                Assert.assertTrue(result > 21 && result < 179);
            } catch (RollFormatException e) {
                Assert.fail("The roller threw a RollFormatException");
            }
        }
    }

    //RollCalc Test Cases With Sum--------------------------------------------------------------------------------------
    @Test
    public void rollCalcOneDiceInputWithSum() {
        DiceRoller roller = new DiceRoller(true);
        for (int j = 0; j < 20; j++) {
            for (int i = 0; i < 20; i++) {
                try {
                    roller.rollCalc("1d4");
                } catch (RollFormatException e) {
                    Assert.fail("The roller threw a RollFormatException");
                }
            }
            int total = roller.getRollTotal();
            Assert.assertTrue(total > 19 && total < 81);
            roller.clearTotal();
        }
    }

    @Test
    public void rollCalcMultipleOfTheSameDiceInputWithSum() {
        DiceRoller roller = new DiceRoller(true);
        for (int j = 0; j < 20; j++) {
            for (int i = 0; i < 20; i++) {
                try {
                    roller.rollCalc("8d4");
                } catch (RollFormatException e) {
                    Assert.fail("The roller threw a RollFormatException");
                }
            }
            int total = roller.getRollTotal();
            Assert.assertTrue(total > 159 && total < 641);
            roller.clearTotal();
        }
    }

    @Test
    public void rollCalcMultipleDiceOfTheSameDicePlusModifierInputWithSum() {
        DiceRoller roller = new DiceRoller(true);
        for (int j = 0; j < 20; j++) {
            for (int i = 0; i < 20; i++) {
                try {
                    roller.rollCalc("8d4+1");
                } catch (RollFormatException e) {
                    Assert.fail("The roller threw a RollFormatException");
                }
            }
            int total = roller.getRollTotal();
            Assert.assertTrue(total > 179 && total < 661);
            roller.clearTotal();
        }
    }

    @Test
    public void rollCalcMultipleDiceDifferentDiceInputWithSum() {
        DiceRoller roller = new DiceRoller(true);
        for (int j = 0; j < 20; j++) {
            for (int i = 0; i < 20; i++) {
                try {
                    roller.rollCalc("8d4+12d12");
                } catch (RollFormatException e) {
                    Assert.fail("The roller threw a RollFormatException");
                }
            }
            int total = roller.getRollTotal();
            Assert.assertTrue(total > 399 && total < 3521);
            roller.clearTotal();
        }
    }

    @Test
    public void rollCalcMultipleDiceDifferentDicePlusModifierInputWithSum() {
        DiceRoller roller = new DiceRoller(true);
        for (int j = 0; j < 20; j++) {
            for (int i = 0; i < 20; i++) {
                try {
                    roller.rollCalc("8d4+1+12d12+1");
                } catch (RollFormatException e) {
                    Assert.fail("The roller threw a RollFormatException");
                }
            }
            int total = roller.getRollTotal();
            Assert.assertTrue(total > 439 && total < 3561);
            roller.clearTotal();
        }
    }

    //Clear Total Test--------------------------------------------------------------------------------------------------
    @Test
    public void clearTotal() {
        DiceRoller roller = new DiceRoller(true);
        for (int i = 0; i < 20; i++) {
            roller.rollCustom(4);
        }
        roller.clearTotal();
        Assert.assertTrue(roller.getSumRolls());
    }

    //Modifier Tests----------------------------------------------------------------------------------------------------
    @Test
    public void oneDiceModifierTestBaseFunction() {
        DiceRoller roller = new DiceRoller(false);
        for (int i = 0; i < 10000000; i++) {
            int result = roller.rollCustom(4, 1, 100);
            Assert.assertTrue(result > 100);
        }
    }

    @Test
    public void oneDiceModifierTestStringFunction() throws RollFormatException {
        DiceRoller roller = new DiceRoller(false);
        for (int i = 0; i < 10000000; i++) {
            int result = roller.rollCalc("4d12+100");
            Assert.assertTrue(result > 100);
        }
    }

    @Test
    public void multipleDiceModifierTestBaseFunction() {
        DiceRoller roller = new DiceRoller(false);
        for (int i = 0; i < 10000000; i++) {
            int result = roller.rollCustom(12, 4, 29);
            Assert.assertTrue(result > 29);
        }
    }
}