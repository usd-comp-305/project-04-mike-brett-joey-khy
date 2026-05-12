package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameFactoryTest {

    @Test
    void choice1ReturnsSlots() {
        assertInstanceOf(Slots.class, GameFactory.getGame(1));
    }

    @Test
    void choice2ReturnsCraps() {
        assertInstanceOf(Craps.class, GameFactory.getGame(2));
    }

    @Test
    void choice3ReturnsBlackjack() {
        assertInstanceOf(Blackjack.class, GameFactory.getGame(3));
    }

    @Test
    void choice4ReturnsBaccarat() {
        assertInstanceOf(Baccarat.class, GameFactory.getGame(4));
    }

    @Test
    void choice5ReturnsRoulette() {
        assertInstanceOf(Roulette.class, GameFactory.getGame(5));
    }

    @Test
    void invalidChoiceReturnsNull() {
        assertNull(GameFactory.getGame(99));
    }

    @Test
    void exitChoiceReturnsNull() {
        assertNull(GameFactory.getGame(0));
    }

    @Test
    void allReturnedGamesImplementGameInterface() {
        for (int i = 1; i <= 5; i++) {
            assertInstanceOf(Game.class, GameFactory.getGame(i));
        }
    }
}