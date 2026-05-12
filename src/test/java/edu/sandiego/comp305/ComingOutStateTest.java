package edu.sandiego.comp305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ComingOutStateTest {

    private ComingOutState state;
    private Craps mockCraps;

    @BeforeEach
    void setUp() {
        state = new ComingOutState();
        mockCraps = Mockito.mock(Craps.class);
    }

    @Test
    void roll7ReturnsWin() {
        assertEquals("WIN", state.handleRoll(7, mockCraps));
    }

    @Test
    void roll11ReturnsWin() {
        assertEquals("WIN", state.handleRoll(11, mockCraps));
    }

    @Test
    void roll2ReturnsLose() {
        assertEquals("LOSE", state.handleRoll(2, mockCraps));
    }

    @Test
    void roll3ReturnsLose() {
        assertEquals("LOSE", state.handleRoll(3, mockCraps));
    }

    @Test
    void roll12ReturnsLose() {
        assertEquals("LOSE", state.handleRoll(12, mockCraps));
    }

    @Test
    void rollPointValueSetsPointOnContext() {
        state.handleRoll(6, mockCraps);
        verify(mockCraps).setPoint(6);
    }

    @Test
    void rollPointValueTransitionsToPointPhaseState() {
        state.handleRoll(6, mockCraps);
        verify(mockCraps).setState(any(PointPhaseState.class));
    }

    @Test
    void rollPointValueReturnsPointSet() {
        assertEquals("POINT_SET", state.handleRoll(6, mockCraps));
    }
}
