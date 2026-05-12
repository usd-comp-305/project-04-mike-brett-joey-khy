package edu.sandiego.comp305;

import java.util.Random;
import java.util.Scanner;

public class GameFactory {
    public static Game getGame(int choice) {
        Scanner scanner = new Scanner(System.in);
        Random rng = new Random();
        switch (choice) {
            case 1: return new Slots(scanner, rng);
            case 2: return new Craps(scanner, rng);
            case 3: return new Blackjack(scanner);
            case 4: return new Baccarat(scanner);
            case 5: return new Roulette(scanner, rng);
            default: return null;
        }
    }
}
