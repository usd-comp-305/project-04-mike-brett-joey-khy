package edu.sandiego.comp305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Scanner;
import java.util.Random;

public class SlotsTest {

    private Slots slots;
    private Scanner scanner;
    private Random rng;

    @BeforeEach
    void setUp(){
        slots = new Slots(scanner, rng);
    }

    @Test
    void CorrectNumberOfSymbolsTest(){
        assertEquals(8, SlotSymbols.values().length);
    }

    @Test
    void SpinReturnsFiveSymbolsTest(){
        SlotSymbols[] result = slots.spin();
        assertEquals(5, result.length);
    }

    @Test
    void SpinReturnsValidSymbolsTest(){
        SlotSymbols[] result = slots.spin();
        for (SlotSymbols slotSymbols : result) {
            assertInstanceOf(SlotSymbols.class, slotSymbols);
        }
    }

    @Test
    void SpinIsDifferentOverMultipleIterationsTest(){
        boolean difference = false;
        int smallSample = 10;
        SlotSymbols[] firstSpin = slots.spin();
        for (int i = 0; i < smallSample; i++){
            SlotSymbols[] next = slots.spin();
            for (int j = 0; j < next.length; j++){
                if (next[j] != firstSpin[j]) {
                    difference = true;
                    break;
                }
            }
        }
        assertTrue(difference);
    }

    @Test
    void CalculateBetReturnsNegativeWithNoMatchTest(){
        SlotSymbols[] result = {SlotSymbols.DIAMOND,SlotSymbols.LEMON,SlotSymbols.DIAMOND,SlotSymbols.STRAWBERRY,SlotSymbols.BELL};
        assertEquals(-100, slots.calculatePayout(result,100));
    }

    @Test
    void MatchingStrawberryReturns3xBetTest(){
        SlotSymbols[] result = {SlotSymbols.STRAWBERRY,SlotSymbols.STRAWBERRY,SlotSymbols.STRAWBERRY,SlotSymbols.STRAWBERRY,SlotSymbols.STRAWBERRY};
        assertEquals(200, slots.calculatePayout(result,100));
    }

    @Test
    void MatchingCherryReturns3xBetTest(){
        SlotSymbols[] result = {SlotSymbols.CHERRY,SlotSymbols.CHERRY,SlotSymbols.CHERRY,SlotSymbols.CHERRY,SlotSymbols.CHERRY};
        assertEquals(300, slots.calculatePayout(result,100));
    }

    @Test
    void MatchingLemonReturns4xBetTest(){
        SlotSymbols[] result = {SlotSymbols.LEMON,SlotSymbols.LEMON,SlotSymbols.LEMON,SlotSymbols.LEMON,SlotSymbols.LEMON};
        assertEquals(400, slots.calculatePayout(result,100));
    }

    @Test
    void MatchingGrapeReturns5xBetTest(){
        SlotSymbols[] result = {SlotSymbols.GRAPE,SlotSymbols.GRAPE,SlotSymbols.GRAPE,SlotSymbols.GRAPE,SlotSymbols.GRAPE};
        assertEquals(500, slots.calculatePayout(result,100));
    }

    @Test
    void MatchingBellReturns6xBetTest(){
        SlotSymbols[] result = {SlotSymbols.BELL,SlotSymbols.BELL,SlotSymbols.BELL,SlotSymbols.BELL,SlotSymbols.BELL};
        assertEquals(600, slots.calculatePayout(result,100));
    }

    @Test
    void MatchingSevenReturns7xBetTest(){
        SlotSymbols[] result = {SlotSymbols.SEVEN,SlotSymbols.SEVEN,SlotSymbols.SEVEN,SlotSymbols.SEVEN,SlotSymbols.SEVEN};
        assertEquals(700, slots.calculatePayout(result,100));
    }

    @Test
    void MatchingGoldBarReturns8xBetTest(){
        SlotSymbols[] result = {SlotSymbols.GOLD_BAR,SlotSymbols.GOLD_BAR,SlotSymbols.GOLD_BAR,SlotSymbols.GOLD_BAR,SlotSymbols.GOLD_BAR};
        assertEquals(800, slots.calculatePayout(result,100));
    }

    @Test
    void MatchingDiamondReturns12xBetTest(){
        SlotSymbols[] result = {SlotSymbols.DIAMOND,SlotSymbols.DIAMOND,SlotSymbols.DIAMOND,SlotSymbols.DIAMOND,SlotSymbols.DIAMOND};
        assertEquals(1200, slots.calculatePayout(result,100));
    }

    @Test
    void HandleBetReturnsCorrectValueRange(){
        int result = slots.handleBet(100);
        assertTrue(result>= -100 && result<=1200);
    }
}
