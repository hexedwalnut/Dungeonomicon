package DiceRoller;

public class DiceRoller {

    public static int roll(Dice die){
        return (int)(Math.random()*die.getSides()) + 1;
    }

    public static int roll(Dice die, int numRolled){
        int total = 0;
        for(int i = 0; i < numRolled; i++){
            total += roll(die);
        }
        return total;
    }

    public static int roll(Dice die, int numRolled, int modifier){
        return roll(die,numRolled) + modifier;
    }

    public static int rollCustom(int sides){
        return 0;
    }

    public static int rollCustom(int sides, int numRolled){
        return 0;
    }

    public static int rollCustom(int sides, int numRolled, int modifier){
        return 0;
    }

    public static int rollCalc(String input){
        return 0;
    }
}
