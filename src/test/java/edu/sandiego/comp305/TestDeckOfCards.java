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
        assertEquals(CardValues.ACE, deck.getFirst().cardValue);
        assertEquals(Suit.CLUB, deck.getFirst().suit);
        }



}
