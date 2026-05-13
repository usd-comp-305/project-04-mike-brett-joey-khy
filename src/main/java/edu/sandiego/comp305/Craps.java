package edu.sandiego.comp305;

import java.util.Random;
import java.util.Scanner;

public class Craps implements Game {

    private static final int DIE_FACE_COUNT = 6;

    private static final int DIE_MINIMUM_VALUE = 1;

    private CrapsState currentState;

    private int point;

    private int betAmount;

    private Scanner scanner;

    private Random random;

    public Craps(final Scanner scanner,final Random random) {
        this.scanner = scanner;
        this.random = random;
        this.currentState = new ComingOutState();
        this.point = 0;
    }

    public CrapsState getCurrentState() {

        return currentState;
    }

    public void setState(final CrapsState state) {

        this.currentState = state;
    }

    public int getPoint() {

        return point;
    }

    public void setPoint(final int point) {

        this.point = point;
    }

    public int rollDie() {
        return random.nextInt(DIE_FACE_COUNT)
                + DIE_MINIMUM_VALUE;
    }

    public int roll() {

        return rollDie() + rollDie();
    }

    @Override
    public int handleBet(final int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Bet amount " +
                    "must be at least $1");
        }
        this.betAmount = amount;

        while (true) {
            final int roll = roll();
            System.out.println("You rolled: " + roll);

            if (point != 0 && currentState instanceof PointPhaseState) {
                System.out.println("Point is: " + point);
            }

            final String outcome = currentState.handleRoll(roll, this);

            switch (outcome) {
                case "WIN":
                    System.out.println("You win!");
                    return betAmount;
                case "LOSE":
                    System.out.println("You lose.");
                    return -betAmount;
                case "POINT_SET":
                    System.out.println("Point set: " +
                            point + ". Keep rolling!");
                    break;
                case "CONTINUE":
                    System.out.println("No result. Roll again.");
                    break;
            }
        }
    }

    @Override
    public void playGame() {
        System.out.println("\nWelcome to Craps!");

        boolean keepPlaying = true;
        while (keepPlaying) {
            System.out.println("Your balance: $" + Casino.balance);
            System.out.print("Enter your bet amount (or 0 to quit): $");
            final int bet = scanner.nextInt();

            if (bet == 0) {
                keepPlaying = false;
            } else if (bet > Casino.balance) {
                System.out.println("Insufficient balance. " +
                        "Please bet $" + Casino.balance + " or less.");
            } else {
                final int netChange = handleBet(bet);
                updateBalance(netChange);
            }
        }

        System.out.println("Thanks for playing Craps! " +
                "Final balance: $" + Casino.balance);
    }

    @Override
    public void updateBalance(final int amount) {
        Casino.balance += amount;
        if (amount > 0) {
            System.out.println("Game result: +" + amount);
        } else {
            System.out.println("Game result: " + amount);
        }
        System.out.println("Balance: $" + Casino.balance);
    }
}
