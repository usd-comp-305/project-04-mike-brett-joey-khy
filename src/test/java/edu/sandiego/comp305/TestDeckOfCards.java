package edu.sandiego.comp305;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDeckOfCards {
    @Test
    void testCreatesDeckWith52Cards(){
        ArrayList<Card> deck = DeckOfCards.createDeckOfCards();
        assertEquals(52, deck.size());

    }
}
