package edu.sandiego.comp305;

import java.util.Random;
import java.util.Scanner;

public class Slots implements Game{

    private final Random random = new Random();
    private final SlotSymbols[] symbols = SlotSymbols.values();

    public SlotSymbols[] spin(){
        SlotSymbols[] results = new SlotSymbols[5];
        for (int i = 0; i < results.length; i++){
            results[i] = symbols[random.nextInt(symbols.length)];
        }
        return results;
    }

    public int calculatePayout(SlotSymbols result[], int betAmount) {
        SlotSymbols first = result[0];
        for (int i = 0; i < result.length; i++) {
            if (result[i] != first) {
                return -betAmount;
            }
        }
        switch (first) {
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
    }

    @Override
    public int handleBet(int amount){
        if (amount <= 0) {
            throw new IllegalArgumentException("Bet amount must be at least $1");
        }
        SlotSymbols[] result = spin();
        printResult(result);
        return calculatePayout(result, amount);
    }

    @Override
    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Slots");
        System.out.print("Please enter your bet amount: ");
        int bet = scanner.nextInt();
        int netChange = handleBet(bet);
        updateBalance(netChange);
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
