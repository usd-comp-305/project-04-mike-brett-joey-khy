package edu.sandiego.comp305;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestBlackjack {
    @Test
    void testPlayerStartsWithTwoCards(){
        Scanner mockScanner = mock(Scanner.class);
        Blackjack blackjack = new Blackjack(mockScanner);
        blackjack.playGame();
        assertEquals(2, blackjack.getPlayerHand().size());
    }

    @Test
    void testDealerStartsWithTwoCards(){
        Scanner mockScanner = mock(Scanner.class);
        Blackjack blackjack = new Blackjack(mockScanner);
        blackjack.playGame();
        assertEquals(2, blackjack.getDealerHand().size());
    }
}
