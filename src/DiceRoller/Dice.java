package DiceRoller;

public enum Dice {
    D3(3),
    D4(4),
    D6(6),
    D8(8),
    D10(10),
    D12(12),
    D20(20),
    D100(100);

    private int sides;
    public int getSides(){
        return this.sides;
    }

    Dice(int sides){
        this.sides = sides;
    }
}
