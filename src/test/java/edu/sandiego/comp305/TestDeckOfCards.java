package edu.sandiego.comp305;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;


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
        Card card = deck.get(13);
        assertEquals(CardValues.ACE, card.getFaceValue());
        assertEquals(Suit.HEART, card.getSuit());
    }

    @Test
    void testAceOfDiamondsIsCorrect(){
        Card card = deck.get(26);
        assertEquals(CardValues.ACE, card.getFaceValue());
        assertEquals(Suit.DIAMOND, card.getSuit());
    }

    @Test
    void testAceOfSpadesIsCorrect(){
        Card card = deck.get(39);
        assertEquals(CardValues.ACE, card.getFaceValue());
        assertEquals(Suit.SPADE, card.getSuit());
    }

    @Test
    void testLastCardIsKingOfSpades(){
        assertEquals(CardValues.KING, deck.getLast().getFaceValue());
        assertEquals(Suit.SPADE, deck.getLast().getSuit());
    }




}
