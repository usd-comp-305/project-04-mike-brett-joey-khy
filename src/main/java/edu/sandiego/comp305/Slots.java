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

    private boolean isAllMatch(SlotSymbols[] result){
        SlotSymbols firstSymbol = result[0];
        for (SlotSymbols slotSymbols : result) {
            if (slotSymbols != firstSymbol) {
                return false;
            }
        }
        return true;
    }

    private int getMultiplier(SlotSymbols symbol){
        switch (symbol) {
            case SEVEN:
                return 7;
            case BELL:
                return 6;
            case GRAPE:
                return 5;
            case LEMON:
                return 4;
            case CHERRY:
                return 3;
            case DIAMOND:
                return 12;
            case GOLD_BAR:
                return 8;
            case STRAWBERRY:
                return 2;
            default:
                return -1;
        }
    }

    public int calculatePayout(SlotSymbols[] result, int betAmount) {
        if (isAllMatch(result)){
            return getMultiplier(result[0]) * betAmount;
        } else {
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
            System.out.print("Please enter your bet amount (0 to exit the game) : ");
            int bet = scanner.nextInt();
            if (bet == 0){
                System.out.println("Exiting Slots");
                return;
            }
            if (bet < 0) {
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
