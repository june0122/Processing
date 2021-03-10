package sezikim.protocol;

public class Dice {
    private int firstDice;
    private int secondDice;

    public Dice(int firstDice, int secondDice) {
        this.firstDice = firstDice;
        this.secondDice = secondDice;
    }

    public int getFirstDice() {
        return firstDice;
    }

    public int getSecondDice() {
        return secondDice;
    }
}
