package edu.sandiego.comp305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RouletteTest {
    private Roulette wheel;
    private Random mockRng;
    private Scanner mockScanner;
    private final int MAX_SPACES_ON_WHEEL = 37;

    @BeforeEach
    void createTestObjects(){
        mockRng = mock(Random.class);
        mockScanner = mock(Scanner.class);
        wheel = new Roulette(mockRng, mockScanner);
    }

    @Test
    void testHandleBet(){
        int redSpaceOnWheel = 1;
        int userGuessesRed = 1;
        when(mockRng.nextInt(MAX_SPACES_ON_WHEEL)).thenReturn(redSpaceOnWheel);
        when(mockScanner.nextInt()).thenReturn(userGuessesRed);

        wheel.getUserWager();
        wheel.spinWheel();

        assertEquals(10, wheel.handleBet(10));
    }

    @Test
    void testPlayGame() {

    }

    @Test
    void testUpdateBalance(){

    }

    @Test
    void testSpinWheelFirstRed() {
        when(mockRng.nextInt(MAX_SPACES_ON_WHEEL)).thenReturn(1);
        assertEquals(Color.RED, wheel.spinWheel());
    }

    @Test
    void testSpinWheelFirstBlack(){
        when(mockRng.nextInt(MAX_SPACES_ON_WHEEL)).thenReturn(2);
        assertEquals(Color.BLACK, wheel.spinWheel());
    }

    @Test
    void testSpinWheelGreen(){
        when(mockRng.nextInt(MAX_SPACES_ON_WHEEL)).thenReturn(0);
        assertEquals(Color.GREEN, wheel.spinWheel());
    }

    @Test
    void testLastSpaceIsCorrectColor(){
        when(mockRng.nextInt(MAX_SPACES_ON_WHEEL)).thenReturn(36);
        assertEquals(Color.BLACK, wheel.spinWheel());
    }

    @Test
    void testGetUserWager(){
        when(mockScanner.nextInt()).thenReturn(1);
        assertEquals(1, wheel.getUserWager());
    }
}
