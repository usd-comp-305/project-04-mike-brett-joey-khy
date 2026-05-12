package edu.sandiego.comp305;

import java.util.Random;
import java.util.Scanner;

public class Slots implements Game{
    private final Scanner scanner;
    private final Random rng;
    private final SlotSymbols[] symbols = SlotSymbols.values();

    public Slots(Scanner scanner, Random rng){
        this.scanner = scanner;
        this.rng = rng;
    }

    public SlotSymbols[] spin(){
        SlotSymbols[] spinResults = new SlotSymbols[5];
        for (int i = 0; i < spinResults.length; i++){
            spinResults[i] = symbols[rng.nextInt(symbols.length)];
        }
        return spinResults;
    }

    public int calculatePayout(SlotSymbols result[], int betAmount) {
        SlotSymbols firstSymbol = result[0];
        for (int i = 0; i < result.length; i++) {
            if (result[i] != firstSymbol) {
                return -betAmount;
            }
        }
        switch (firstSymbol) {
            case SEVEN:
                return betAmount * 7;
            case BELL:
                return betAmount * 6;
            case GRAPE:
                return betAmount * 5;
            case LEMON:
                return betAmount * 4;
            case CHERRY:
                return betAmount * 3;
            case DIAMOND:
                return betAmount * 12;
            case GOLD_BAR:
                return betAmount * 8;
            case STRAWBERRY:
                return betAmount * 2;
            default:
                return -betAmount;
        }
    }

    public void printResult(SlotSymbols[] results){
        System.out.print("Results: ");
        for(int i  = 0; i < results.length; i++){
            System.out.print(results[i]);
            if (i< results.length-1){
                System.out.print(" | ");
            }
        }
        System.out.print("\n");
    }

    @Override
    public int handleBet(int amount){
        SlotSymbols[] result = spin();
        printResult(result);
        return calculatePayout(result, amount);
    }

    @Override
    public void playGame() {
        System.out.println("Welcome to Slots");
        boolean playing = true;
        while (playing) {
            System.out.print("Please enter your bet amount: ");
            int bet = scanner.nextInt();
            if (bet <= 0) {
                System.out.println("Invalid Bet. Bet amount must be at least $1");
                continue;
            }
            int netChange = handleBet(bet);
            updateBalance(netChange);
            System.out.println("Current Balance: $" + Casino.balance);
            if (Casino.balance <= 0){
                System.out.println("You are out of money");
                break;
            }
            System.out.println("Play Again? (Yes/No): ");
            String playAgain = scanner.next().toLowerCase();
            if (!playAgain.equals("yes")){
                playing = false;
            }
        }
    }


    @Override
    public void updateBalance(int amount){
        Casino.balance += amount;
        if (amount > 0) {
            System.out.println("Game result: +" + amount);
        } else {
            System.out.println("Game result: " + amount);
        }
    }
}
