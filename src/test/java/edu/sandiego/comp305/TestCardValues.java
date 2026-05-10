package edu.sandiego.comp305;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCardValues {

    @Test
    void testGetCorrectFaceValueAce(){
        int aceVal = CardValues.ACE.getCardValue();
        assertEquals(1, aceVal);
    }

}
