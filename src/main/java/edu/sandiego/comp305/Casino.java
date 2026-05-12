package edu.sandiego.comp305;

import java.util.Scanner;

public class Casino {

    public static int balance = 1000;
    public static String playerName;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to USD Casino!");
        System.out.print("Enter your name: ");
        playerName = scanner.nextLine();
        System.out.println("\nHello, " + playerName + "! You're starting with $" + balance + ".\n");

        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Choose a game: ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                running = false;
                continue;
            }

            Game selectedGame = GameFactory.getGame(choice);

            if (selectedGame == null) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }

            selectedGame.playGame();

            if (balance <= 0) {
                System.out.println("\nYou're out of money! Thanks for playing, " + playerName + ".");
                running = false;
            }
        }

        System.out.println("\nGoodbye, " + playerName + "! You're leaving with $" + balance + ".");
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
}