package edu.sandiego.comp305;

import java.util.Scanner;
import java.nio.charset.StandardCharsets;

public class Casino {

    static int balance = 1000;

    private static String playerName;

    private static Scanner scanner = new Scanner(System.in,
            StandardCharsets.UTF_8);

    private Casino(){
    }

    private static void main(final String[] args) {
        greetPlayer();
        while (isOpen()) {
            printMenu();
            final int choice = promptChoice();
            if (choice == 0) {
                break;
            }
            runGame(choice);
        }
        exitCasino();
    }

    private static void greetPlayer() {
        System.out.println("Welcome to USD Casino!");
        System.out.print("Enter your name: ");
        playerName = scanner.nextLine();
        System.out.println("\nHello, " + playerName +
                "! You're starting with $" + balance + ".\n");
    }

    private static int promptChoice() {
        System.out.print("Choose a game: ");
        return scanner.nextInt();
    }

    public static void runGame(final int choice) {
        final Game selectedGame = GameFactory.getGame(choice);
        if (selectedGame == null) {
            System.out.println("Invalid choice. Please try again.");
            return;
        }
        selectedGame.playGame();
    }

    public static boolean isOpen() {

        return balance > 0;
    }

    public static void exitCasino() {
        System.out.println("\nGoodbye, " + playerName +
                "! You're leaving with $" + balance + ".");
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n--- USD Casino ---");
        System.out.println("Balance: $" + balance);
        System.out.println("1. Slots");
        System.out.println("2. Craps");
        System.out.println("3. Blackjack");
        System.out.println("4. Baccarat");
        System.out.println("5. Roulette");
        System.out.println("0. Exit");
        System.out.println("------------------");
    }

    static void addToBalance(final int amount){
        balance += amount;
    }
}


