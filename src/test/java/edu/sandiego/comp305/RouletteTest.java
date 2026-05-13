package edu.sandiego.comp305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RouletteTest {

    private static final int MAX_SPACES_ON_WHEEL = 37;

    private Roulette roulette;

    private Random mockRng;

    private Scanner mockScanner;

    private int amountWagered = 10;

    @BeforeEach
    void createTestObjects(){
        mockRng = mock(Random.class);
        mockScanner = mock(Scanner.class);
        roulette = new Roulette(mockScanner, mockRng);
    }

    @Test
    void testHandleBetWinOnRed(){
        final int redSpaceOnWheel = 1;
        final int userGuessesRed = 1;
        final int amountWon = 10;
        when(mockRng.nextInt(MAX_SPACES_ON_WHEEL)).thenReturn(redSpaceOnWheel);
        when(mockScanner.nextInt()).thenReturn(userGuessesRed);

        roulette.getUserWager();
        roulette.spinWheel();

        assertEquals(amountWon, roulette.handleBet(amountWagered));
    }

    @Test
    void testHandleBetLoseOnRed() {
        final int redSpaceOnWheel = 1;
        final int userGuessesBlack = 2;
        final int amountLost = -10;

        when(mockRng.nextInt(MAX_SPACES_ON_WHEEL)).thenReturn(redSpaceOnWheel);
        when(mockScanner.nextInt()).thenReturn(userGuessesBlack);

        roulette.getUserWager();
        roulette.spinWheel();

        assertEquals(amountLost, roulette.handleBet(amountWagered));
    }

    @Test
    void testHandleBetWinOnGreen(){
        final int greenSpaceOnWheel = 0;
        final int userGuessesGreen = 3;
        final int amountWon = 350;
        when(mockRng.nextInt(MAX_SPACES_ON_WHEEL))
                .thenReturn(greenSpaceOnWheel);
        when(mockScanner.nextInt()).thenReturn(userGuessesGreen);

        roulette.getUserWager();
        roulette.spinWheel();

        assertEquals(amountWon, roulette.handleBet(amountWagered));
    }

    @Test
    void testHandleBetLoseOnGreen(){
        final int redSpaceOnWheel = 1;
        final int userGuessesGreen = 3;
        final int amountLost = -10;
        when(mockRng.nextInt(MAX_SPACES_ON_WHEEL)).thenReturn(redSpaceOnWheel);
        when(mockScanner.nextInt()).thenReturn(userGuessesGreen);

        roulette.getUserWager();
        roulette.spinWheel();

        assertEquals(amountLost, roulette.handleBet(amountWagered));
    }

    @Test
    void testHandleBetWinOnEven(){
        final int evenSpaceOnWheel = 2;
        final int userGuessesEven = 4;
        final int amountWon = 10;
        when(mockRng.nextInt(MAX_SPACES_ON_WHEEL)).thenReturn(evenSpaceOnWheel);
        when(mockScanner.nextInt()).thenReturn(userGuessesEven);

        roulette.getUserWager();
        roulette.spinWheel();

        assertEquals(amountWon, roulette.handleBet(amountWagered));
    }

    @Test
    void testHandleBetLoseOnEven(){
        final int evenSpaceOnWheel = 2;
        final int userGuessesOdd = 5;
        final int amountLost = -10;
        when(mockRng.nextInt(MAX_SPACES_ON_WHEEL)).thenReturn(evenSpaceOnWheel);
        when(mockScanner.nextInt()).thenReturn(userGuessesOdd);

        roulette.getUserWager();
        roulette.spinWheel();

        assertEquals(amountLost, roulette.handleBet(amountWagered));
    }

    @Test
    void testHandleBetWinOnNumber(){
        final int winningNumber = 5;
        final int userGuessesNumber = 6;
        final int userGuessesWinningNumber = 5;
        final int amountWon = 350;
        when(mockRng.nextInt(MAX_SPACES_ON_WHEEL)).thenReturn(winningNumber);
        when(mockScanner.nextInt()).thenReturn(userGuessesNumber)
                .thenReturn(userGuessesWinningNumber);

        roulette.getUserWager();
        roulette.spinWheel();

        assertEquals(amountWon, roulette.handleBet(amountWagered));
    }

    @Test
    void testHandleBetLoseOnNumber(){
        final int winningNumber = 5;
        final int userGuessesNumber = 6;
        final int userGuessesWinningNumber = 4;
        final int amountLost = -10;
        when(mockRng.nextInt(MAX_SPACES_ON_WHEEL)).thenReturn(winningNumber);
        when(mockScanner.nextInt()).thenReturn(userGuessesNumber)
                .thenReturn(userGuessesWinningNumber);

        roulette.getUserWager();
        roulette.spinWheel();

        assertEquals(amountLost, roulette.handleBet(amountWagered));
    }

    @Test
    void testSpinWheelFirstRed() {
        final int redSpaceOnWheel = 1;
        when(mockRng.nextInt(MAX_SPACES_ON_WHEEL))
                .thenReturn(redSpaceOnWheel);
        assertEquals(Color.RED, roulette.spinWheel());
    }

    @Test
    void testSpinWheelFirstBlack(){
        final int blackSpaceOnWheel = 2;
        when(mockRng.nextInt(MAX_SPACES_ON_WHEEL))
                .thenReturn(blackSpaceOnWheel);
        assertEquals(Color.BLACK, roulette.spinWheel());
    }

    @Test
    void testSpinWheelGreen(){
        final int greenSpaceOnWheel = 0;
        when(mockRng.nextInt(MAX_SPACES_ON_WHEEL))
                .thenReturn(greenSpaceOnWheel);
        assertEquals(Color.GREEN, roulette.spinWheel());
    }

    @Test
    void testLastSpaceIsCorrectColor(){
        final int lastSpaceOnWheel = 36;
        when(mockRng.nextInt(MAX_SPACES_ON_WHEEL))
                .thenReturn(lastSpaceOnWheel);
        assertEquals(Color.BLACK, roulette.spinWheel());
    }

    @Test
    void testGetUserWagerColor(){
        final int redSpaceOnWheel = 1;
        final int userGuessesRed = 1;
        when(mockScanner.nextInt()).thenReturn(redSpaceOnWheel);
        assertEquals(userGuessesRed, roulette.getUserWager());
    }

    @Test
    void testGetUserNumberWager(){
        final int userGuessesNumberOption = 6;
        final int userGuessesSpecificNumber = 30;
        when(mockScanner.nextInt()).thenReturn(userGuessesNumberOption)
                .thenReturn(userGuessesSpecificNumber);
        assertEquals(userGuessesNumberOption, roulette.getUserWager());
    }

    @Test
    void testGetUserWagerInvalidUpperWager() {
        final int highValueNotInWagerMenu = 7;
        final int userGuessesRed = 1;
        when(mockScanner.nextInt()).thenReturn(highValueNotInWagerMenu)
                .thenReturn(userGuessesRed);
        assertEquals(userGuessesRed, roulette.getUserWager());
    }

    @Test
    void testGetUserWagerInvalidLowerWager(){
        final int lowValueNotInWagerMenu = 0;
        final int userGuessesRed = 1;
        when(mockScanner.nextInt()).thenReturn(lowValueNotInWagerMenu)
                .thenReturn(userGuessesRed);
        assertEquals(userGuessesRed, roulette.getUserWager());
    }
}
