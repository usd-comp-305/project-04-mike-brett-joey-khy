package edu.sandiego.comp305;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Roulette implements Game{
    private RouletteWheel wheel;
    private static final int MAX_SPACES_ON_WHEEL = 37;
    private Random rng;
    private Scanner scanner;
    private int WagerType;
    private int specificNumberChoice;
    private int lastSpinNumber;
    private final int SINGLE_OPTION_MULTIPLIER = 35;
    private final int EVEN_OR_ODD_FACTOR = 2;
    private Color lastSpinColor;

    public Roulette(Scanner scanner, Random rng){
        this.rng = rng;
        this.scanner = scanner;
        wheel = new RouletteWheel();
    }

    @Override
    public int handleBet(int amount) {
        switch (WagerType){
            case 1: return lastSpinColor.equals(Color.RED) ? amount : -amount;
            case 2: return lastSpinColor.equals(Color.BLACK) ? amount : -amount;
            case 3: return lastSpinColor.equals(Color.GREEN) ? amount * SINGLE_OPTION_MULTIPLIER : -amount;
            case 4: return lastSpinNumber % EVEN_OR_ODD_FACTOR == 0 ? amount : -amount;
            case 5: return lastSpinNumber % EVEN_OR_ODD_FACTOR != 0 ? amount : -amount;
            case 6: return lastSpinNumber == specificNumberChoice ? amount * SINGLE_OPTION_MULTIPLIER : -amount;
            default: return 0;
        }
    }

   @Override
   public void playGame() {
        boolean isContinuingToPlay = true;
        System.out.println("Welcome to Roulette!");
        while (isContinuingToPlay){
            System.out.println("How much would you like to bet? (Enter 0 to stop playing) ");
            int wagerAmount = scanner.nextInt();
            if (wagerAmount == 0){
                isContinuingToPlay = false;
                continue;
            }
            if (wagerAmount > Casino.balance){
                System.out.println("Invalid wager: You can't bet more than your balance.");
                continue;
            }
            if (wagerAmount < 0) {
                System.out.println("Invalid wager: The bet must be a positive number.");
                continue;
            }

            System.out.println("User wagered $" + wagerAmount);
            getUserWager();

            System.out.println("Spinning wheel...");
            spinWheel();
            System.out.println("The winning square is... " + lastSpinNumber + " " + lastSpinColor);
            int balanceChange = handleBet(wagerAmount);

            updateBalance(balanceChange);
        }
    }

    @Override
    public void updateBalance(int amount) {
        Casino.balance += amount;
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
        List<Color> colors = wheel.getWheel();
        lastSpinColor = colors.get(lastSpinNumber);

        return lastSpinColor;
    }

    public int getUserWager() {
        System.out.println("What is your wager? (Enter only an int)");
        System.out.println("1 for Red");
        System.out.println("2 for Black");
        System.out.println("3 for Green");
        System.out.println("4 for Even");
        System.out.println("5 for Odd");
        System.out.println("6 for a specific number");

        WagerType = scanner.nextInt();
        if (WagerType <= 0 || WagerType > 6){
            System.out.println("Invalid: Answer must be between 1-6. Enter a valid wager.");
            return getUserWager();
        }

        if (WagerType == 6){
            System.out.println("Enter the number you would like to bet on: ");
            specificNumberChoice = scanner.nextInt();
        }
        return WagerType;
    }
}
