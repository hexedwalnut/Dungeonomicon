package TestCases;

import DiceRoller.DiceRoller;
import DiceRoller.RollFormatException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

/**
 * This class tests the DiceRoller class.
 *
 * Date Last Modified: 1/1/2020
 * @author Joseph Teahen
 */
class DiceRollerTest {

    //Basic Dice Rolling Test Cases No Sum------------------------------------------------------------------------------
    @Test
    private void testRollCustomNoSum() {
        DiceRoller roller = new DiceRoller(false);
        for (int i = 0; i < 20; i++) {
            int result = roller.rollCustom(100);
            Assert.assertTrue(result > 0 && result < 101);
        }
    }

    @Test
    private void testRollCustomWithMultipleDiceNoSum() {
        DiceRoller roller = new DiceRoller(false);
        for (int i = 0; i < 20; i++) {
            int result = roller.rollCustom(100, 2);
            Assert.assertTrue(result > 1 && result < 201);
        }
    }

    @Test
    private void testRollCustomWithMultipleDiceAndModifierNoSum() {
        DiceRoller roller = new DiceRoller(false);
        for (int i = 0; i < 20; i++) {
            int result = roller.rollCustom(100, 2, 1);
            Assert.assertTrue(result > 2 && result < 202);
        }
    }

    //Basic Dice Rolling Test Cases With Sum----------------------------------------------------------------------------
    @Test
    private void testRollCustomWithSum() {
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
    private void testRollCustomWithMultipleDiceWithSum() {
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
    private void testRollCustomWithMultipleDiceAndModifierWithSum() {
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
    private void rollCalcExceptionThrown() {
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
    private void rollCalcOneDiceInputNoSum() {
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
    private void rollCalcMultipleOfTheSameDiceInputNoSum() {
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
    private void rollCalcMultipleDiceOfTheSameDicePlusModifierInputNoSum() {
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
    private void rollCalcMultipleDiceDifferentDiceInputNoSum() {
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
    private void rollCalcMultipleDiceDifferentDicePlusModifierInputNoSum() {
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
    private void rollCalcOneDiceInputWithSum() {
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
    private void rollCalcMultipleOfTheSameDiceInputWithSum() {
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
    private void rollCalcMultipleDiceOfTheSameDicePlusModifierInputWithSum() {
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
    private void rollCalcMultipleDiceDifferentDiceInputWithSum() {
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
    private void rollCalcMultipleDiceDifferentDicePlusModifierInputWithSum() {
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
    private void clearTotal() {
        DiceRoller roller = new DiceRoller(true);
        for (int i = 0; i < 20; i++) {
            roller.rollCustom(4);
        }
        roller.clearTotal();
        Assert.assertTrue(roller.getSumRolls() == true);
    }
}