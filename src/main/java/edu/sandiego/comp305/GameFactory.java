package edu.sandiego.comp305;

import java.util.Scanner;

public class GameFactory {
    public static Game getGame(int choice) {
        switch (choice) {
            case 1: return new Slots();
            case 2: return new Craps();
            case 3: return new Blackjack();
            case 4: return new Baccarat();
            case 5: return new Roulette();
            default: return null;
        }
    }
}
