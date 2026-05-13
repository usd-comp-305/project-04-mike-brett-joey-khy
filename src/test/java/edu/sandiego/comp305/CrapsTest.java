package edu.sandiego.comp305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Scanner;
import java.util.Random;


public class CrapsTest {

    private static final int STARTING_BALANCE = 1000;

    private static final int DIE_MINIMUM_VALUE = 1;

    private static final int DIE_FACE_COUNT = 6;

    private static final int MINIMUM_ROLL_VALUE = 2;

    private static final int MAXIMUM_ROLL_VALUE = 12;

    private static final int TEST_ITERATIONS = 100;

    private static final int TEST_BET_AMOUNT = 100;

    private Scanner scanner;

    private Random rng;

    private Craps craps;

    @BeforeEach
    void setUp() {
        Casino.balance = STARTING_BALANCE;
        scanner = new Scanner(System.in);
        rng = new Random();
        craps = new Craps(scanner,rng);
    }

    @Test
    void initialPointTargetIsZero() {
        assertEquals(0, craps.getPoint());
    }

    @Test
    void initialStateIsComingOut() {
        assertInstanceOf(ComingOutState.class, craps.getCurrentState());
    }

    @Test
    void rollDieIsInValidRange() {
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            final int result = craps.rollDie();
            assertTrue(result >= DIE_MINIMUM_VALUE
                    && result <= DIE_FACE_COUNT);
        }
    }

    @Test
    void rollIsInValidRange() {
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            final int result = craps.roll();
            assertTrue(result >= MINIMUM_ROLL_VALUE
                    && result <= MAXIMUM_ROLL_VALUE);
        }
    }

    @Test
    void handleBetThrowsOnZeroBet() {
        assertThrows(IllegalArgumentException.class, () -> craps.handleBet(0));
    }

    @Test
    void handleBetThrowsOnNegativeBet() {
        assertThrows(IllegalArgumentException.class,
                () -> craps.handleBet(-TEST_BET_AMOUNT));
    }

    @Test
    void updateBalanceIncreasesBalanceOnWin() {
        craps.updateBalance(TEST_BET_AMOUNT);
        assertEquals(STARTING_BALANCE + TEST_BET_AMOUNT, Casino.balance);
    }

    @Test
    void updateBalanceDecreasesBalanceOnLoss() {
        craps.updateBalance(-TEST_BET_AMOUNT);
        assertEquals(STARTING_BALANCE - TEST_BET_AMOUNT, Casino.balance);
    }

    @Test
    void setAndGetPointTarget() {
        craps.setPoint(8);
        assertEquals(8, craps.getPoint());
    }

    @Test
    void setStateTransitionsToPointPhaseState() {
        craps.setState(new PointPhaseState());
        assertInstanceOf(PointPhaseState.class, craps.getCurrentState());
    }
}
