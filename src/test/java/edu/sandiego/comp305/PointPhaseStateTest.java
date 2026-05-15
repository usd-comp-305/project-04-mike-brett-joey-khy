package edu.sandiego.comp305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class PointPhaseStateTest {

    private PointPhaseState state;

    private Craps craps;

    private Scanner scanner;

    private Random rng;

    @BeforeEach
    void setUp() {
        scanner = new Scanner("");
        rng = new Random(0);
        state = new PointPhaseState();
        craps = new Craps(scanner,rng);
        craps.setPoint(6);
        craps.setState(new PointPhaseState());
    }

    @Test
    void rollingPointReturnsWin() {
        assertEquals("WIN", state.handleRoll(6, craps));
    }

    @Test
    void rollingPointTransitionsToComingOutState() {
        state.handleRoll(6, craps);
        assertInstanceOf(ComingOutState.class, craps.getCurrentState());
    }

    @Test
    void rolling7ReturnsLose() {
        assertEquals("LOSE", state.handleRoll(7, craps));
    }

    @Test
    void rolling7TransitionsToComingOutState() {
        state.handleRoll(7, craps);
        assertInstanceOf(ComingOutState.class, craps.getCurrentState());
    }

    @Test
    void rollingNeitherPointNor7ReturnsContinue() {
        assertEquals("CONTINUE", state.handleRoll(4, craps));
    }

    @Test
    void rollingContinueDoesNotTransitionState() {
        state.handleRoll(4, craps);
        assertInstanceOf(PointPhaseState.class, craps.getCurrentState());
    }
}
