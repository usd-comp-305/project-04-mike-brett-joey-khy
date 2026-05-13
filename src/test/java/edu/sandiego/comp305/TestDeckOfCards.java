package edu.sandiego.comp305;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;


public class TestDeckOfCards {

    public ArrayList<Card> deck;


    @BeforeEach
    void setUp() {
        deck = DeckOfCards.createDeckOfCards();
    }

    @Test
    void testCreatesDeckWith52Cards(){
        assertEquals(52, deck.size());
    }

    @Test
    void testFistCardIsAceOfClubs(){
        assertEquals(CardValues.ACE, deck.getFirst().getFaceValue());
        assertEquals(Suit.CLUB, deck.getFirst().getSuit());
    }

    @Test
    void testAceOfHeartsISCorrect(){
        final Card card = deck.get(13);
        assertEquals(CardValues.ACE, card.getFaceValue());
        assertEquals(Suit.HEART, card.getSuit());
    }

    @Test
    void testAceOfDiamondsIsCorrect(){
        final Card card = deck.get(26);
        assertEquals(CardValues.ACE, card.getFaceValue());
        assertEquals(Suit.DIAMOND, card.getSuit());
    }

    @Test
    void testAceOfSpadesIsCorrect(){
        final Card card = deck.get(39);
        assertEquals(CardValues.ACE, card.getFaceValue());
        assertEquals(Suit.SPADE, card.getSuit());
    }

    @Test
    void testLastCardIsKingOfSpades(){
        assertEquals(CardValues.KING, deck.getLast().getFaceValue());
        assertEquals(Suit.SPADE, deck.getLast().getSuit());
    }

    @Test
    void testShuffleChangesOrder(){
        final ArrayList<CardValues> originalValues = new ArrayList<>();
        for(Card card : deck){
            originalValues.add(card.getFaceValue());
        }

        DeckOfCards.shuffleDeck(deck);

        boolean different = false;

        for(int i = 0; i < deck.size(); i++){
            if(deck.get(i).getFaceValue().getCardValue()
                    != originalValues.get(i).getCardValue()){
                different = true;
            }
        }
        assertTrue(different);
    }

    @Test
    void testShuffleDoesNotRemoveCards(){
        DeckOfCards.shuffleDeck(deck);
        assertEquals(52, deck.size());
    }

    @Test
    void testDealReturnsFirstCard(){
        final Card card = DeckOfCards.dealCard(deck);
        assertEquals(CardValues.ACE, card.getFaceValue());
        assertEquals(Suit.CLUB, card.getSuit());
    }

    @Test
    void testDealRemovesCardFromDeck(){
        final Card card = DeckOfCards.dealCard(deck);
        assertNotEquals(card, deck.getFirst());
    }

}
