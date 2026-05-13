package edu.sandiego.comp305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Scanner;
import java.util.Random;

public class ComingOutStateTest {

    private ComingOutState state;

    private Craps craps;

    private Scanner scanner;

    private Random rng;

    @BeforeEach
    void setUp() {
        state = new ComingOutState();
        craps = new Craps(scanner, rng);
    }

    @Test
    void roll7ReturnsWin() {
        assertEquals("WIN", state.handleRoll(7, craps));
    }

    @Test
    void roll11ReturnsWin() {
        assertEquals("WIN", state.handleRoll(11, craps));
    }

    @Test
    void roll2ReturnsLose() {
        assertEquals("LOSE", state.handleRoll(2, craps));
    }

    @Test
    void roll3ReturnsLose() {
        assertEquals("LOSE", state.handleRoll(3, craps));
    }

    @Test
    void roll12ReturnsLose() {
        assertEquals("LOSE", state.handleRoll(12, craps));
    }

    @Test
    void rollPointValueSetsPointOnContext() {
        state.handleRoll(6, craps);
        assertEquals(6, craps.getPoint());
    }

    @Test
    void rollPointValueTransitionsToPointPhaseState() {
        state.handleRoll(6, craps);
        assertInstanceOf(PointPhaseState.class, craps.getCurrentState());
    }

    @Test
    void rollPointValueReturnsPointSet() {
        assertEquals("POINT_SET", state.handleRoll(6, craps));
    }
}
