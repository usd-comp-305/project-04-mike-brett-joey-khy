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
    private int lastSpinNumber;
    private Color lastSpinColor;

    public Roulette(Random rng, Scanner scanner){
        this.rng = rng;
        this.scanner = scanner;
        wheel = new RouletteWheel();
    }

    @Override
    public int handleBet(int amount) {
        if (userWagerChoice == 1 && lastSpinColor.equals(Color.RED)) {
            return amount;
        }
        return 0;
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
        return userWagerChoice;
    }

}
