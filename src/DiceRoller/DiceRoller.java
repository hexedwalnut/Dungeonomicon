package DiceRoller;

import java.util.ArrayList;
import java.util.Scanner;

public class DiceRoller {

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
        if(numRolled > 1){
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
        Scanner reader = new Scanner(input);
        reader.useDelimiter(" + ");
        String nextInput;
        ArrayList<Integer> resultStorage = new ArrayList<>();

        while(reader.hasNext()) {
            try {
                nextInput = reader.next().trim().toLowerCase();
                if (nextInput.contains("d")) {
                    int numRolled = Integer.parseInt(nextInput.substring(0, nextInput.indexOf('d')));
                    int sides = Integer.parseInt(nextInput.substring(nextInput.indexOf('d') + 1));
                    resultStorage.add(rollCustom(sides, numRolled));
                }
                else {
                    int mod = Integer.parseInt(nextInput);
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
}
