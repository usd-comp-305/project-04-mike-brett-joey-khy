package edu.sandiego.comp305;

public interface Game {

    public abstract int handleBet(final int amount);

    public abstract void playGame();

    public abstract void updateBalance(final int amountWonOrLost);
}
