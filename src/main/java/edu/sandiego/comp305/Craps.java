package edu.sandiego.comp305;

public class Craps implements Game {

    private CrapsState currentState;
    private int pointTarget;
    private int currentBetAmount;

    public Craps() {
        this.currentState = new ComingOutState();
        this.pointTarget = 0;
    }

    public void setState(CrapsState state)
    {}

    public int getPoint()
    { return 0; }

    public void setPoint(int pointTarget)
    {}

    public int rollDie()
    { return 0; }

    public int roll()
    { return 0; }

    @Override
    public int handleBet(int amount)
    { return 0; }

    @Override
    public void playGame()
    {}

    @Override
    public void updateBalance(int amount)
    {}
}