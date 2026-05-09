package edu.sandiego.comp305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Random;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RouletteTest {
    private Roulette wheel;
    private Random mockRng;
    private final int MAX_NUM_ON_WHEEL = 36;

    @BeforeEach
    void createTestObjects(){
        wheel = new Roulette();
        mockRng = mock(Random.class);
    }

    @Test
    void testHandleBet(){

    }

    @Test
    void testPlayGame() {

    }

    @Test
    void testUpdateBalance(){

    }

    @Test
    void testSpinWheelFirstRed() {
        when(mockRng.nextInt(MAX_NUM_ON_WHEEL)).thenReturn(1);
        assertEquals(Color.RED, wheel.spinWheel());
    }

    @Test
    void testSpinWheelFirstBlack(){

    }

    @Test
    void testSpinWheelGreen(){

    }
    @Test
    void testCorrectWheelSize(){

    }

    @Test
    void testLastSpaceIsCorrectColor(){

    }
}
