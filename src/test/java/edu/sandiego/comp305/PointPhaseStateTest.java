package edu.sandiego.comp305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PointPhaseStateTest {

    private PointPhaseState state;
    private Craps mockCraps;

    @BeforeEach
    void setUp() {
        state = new PointPhaseState();
        mockCraps = Mockito.mock(Craps.class);
        when(mockCraps.getPoint()).thenReturn(6);
    }

    @Test
    void rollingPointReturnsWin() {
        assertEquals("WIN", state.handleRoll(6, mockCraps));
    }

    @Test
    void rollingPointTransitionsToComingOutState() {
        state.handleRoll(6, mockCraps);
        verify(mockCraps).setState(any(ComingOutState.class));
    }

    @Test
    void rolling7ReturnsLose() {
        assertEquals("LOSE", state.handleRoll(7, mockCraps));
    }

    @Test
    void rolling7TransitionsToComingOutState() {
        state.handleRoll(7, mockCraps);
        verify(mockCraps).setState(any(ComingOutState.class));
    }

    @Test
    void rollingNeitherPointNor7ReturnsContinue() {
        assertEquals("CONTINUE", state.handleRoll(4, mockCraps));
    }

    @Test
    void rollingContinueDoesNotTransitionState() {
        state.handleRoll(4, mockCraps);
        verify(mockCraps, never()).setState(any());
    }
}