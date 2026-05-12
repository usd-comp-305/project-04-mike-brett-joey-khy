package edu.sandiego.comp305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CrapsTest {

    private Craps craps;

    @BeforeEach
    void setUp() {
        Casino.balance = 1000;
        craps = new Craps();
    }

    @Test
    void initialStateIsComingOut() {
        assertTrue(craps.getPoint() == 0);
    }

    @Test
    void rollDieIsInValidRange() {
        for (int i = 0; i < 100; i++) {
            int result = craps.rollDie();
            assertTrue(result >= 1 && result <= 6);
        }
    }

    @Test
    void rollIsInValidRange() {
        for (int i = 0; i < 100; i++) {
            int result = craps.roll();
            assertTrue(result >= 2 && result <= 12);
        }
    }

    @Test
    void handleBetThrowsOnZeroBet() {
        assertThrows(IllegalArgumentException.class, () -> craps.handleBet(0));
    }

    @Test
    void handleBetThrowsOnNegativeBet() {
        assertThrows(IllegalArgumentException.class, () -> craps.handleBet(-50));
    }

    @Test
    void updateBalanceIncreasesBalanceOnWin() {
        craps.updateBalance(100);
        assertEquals(1100, Casino.balance);
    }

    @Test
    void updateBalanceDecreasesBalanceOnLoss() {
        craps.updateBalance(-100);
        assertEquals(900, Casino.balance);
    }

    @Test
    void setAndGetPointTarget() {
        craps.setPoint(8);
        assertEquals(8, craps.getPoint());
    }

    @Test
    void setStateTransitionsCorrectly() {
        craps.setState(new PointPhaseState());
        craps.setPoint(6);
        assertEquals(6, craps.getPoint());
    }
}