package edu.sandiego.comp305;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Roulette implements Game{
    private static final int SINGLE_OPTION_MULTIPLIER = 35;

    private static final int EVEN_OR_ODD_FACTOR = 2;

    private static final int MAX_WAGER_TYPE = 6;

    private static final int MAX_ROULETTE_NUMBER = 36;

    private static final int MAX_SPACES_ON_WHEEL = 37;

    private RouletteWheel wheel;

    private final Random rng;

    private final Scanner scanner;

    private int WagerType;

    private int specificNumberChoice;

    private int lastSpinNumber;

    private Color lastSpinColor;

    public Roulette(final Scanner scanner,final Random rng){
        this.rng = rng;
        this.scanner = scanner;
        wheel = new RouletteWheel();
    }

    @Override
    public int handleBet(final int amount) {
        switch (WagerType) {
            case 1:
                if (lastSpinColor.equals(Color.RED)) {
                    return amount;
                }
                return -amount;
            case 2:
                if (lastSpinColor.equals(Color.BLACK)) {
                    return amount;
                }
                return -amount;
            case 3:
                if (lastSpinColor.equals(Color.GREEN)) {
                    return amount * SINGLE_OPTION_MULTIPLIER;
                }
                return -amount;
            case 4:
                if (lastSpinNumber % EVEN_OR_ODD_FACTOR == 0) {
                    return amount;
                }
                return -amount;
            case 5:
                if (lastSpinNumber % EVEN_OR_ODD_FACTOR != 0) {
                    return amount;
                }
                return -amount;
            case 6:
                if (lastSpinNumber == specificNumberChoice) {
                    return amount * SINGLE_OPTION_MULTIPLIER;
                }
                return -amount;
            default:
                return 0;
        }
    }


    @Override
    public void playGame() {
        boolean isContinuingToPlay = true;
        System.out.println("Welcome to Roulette!");
        while (isContinuingToPlay){
            System.out.println("How much would you like " +
                    "to bet? (Enter 0 to stop playing) ");
            final int wagerAmount = scanner.nextInt();
            if (wagerAmount == 0){
                isContinuingToPlay = false;
                continue;
            }
            if (wagerAmount > Casino.balance){
                System.out.println("Invalid wager: You can't " +
                        "bet more than your balance.");
                continue;
            }
            if (wagerAmount < 0) {
                System.out.println("Invalid wager: The bet " +
                        "must be a positive number.");
                continue;
            }

            System.out.println("User wagered $" + wagerAmount);
            getUserWager();

            System.out.println("Spinning wheel...");
            spinWheel();
            System.out.println("The winning square is... "
                    + lastSpinNumber + " " + lastSpinColor);
            final int balanceChange = handleBet(wagerAmount);

            updateBalance(balanceChange);
        }
    }

    @Override
    public void updateBalance(final int amount) {
        Casino.addToBalance(amount);
        if (amount > 0){
            System.out.println("Congrats! You won $" + amount);
        }
        if (amount < 0){
            System.out.println("Sorry, you lost $" + amount);
        }
        System.out.println("Balance: " + Casino.balance);
    }

    public Color spinWheel() {
        lastSpinNumber = rng.nextInt(MAX_SPACES_ON_WHEEL);
        final List<Color> colors = wheel.getWheel();
        lastSpinColor = colors.get(lastSpinNumber);

        return lastSpinColor;
    }

    public int getUserWager() {
        System.out.println("What is your wager? " +
                "(Enter only an int)");
        System.out.println("1 for Red");
        System.out.println("2 for Black");
        System.out.println("3 for Green");
        System.out.println("4 for Even");
        System.out.println("5 for Odd");
        System.out.println("6 for a specific number");

        WagerType = scanner.nextInt();
        if (WagerType <= 0 || WagerType > MAX_WAGER_TYPE){
            System.out.println("Invalid: Answer must be " +
                    "between 1-6. Enter a valid wager.");
            return getUserWager();
        }

        if (WagerType == MAX_WAGER_TYPE){
            System.out.println("Enter the number you " +
                    "would like to bet on: ");
            specificNumberChoice = scanner.nextInt();
            if (specificNumberChoice < 0 ||
                    specificNumberChoice > MAX_ROULETTE_NUMBER){
                System.out.println("Invalid: Number " +
                        "must be between 0-36. Restarting...");
                return getUserWager();
            }
        }
        return WagerType;
    }
}
