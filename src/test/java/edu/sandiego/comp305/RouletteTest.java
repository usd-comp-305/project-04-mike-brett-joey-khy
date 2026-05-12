package edu.sandiego.comp305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RouletteTest {
    private Roulette roulette;
    private Random mockRng;
    private Scanner mockScanner;
    private final int MAX_SPACES_ON_WHEEL = 37;
    private final int MIN_WHEEL_SIZE = 0;
    private int amountWagered = 10;

    @BeforeEach
    void createTestObjects(){
        mockRng = mock(Random.class);
        mockScanner = mock(Scanner.class);
        roulette = new Roulette(mockScanner, mockRng);
    }

    @Test
    void testHandleBetWinOnRed(){
        int redSpaceOnWheel = 1;
        int userGuessesRed = 1;
        int amountWon = 10;
        when(mockRng.nextInt(MAX_SPACES_ON_WHEEL)).thenReturn(redSpaceOnWheel);
        when(mockScanner.nextInt()).thenReturn(userGuessesRed);

        roulette.getUserWager();
        roulette.spinWheel();

        assertEquals(amountWon, roulette.handleBet(amountWagered));
    }

    @Test
    void testHandleBetLoseOnRed() {
        int redSpaceOnWheel = 1;
        int userGuessesBlack = 2;
        int amountLost = -10;

        when(mockRng.nextInt(MAX_SPACES_ON_WHEEL)).thenReturn(redSpaceOnWheel);
        when(mockScanner.nextInt()).thenReturn(userGuessesBlack);

        roulette.getUserWager();
        roulette.spinWheel();

        assertEquals(amountLost, roulette.handleBet(amountWagered));
    }

    @Test
    void testHandleBetWinOnGreen(){
        int greenSpaceOnWheel = 0;
        int userGuessesGreen = 3;
        int amountWon = 350;
        when(mockRng.nextInt(MAX_SPACES_ON_WHEEL)).thenReturn(greenSpaceOnWheel);
        when(mockScanner.nextInt()).thenReturn(userGuessesGreen);

        roulette.getUserWager();
        roulette.spinWheel();

        assertEquals(amountWon, roulette.handleBet(amountWagered));
    }

    @Test
    void testHandleBetLoseOnGreen(){
        int redSpaceOnWheel = 1;
        int userGuessesGreen = 3;
        int amountLost = -10;
        when(mockRng.nextInt(MAX_SPACES_ON_WHEEL)).thenReturn(redSpaceOnWheel);
        when(mockScanner.nextInt()).thenReturn(userGuessesGreen);

        roulette.getUserWager();
        roulette.spinWheel();

        assertEquals(amountLost, roulette.handleBet(amountWagered));
    }

    @Test
    void testHandleBetWinOnEven(){
        int evenSpaceOnWheel = 2;
        int userGuessesEven = 4;
        int amountWon = 10;
        when(mockRng.nextInt(MAX_SPACES_ON_WHEEL)).thenReturn(evenSpaceOnWheel);
        when(mockScanner.nextInt()).thenReturn(userGuessesEven);

        roulette.getUserWager();
        roulette.spinWheel();

        assertEquals(amountWon, roulette.handleBet(amountWagered));
    }

    @Test
    void testHandleBetLoseOnEven(){
        int evenSpaceOnWheel = 2;
        int userGuessesOdd = 5;
        int amountLost = -10;
        when(mockRng.nextInt(MAX_SPACES_ON_WHEEL)).thenReturn(evenSpaceOnWheel);
        when(mockScanner.nextInt()).thenReturn(userGuessesOdd);

        roulette.getUserWager();
        roulette.spinWheel();

        assertEquals(amountLost, roulette.handleBet(amountWagered));
    }

    @Test
    void testHandleBetWinOnNumber(){
        int winningNumber = 5;
        int userGuessesNumber = 6;
        int userGuessesWinningNumber = 5;
        int amountWon = 350;
        when(mockRng.nextInt(MAX_SPACES_ON_WHEEL)).thenReturn(winningNumber);
        when(mockScanner.nextInt()).thenReturn(userGuessesNumber).thenReturn(userGuessesWinningNumber);

        roulette.getUserWager();
        roulette.spinWheel();

        assertEquals(amountWon, roulette.handleBet(amountWagered));
    }

    @Test
    void testHandleBetLoseOnNumber(){
        int winningNumber = 5;
        int userGuessesNumber = 6;
        int userGuessesWinningNumber = 4;
        int amountLost = 10;
        when(mockRng.nextInt(MAX_SPACES_ON_WHEEL)).thenReturn(winningNumber);
        when(mockScanner.nextInt()).thenReturn(userGuessesNumber).thenReturn(userGuessesWinningNumber);

        roulette.getUserWager();
        roulette.spinWheel();

        assertEquals(amountLost, roulette.handleBet(amountWagered));
    }

    @Test
    void testSpinWheelFirstRed() {
        int redSpaceOnWheel = 1;
        when(mockRng.nextInt(MAX_SPACES_ON_WHEEL)).thenReturn(redSpaceOnWheel);
        assertEquals(Color.RED, roulette.spinWheel());
    }

    @Test
    void testSpinWheelFirstBlack(){
        int blackSpaceOnWheel = 2;
        when(mockRng.nextInt(MAX_SPACES_ON_WHEEL)).thenReturn(blackSpaceOnWheel);
        assertEquals(Color.BLACK, roulette.spinWheel());
    }

    @Test
    void testSpinWheelGreen(){
        int greenSpaceOnWheel = 0;
        when(mockRng.nextInt(MAX_SPACES_ON_WHEEL)).thenReturn(greenSpaceOnWheel);
        assertEquals(Color.GREEN, roulette.spinWheel());
    }

    @Test
    void testLastSpaceIsCorrectColor(){
        int lastSpaceOnWheel = 36;
        when(mockRng.nextInt(MAX_SPACES_ON_WHEEL)).thenReturn(lastSpaceOnWheel);
        assertEquals(Color.BLACK, roulette.spinWheel());
    }

    @Test
    void testGetUserWagerColor(){
        int redSpaceOnWheel = 1;
        int userGuessesRed = 1;
        when(mockScanner.nextInt()).thenReturn(redSpaceOnWheel);
        assertEquals(userGuessesRed, roulette.getUserWager());
    }

    @Test
    void testGetUserNumberWager(){
        int userGuessesNumberOption = 6;
        int userGuessesSpecificNumber = 30;
        when(mockScanner.nextInt()).thenReturn(userGuessesNumberOption).thenReturn(userGuessesSpecificNumber);
        assertEquals(userGuessesNumberOption, roulette.getUserWager());
    }

    @Test
    void testGetUserWagerInvalidUpperWager() {
        int highValueNotInWagerMenu = 7;
        int userGuessesRed = 1;
        when(mockScanner.nextInt()).thenReturn(highValueNotInWagerMenu).thenReturn(userGuessesRed);
        assertEquals(userGuessesRed, roulette.getUserWager());
    }

    @Test
    void testGetUserWagerInvalidLowerWager(){
        int lowValueNotInWagerMenu = 0;
        int userGuessesRed = 1;
        when(mockScanner.nextInt()).thenReturn(lowValueNotInWagerMenu).thenReturn(userGuessesRed);
        assertEquals(userGuessesRed, roulette.getUserWager());
    }
}
