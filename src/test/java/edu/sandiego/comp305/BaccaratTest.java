package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class BaccaratTest {

    private Baccarat baccarat;

    @BeforeEach
    void setUp(){baccarat = new Baccarat();}



    @Test
    void CardWorthZeroTest() {
        Card king = new Card(Suit.SPADE, "K");
        Card queen = new Card(Suit.SPADE, "Q");
        Card jack = new Card(Suit.SPADE, "J");
        Card ten = new Card(Suit.SPADE, "10");
        assertEquals(0, baccarat.getCardValue(king));
        assertEquals(0, baccarat.getCardValue(queen));
        assertEquals(0, baccarat.getCardValue(jack));
        assertEquals(0, baccarat.getCardValue(ten));
    }

    @Test
    void AceWorthOneTest(){
        Card ace = new Card(Suit.SPADE,"A");
        assertEquals(1, baccarat.getCardValue(ace));
    }

    @Test
    void CardWorthNumberNineTest(){
        Card nine = new Card(Suit.SPADE,"9");
        assertEquals(9, baccarat.getCardValue(nine));
    }

    @Test
    void CardWorthNumberTwoTest(){
        Card two = new Card(Suit.SPADE,"2");
        assertEquals(2, baccarat.getCardValue(two));
    }

    @Test
    void DealInitialCardWithCorrectScoringPlayerTest(){
        baccarat.dealInitialCards();
        assertTrue(baccarat.getPlayerTotal() >= 0 && baccarat.getPlayerTotal() <= 9);
    }

    @Test
    void DealInitialCardWithCorrectScoringBankingTest(){
        baccarat.dealInitialCards();
        assertTrue(baccarat.getBankerTotal() >= 0 && baccarat.getBankerTotal() <= 9);
    }

    @Test
    void PlayerHasNaturalNineTest(){
        baccarat.setPlayerTotal(9);
        baccarat.setBankerTotal(2);
        assertTrue(baccarat.isNatural());
    }

    @Test
    void PlayerHasNaturalEightTest(){
        baccarat.setPlayerTotal(9);
        baccarat.setBankerTotal(2);
        assertTrue(baccarat.isNatural());
    }

    @Test
    void BankerHasNaturalNineTest(){
        baccarat.setPlayerTotal(3);
        baccarat.setBankerTotal(9);
        assertTrue(baccarat.isNatural());
    }

    @Test
    void BankerHasNaturalEightTest(){
        baccarat.setPlayerTotal(3);
        baccarat.setBankerTotal(8);
        assertTrue(baccarat.isNatural());
    }

}