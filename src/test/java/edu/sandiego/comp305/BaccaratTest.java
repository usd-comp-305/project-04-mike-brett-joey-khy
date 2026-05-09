package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class BaccaratTest {

    private Baccarat baccarat;

    @BeforeEach
    void setUp(){baccarat = new Baccarat();}



    @Test
    void CardWorthZeroKingTest() {
        Card king = new Card(Suit.SPADE, "K");
        assertEquals(0, baccarat.getCardValue(king));
    }

    @Test
    void CardWorthZeroQueenTest() {
        Card queen = new Card(Suit.SPADE, "Q");
        assertEquals(0, baccarat.getCardValue(queen));
    }

    @Test
    void CardWorthZeroJackTest() {
        Card jack = new Card(Suit.SPADE, "J");
        assertEquals(0, baccarat.getCardValue(jack));
    }

    @Test
    void CardWorthZeroTenTest() {
        Card ten = new Card(Suit.SPADE, "10");
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
    void PlayerGetsTwoCardTest(){
        baccarat.dealInitialCards();
        assertEquals(2, baccarat.getPlayerHand().size());
    }

    @Test
    void BankerGetsTwoCardTest(){
        baccarat.dealInitialCards();
        assertEquals(2, baccarat.getBankerHand().size());
    }

    @Test
    void PlayerHasNaturalNineTest(){
        baccarat.setPlayerTotal(9);
        baccarat.setBankerTotal(2);
        assertTrue(baccarat.isNatural());
    }

    @Test
    void PlayerHasNaturalEightTest(){
        baccarat.setPlayerTotal(8);
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

    @Test
    void NeitherHasNaturalTest(){
        baccarat.setPlayerTotal(3);
        baccarat.setBankerTotal(4);
        assertFalse(baccarat.isNatural());
    }

    @Test
    void PlayerDoesNotDrawOnNaturalTest(){
        baccarat.setPlayerTotal(8);
        baccarat.setBankerTotal(2);
        int totalBefore = baccarat.getPlayerTotal();
        baccarat.drawPlayerThirdCard();
        assertEquals(totalBefore, baccarat.getPlayerTotal());
    }

    @Test
    void PlayerDoesNotDrawOnSixTest(){
        baccarat.setPlayerTotal(6);
        baccarat.setBankerTotal(2);
        int totalBefore = baccarat.getPlayerTotal();
        baccarat.drawPlayerThirdCard();
        assertEquals(totalBefore, baccarat.getPlayerTotal());
    }

    @Test
    void PlayerDoesNotDrawOnSevenTest(){
        baccarat.setPlayerTotal(7);
        baccarat.setBankerTotal(2);
        int totalBefore = baccarat.getPlayerTotal();
        baccarat.drawPlayerThirdCard();
        assertEquals(totalBefore, baccarat.getPlayerTotal());
    }

    @Test
    void PlayerHandSizeOfThreeWhenDrawTest(){
        baccarat.dealInitialCards();
        baccarat.setPlayerTotal(3);
        baccarat.drawPlayerThirdCard();
        assertEquals(3, baccarat.getPlayerHand().size());
    }

    @Test
    void PlayerHandSizeOfTwoWhenNoDrawTest(){
        baccarat.dealInitialCards();
        baccarat.setPlayerTotal(8);
        baccarat.drawPlayerThirdCard();
        assertEquals(2, baccarat.getPlayerHand().size());
    }

    @Test
    void BankerDoesNotDrawOnNaturalTest(){
        baccarat.setPlayerTotal(8);
        baccarat.setBankerTotal(2);
        int totalBefore = baccarat.getBankerTotal();
        baccarat.drawBankerThirdCard();
        assertEquals(totalBefore, baccarat.getBankerTotal());
    }

    @Test
    void BankerDoesNotDrawOnSevenWhenPlayerStandTest(){
        baccarat.setPlayerTotal(6);
        baccarat.setBankerTotal(7);
        int totalBefore = baccarat.getBankerTotal();
        baccarat.drawBankerThirdCard();
        assertEquals(totalBefore, baccarat.getBankerTotal());
    }

    @Test
    void BankerDrawOnFiveWhenPlayerStandTest(){
        baccarat.dealInitialCards();
        baccarat.setPlayerTotal(6);
        baccarat.setBankerTotal(5);
        int totalBefore = baccarat.getBankerTotal();
        baccarat.drawBankerThirdCard();
        assertNotEquals(totalBefore, baccarat.getBankerTotal());
    }

    @Test
    void BankerHandSizeOfThreeWhenDrawTest(){
        baccarat.dealInitialCards();
        baccarat.setPlayerTotal(6);
        baccarat.setBankerTotal(5);
        baccarat.drawBankerThirdCard();
        assertEquals(3, baccarat.getBankerHand().size());
    }

    @Test
    void BankerHandSizeOfTwoWhenNoDrawTest(){
        baccarat.dealInitialCards();
        baccarat.setPlayerTotal(6);
        baccarat.setBankerTotal(7);
        baccarat.drawBankerThirdCard();
        assertEquals(2, baccarat.getBankerHand().size());
    }

    @Test
    void BankerAlwaysDrawOnTwoWhenPlayerDrawTest(){
        baccarat.dealInitialCards();
        baccarat.setPlayerThird(new Card(Suit.SPADE, "5"));
        baccarat.setBankerTotal(2);
        int totalBefore = baccarat.getBankerTotal();
        baccarat.drawBankerThirdCard();
        assertNotEquals(totalBefore, baccarat.getBankerTotal());
    }

    @Test
    void BankerWithThreeDrawsWhenPlayerThirdIsNotEightTest(){
        baccarat.dealInitialCards();
        baccarat.setPlayerThird(new Card(Suit.HEART, "7"));
        baccarat.setBankerTotal(3);
        int totalBefore = baccarat.getBankerTotal();
        baccarat.drawBankerThirdCard();
        assertNotEquals(totalBefore, baccarat.getBankerTotal());
    }

    @Test
    void BankerWithThreeStandsWhenPlayerThirdIsEightTest(){
        baccarat.dealInitialCards();
        baccarat.setPlayerThird(new Card(Suit.HEART, "8"));
        baccarat.setBankerTotal(3);
        int totalBefore = baccarat.getBankerTotal();
        baccarat.drawBankerThirdCard();
        assertEquals(totalBefore, baccarat.getBankerTotal());
    }

    @Test
    void BankerWithFourDrawsWhenPlayerThirdIsFiveTest(){
        baccarat.dealInitialCards();
        baccarat.setPlayerThird(new Card(Suit.HEART, "5"));
        baccarat.setBankerTotal(4);
        int totalBefore = baccarat.getBankerTotal();
        baccarat.drawBankerThirdCard();
        assertNotEquals(totalBefore, baccarat.getBankerTotal());
    }

    @Test
    void BankerWithFourStandsWhenPlayerThirdIsNineTest(){
        baccarat.dealInitialCards();
        baccarat.setPlayerThird(new Card(Suit.HEART, "9"));
        baccarat.setBankerTotal(4);
        int totalBefore = baccarat.getBankerTotal();
        baccarat.drawBankerThirdCard();
        assertEquals(totalBefore, baccarat.getBankerTotal());
    }

    @Test
    void BankerWithFiveDrawsWhenPlayerThirdIsSixTest(){
        baccarat.dealInitialCards();
        baccarat.setPlayerThird(new Card(Suit.HEART, "6"));
        baccarat.setBankerTotal(5);
        int totalBefore = baccarat.getBankerTotal();
        baccarat.drawBankerThirdCard();
        assertNotEquals(totalBefore, baccarat.getBankerTotal());
    }

    @Test
    void BankerWithFiveStandsWhenPlayerThirdIsThreeTest(){
        baccarat.dealInitialCards();
        baccarat.setPlayerThird(new Card(Suit.HEART, "3"));
        baccarat.setBankerTotal(5);
        int totalBefore = baccarat.getBankerTotal();
        baccarat.drawBankerThirdCard();
        assertEquals(totalBefore, baccarat.getBankerTotal());
    }

    @Test
    void BankerWithSixDrawsWhenPlayerThirdIsSevenTest(){
        baccarat.dealInitialCards();
        baccarat.setPlayerThird(new Card(Suit.HEART, "7"));
        baccarat.setBankerTotal(6);
        int totalBefore = baccarat.getBankerTotal();
        baccarat.drawBankerThirdCard();
        assertNotEquals(totalBefore, baccarat.getBankerTotal());
    }

    @Test
    void BankerWithSixStandsWhenPlayerThirdIsFiveTest(){
        baccarat.dealInitialCards();
        baccarat.setPlayerThird(new Card(Suit.HEART, "5"));
        baccarat.setBankerTotal(6);
        int totalBefore = baccarat.getBankerTotal();
        baccarat.drawBankerThirdCard();
        assertEquals(totalBefore, baccarat.getBankerTotal());
    }

    @Test
    void BankerWithSevenAlwaysStandsWhenPlayerDrewTest(){
        baccarat.dealInitialCards();
        baccarat.setPlayerThird(new Card(Suit.HEART, "6"));
        baccarat.setBankerTotal(7);
        int totalBefore = baccarat.getBankerTotal();
        baccarat.drawBankerThirdCard();
        assertEquals(totalBefore, baccarat.getBankerTotal());
    }

    @Test
    void DetermineWinnerReturnsPlayerWhenPlayerTotalHigherTest(){
        baccarat.setBankerTotal(3);
        baccarat.setPlayerTotal(8);
        assertEquals("player", baccarat.determineWinner());
    }

    @Test
    void DetermineWinnerReturnsBankerWhenBankerTotalHigherTest(){
        baccarat.setBankerTotal(7);
        baccarat.setPlayerTotal(2);
        assertEquals("banker", baccarat.determineWinner());
    }

    @Test
    void DetermineWinnerReturnsTieWhenPlayerBankerTieTest(){
        baccarat.setBankerTotal(6);
        baccarat.setPlayerTotal(6);
        assertEquals("tie", baccarat.determineWinner());
    }

    @Test
    void HandleBetReturnPositiveWhenBetOnMatchesWinnerTest(){
        baccarat.setBetOn("player");
        baccarat.setBankerTotal(2);
        baccarat.setPlayerTotal(8);
        assertTrue(baccarat.handleBet(100)>0);
    }

    @Test
    void HandleBetReturnNegativeWhenBetOnMatchesWinnerTest(){
        baccarat.setBetOn("player");
        baccarat.setBankerTotal(8);
        baccarat.setPlayerTotal(2);
        assertEquals(-100, baccarat.handleBet(100));
    }

    @Test
    void HandleBetReturnTieWhenBetOnTieWinsTest(){
        baccarat.setBetOn("tie");
        baccarat.setBankerTotal(8);
        baccarat.setPlayerTotal(8);
        assertEquals(800,baccarat.handleBet(100));
    }
}