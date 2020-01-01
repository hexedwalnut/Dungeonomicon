package DiceRoller;

import java.util.ArrayList;
import java.util.Scanner;

public class DiceRoller {
    //Class Variables---------------------------------------------------------------------------------------------------
    private boolean sumRolls;
    private int rollTotal = 0;

    //Constructors------------------------------------------------------------------------------------------------------
    public DiceRoller(){
        this.sumRolls = true;
    }

    public DiceRoller(boolean sumRolls){
        this.sumRolls = sumRolls;
    }

    //Methods-----------------------------------------------------------------------------------------------------------
    public int rollCustom(int sides){
        if(sumRolls){
            rollTotal += (int)(Math.random()*sides) + 1;
            return rollTotal;
        }
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
        if(sumRolls){
            return rollTotal;
        }
        return total;
    }

    public int rollCustom(int sides, int numRolled, int modifier){
        if(sumRolls){
            rollCustom(sides, numRolled);
            rollTotal += modifier;
            return rollTotal;
        }
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
        if(sumRolls){
            return rollTotal;
        }
        return total;
    }

    public void setSumRolls(boolean sumRolls){
        this.sumRolls = sumRolls;
    }

    public boolean getSumRolls(){
        return sumRolls;
    }

    public int getRollTotal() {
        return rollTotal;
    }

    public void clearTotal(){
        rollTotal = 0;
    }
}
