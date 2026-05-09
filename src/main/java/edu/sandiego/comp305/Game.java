package edu.sandiego.comp305;

public interface Game {

    int handleBet(int amount);

    int playGame();

    void updateBalance(int amount);
}
