package edu.sandiego.comp305;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestBlackjack {

    Blackjack blackjack;

    @BeforeEach
    void setUp(){
        Scanner mockScanner = mock(Scanner.class);
        blackjack = new Blackjack(mockScanner);
    }

    @Test
    void testPlayerStartsWithTwoCards(){
        blackjack.playGame();
        assertEquals(2, blackjack.getPlayerHand().size());
    }

    @Test
    void testDealerStartsWithTwoCards(){
        blackjack.playGame();
        assertEquals(2, blackjack.getDealerHand().size());
    }

    @Test
    void testCorrectPlayerTotal(){
        Card card1 = new Card(Suit.SPADE, CardValues.TWO);
        Card card2 = new Card(Suit.SPADE, CardValues.TEN);
        blackjack.getPlayerHand().add(card1);
        blackjack.getPlayerHand().add(card2);

        blackjack.newPlayerTotal();
        assertEquals(12, blackjack.getPlayerTotal());
    }

    @Test
    void testCorrectPlayerTotalAce(){
        Card card1 = new Card(Suit.SPADE, CardValues.ACE);
        Card card2 = new Card(Suit.SPADE, CardValues.TEN);
        blackjack.getPlayerHand().add(card1);
        blackjack.getPlayerHand().add(card2);
        blackjack.newPlayerTotal();
        assertEquals(21, blackjack.getPlayerTotal());
    }

    @Test
    void testAceGoesBackToOne(){
        Card card1 = new Card(Suit.SPADE, CardValues.ACE);
        Card card2 = new Card(Suit.SPADE, CardValues.TEN);
        Card card3 = new Card(Suit.CLUB, CardValues.ACE);

        blackjack.getPlayerHand().add(card1);
        blackjack.getPlayerHand().add(card2);
        blackjack.getPlayerHand().add(card3);

        blackjack.newPlayerTotal();
        assertEquals(12, blackjack.getPlayerTotal());
    }

    @Test
    void testHitAddsCardToHand(){
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextInt()).thenReturn(10).thenReturn(1).thenReturn(2);

        Blackjack blackjack = new Blackjack(mockScanner);

        blackjack.playGame();
        assertEquals(3, blackjack.getPlayerHand().size());
    }
}
