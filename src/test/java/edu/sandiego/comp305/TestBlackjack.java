package edu.sandiego.comp305;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestBlackjack {

    final int HIT = 1;
    final int STAND = 2;
    final int DOUBLE = 3;
    final int SPLIT = 4;
    final int BET_SIZE = 10;

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

        int total = blackjack.calculateHandTotal(blackjack.getPlayerHand());
        assertEquals(12, total);
    }

    @Test
    void testCorrectPlayerTotalAce(){
        Card card1 = new Card(Suit.SPADE, CardValues.ACE);
        Card card2 = new Card(Suit.SPADE, CardValues.TEN);
        blackjack.getPlayerHand().add(card1);
        blackjack.getPlayerHand().add(card2);
        int total = blackjack.calculateHandTotal(blackjack.getPlayerHand());
        assertEquals(21, total);
    }

    @Test
    void testAceGoesBackToOne(){
        Card card1 = new Card(Suit.SPADE, CardValues.ACE);
        Card card2 = new Card(Suit.SPADE, CardValues.TEN);
        Card card3 = new Card(Suit.CLUB, CardValues.ACE);

        blackjack.getPlayerHand().add(card1);
        blackjack.getPlayerHand().add(card2);
        blackjack.getPlayerHand().add(card3);

        int total = blackjack.calculateHandTotal(blackjack.getPlayerHand());
        assertEquals(12, total);
    }

    @Test
    void testHitAddsCardToHand(){
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextInt()).thenReturn(BET_SIZE).thenReturn(HIT).thenReturn(STAND);

        Blackjack blackjack = new Blackjack(mockScanner);

        blackjack.playGame();
        assertEquals(3, blackjack.getPlayerHand().size());
    }

    @Test
    void testDoubleDownAddsCardToHand(){
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextInt()).thenReturn(BET_SIZE).thenReturn(DOUBLE).thenReturn(STAND);

        Blackjack blackjack = new Blackjack(mockScanner);

        blackjack.playGame();
        assertEquals(3, blackjack.getPlayerHand().size());
    }

    @Test
    void testDoubleDownDoublesBet(){
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextInt()).thenReturn(BET_SIZE).thenReturn(DOUBLE);

        Blackjack blackjack = new Blackjack(mockScanner);

        blackjack.playGame();
        assertEquals(20, blackjack.getBetAmount());
    }

    @Test
    void testDoubleDownCantHit(){
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextInt()).thenReturn(BET_SIZE).thenReturn(DOUBLE).thenReturn(HIT);

        Blackjack blackjack = new Blackjack(mockScanner);

        blackjack.playGame();
        assert(blackjack.getPlayerStand());
    }

    @Test
    void splitCreatesNewHand(){
        ArrayList<Card> riggedDeck = new ArrayList<>();

        riggedDeck.add(new Card(Suit.HEART, CardValues.EIGHT));
        riggedDeck.add(new Card(Suit.HEART, CardValues.TEN));
        riggedDeck.add(new Card(Suit.DIAMOND, CardValues.EIGHT));
        riggedDeck.add(new Card(Suit.HEART, CardValues.SEVEN));
        riggedDeck.add(new Card(Suit.HEART, CardValues.FIVE));
        riggedDeck.add(new Card(Suit.HEART, CardValues.SIX));

        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextInt()).thenReturn(SPLIT).thenReturn(STAND).thenReturn(STAND);

        Blackjack blackjack = new Blackjack(mockScanner);
        blackjack.setDeck(riggedDeck);
        blackjack.dealStartingHand();

        int playerTotal = blackjack.calculateHandTotal(blackjack.getPlayerHand());
        blackjack.playerDecisions(blackjack.getPlayerHand(), playerTotal);
        assertFalse(blackjack.getSplitHand().isEmpty());
    }

    @Test
    void cantSplitDifferentCards(){
        ArrayList<Card> riggedDeck = new ArrayList<>();

        riggedDeck.add(new Card(Suit.HEART, CardValues.EIGHT));
        riggedDeck.add(new Card(Suit.HEART, CardValues.TEN));
        riggedDeck.add(new Card(Suit.HEART, CardValues.SEVEN));
        riggedDeck.add(new Card(Suit.HEART, CardValues.FIVE));

        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextInt()).thenReturn(SPLIT).thenReturn(STAND);

        Blackjack blackjack = new Blackjack(mockScanner);
        blackjack.setDeck(riggedDeck);
        blackjack.dealStartingHand();
        int playerTotal = blackjack.calculateHandTotal(blackjack.getPlayerHand());
        blackjack.playerDecisions(blackjack.getPlayerHand(), playerTotal);

        assertTrue(blackjack.getSplitHand().isEmpty());
    }

    @Test
    void cantSplitTwice(){
        ArrayList<Card> riggedDeck = new ArrayList<>();
        riggedDeck.add(new Card(Suit.HEART, CardValues.EIGHT));
        riggedDeck.add(new Card(Suit.HEART, CardValues.TEN));
        riggedDeck.add(new Card(Suit.DIAMOND, CardValues.EIGHT));
        riggedDeck.add(new Card(Suit.HEART, CardValues.SEVEN));
        riggedDeck.add(new Card(Suit.CLUB, CardValues.EIGHT));
        riggedDeck.add(new Card(Suit.SPADE, CardValues.EIGHT));

        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextInt()).thenReturn(SPLIT).thenReturn(SPLIT).thenReturn(STAND).thenReturn(STAND);

        Blackjack blackjack = new Blackjack(mockScanner);
        blackjack.setDeck(riggedDeck);
        blackjack.dealStartingHand();
        int playerTotal = blackjack.calculateHandTotal(blackjack.getPlayerHand());
        blackjack.playerDecisions(blackjack.getPlayerHand(), playerTotal);


        assertEquals(2, blackjack.getPlayerHand().size());
        assertEquals(2, blackjack.getSplitHand().size());
    }

    @Test
    void testDealerHitsBelow17(){
        blackjack.setDeck(DeckOfCards.createDeckOfCards());
        blackjack.getDealerHand().add(new Card(Suit.HEART, CardValues.SIX));
        blackjack.getDealerHand().add(new Card(Suit.HEART, CardValues.SEVEN));

        blackjack.dealerDecisions();

        assertTrue(blackjack.getDealerHand().size() > 2);
    }







}
