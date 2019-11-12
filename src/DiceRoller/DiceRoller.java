package DiceRoller;

import java.util.ArrayList;
import java.util.Scanner;

public class DiceRoller {
    //Class Variables---------------------------------------------------------------------------------------------------
    private boolean sumRolls;

    //Constructors------------------------------------------------------------------------------------------------------
    public DiceRoller(){
        this.sumRolls = true;
    }

    public DiceRoller(boolean sumRolls){
        this.sumRolls = sumRolls;
    }

    //Methods-----------------------------------------------------------------------------------------------------------
    public int roll(Dice die){
        return (int)(Math.random()*die.getSides()) + 1;
    }

    public int roll(Dice die, int numRolled){
        int total = 0;
        if(numRolled < 1){
            for(int i = 0; i > numRolled; i--){
                total -= roll(die);
            }
            return total;
        }
        for(int i = 0; i < numRolled; i++){
            total += roll(die);
        }
        return total;
    }

    public int roll(Dice die, int numRolled, int modifier){
        return roll(die,numRolled) + modifier;
    }

    public int rollCustom(int sides){
        return (int)(Math.random()*sides) + 1;
    }

    public int rollCustom(int sides, int numRolled){
        int total = 0;
        if(numRolled < 1){
            for(int i = 0; i > numRolled; i--){
                total -= rollCustom(sides);
            }
            return total;
        }
        for(int i = 0; i < numRolled; i++){
            total += rollCustom(sides);
        }
        return total;
    }

    public int rollCustom(int sides, int numRolled, int modifier){
        return rollCustom(sides, numRolled) + modifier;
    }

    public int rollCalc(String input) throws RollFormatException{
        char[] rawInput = input.toCharArray();
        String[] splitInput = input.split("\\+");
        ArrayList<Integer> resultStorage = new ArrayList<>();

        for(String s : splitInput) {
            s = s.trim();
            try {
                if (s.contains("d")) {
                    int numRolled = Integer.parseInt(s.substring(0, s.indexOf('d')));
                    int sides = Integer.parseInt(s.substring(s.indexOf('d') + 1));
                    resultStorage.add(rollCustom(sides, numRolled));
                }
                else {
                    int mod = Integer.parseInt(s);
                    resultStorage.add(mod);
                }
            }
            catch(Exception e){
                throw new RollFormatException("Invalid Custom Roll Format");
            }
        }
        int total = 0;
        for(Integer i : resultStorage){
            total += i;
        }
        return total;
    }

    public void setSumRolls(boolean sumRolls){
        this.sumRolls = sumRolls;
    }

    public boolean getSumRolls(){
        return sumRolls;
    }
}
