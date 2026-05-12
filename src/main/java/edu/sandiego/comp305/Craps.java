package edu.sandiego.comp305;

import java.util.Random;
import java.util.Scanner;

public class Craps implements Game {

    private CrapsState currentState;
    private int point;
    private int betAmount;
    private final Random random = new Random();

    public Craps() {
        this.currentState = new ComingOutState();
        this.point = 0;
    }

    public CrapsState getCurrentState() {
        return currentState;
    }

    public void setState(CrapsState state) {
        this.currentState = state;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

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