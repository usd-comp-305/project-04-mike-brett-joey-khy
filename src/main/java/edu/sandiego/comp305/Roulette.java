package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Roulette implements Game{
    private RouletteWheel wheel;
    private static final int MAX_SPACES_ON_WHEEL = 37;
    private Random rng;
    private Scanner scanner;
    private int userWagerChoice;
    private int userNumberChoice;
    private int lastSpinNumber;
    private Color lastSpinColor;

    public Roulette(Random rng, Scanner scanner){
        this.rng = rng;
        this.scanner = scanner;
        wheel = new RouletteWheel();
    }

    @Override
    public int handleBet(int amount) {
        int singleOptionMultiplier = 35;
        int evenOrOddFactor = 2;

        switch (userWagerChoice){
            case 1: return lastSpinColor.equals(Color.RED) ? amount : -amount;
            case 2: return lastSpinColor.equals(Color.BLACK) ? amount : -amount;
            case 3: return lastSpinColor.equals(Color.GREEN) ? amount * singleOptionMultiplier : -amount;
            case 4: return lastSpinNumber % evenOrOddFactor == 0 ? amount : -amount;
            case 5: return lastSpinNumber % evenOrOddFactor != 0 ? amount : -amount;

            default: return 0;
        }
    }

   @Override
    public int playGame() {
        return 0;
    }

    @Override
    public void updateBalance(int amount) {

    }

    public Color spinWheel() {
        int lastSpinNumber = rng.nextInt(MAX_SPACES_ON_WHEEL);
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

        userWagerChoice = scanner.nextInt();

        if (userWagerChoice == 6){
            System.out.println("Enter the number you would like to bet on: ");
            userNumberChoice = scanner.nextInt();
            return userWagerChoice;
        }
        return userWagerChoice;
    }


}
