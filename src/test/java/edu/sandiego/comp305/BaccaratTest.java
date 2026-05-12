package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class BaccaratTest {

    private Baccarat baccarat;

    @BeforeEach
    void setUp(){baccarat = new Baccarat();
    Casino.balance = 1000;
    }



    @Test
    void CardWorthZeroKingTest() {
        Card king = new Card(Suit.SPADE, CardValues.KING);
        assertEquals(0, Baccarat.getCardValue(king));
    }

    @Test
    void CardWorthZeroQueenTest() {
        Card queen = new Card(Suit.SPADE, CardValues.QUEEN);
        assertEquals(0, Baccarat.getCardValue(queen));
    }

    @Test
    void CardWorthZeroJackTest() {
        Card jack = new Card(Suit.SPADE, CardValues.JACK);
        assertEquals(0, Baccarat.getCardValue(jack));
    }

    @Test
    void CardWorthZeroTenTest() {
        Card ten = new Card(Suit.SPADE, CardValues.TEN);
        assertEquals(0, Baccarat.getCardValue(ten));
    }

    @Test
    void AceWorthOneTest(){
        Card ace = new Card(Suit.SPADE, CardValues.ACE);
        assertEquals(1, Baccarat.getCardValue(ace));
    }

    @Test
    void CardWorthNumberNineTest(){
        Card nine = new Card(Suit.SPADE, CardValues.NINE);
        assertEquals(9, Baccarat.getCardValue(nine));
    }

    @Test
    void CardWorthNumberTwoTest(){
        Card two = new Card(Suit.SPADE, CardValues.TWO);
        assertEquals(2, Baccarat.getCardValue(two));
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
        int sizeBefore = baccarat.getPlayerHand().size();
        baccarat.drawPlayerThirdCard();
        assertEquals(sizeBefore, baccarat.getPlayerHand().size());
    }

    @Test
    void PlayerDoesNotDrawOnSixTest(){
        baccarat.setPlayerTotal(6);
        baccarat.setBankerTotal(2);
        int sizeBefore = baccarat.getPlayerHand().size();
        baccarat.drawPlayerThirdCard();
        assertEquals(sizeBefore, baccarat.getPlayerHand().size());
    }

    @Test
    void PlayerDoesNotDrawOnSevenTest(){
        baccarat.setPlayerTotal(7);
        baccarat.setBankerTotal(2);
        int sizeBefore = baccarat.getPlayerHand().size();
        baccarat.drawPlayerThirdCard();
        assertEquals(sizeBefore, baccarat.getPlayerHand().size());
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
        int sizeBefore = baccarat.getBankerHand().size();
        baccarat.drawBankerThirdCard();
        assertEquals(sizeBefore, baccarat.getBankerHand().size());
    }

    @Test
    void BankerDoesNotDrawOnSevenWhenPlayerStandTest(){
        baccarat.setPlayerTotal(6);
        baccarat.setBankerTotal(7);
        int sizeBefore = baccarat.getBankerHand().size();
        baccarat.drawBankerThirdCard();
        assertEquals(sizeBefore, baccarat.getBankerHand().size());
    }

    @Test
    void BankerDrawOnFiveWhenPlayerStandTest(){
        baccarat.dealInitialCards();
        baccarat.setPlayerTotal(6);
        baccarat.setBankerTotal(5);
        int sizeBefore = baccarat.getBankerHand().size();
        baccarat.drawBankerThirdCard();
        assertEquals(sizeBefore+1, baccarat.getBankerHand().size());
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
        baccarat.setPlayerThird(new Card(Suit.SPADE, CardValues.FIVE));
        baccarat.setBankerTotal(2);
        int sizeBefore = baccarat.getBankerHand().size();
        baccarat.drawBankerThirdCard();
        assertEquals(sizeBefore+1, baccarat.getBankerHand().size());
    }

    @Test
    void BankerWithThreeDrawsWhenPlayerThirdIsNotEightTest(){
        baccarat.dealInitialCards();
        baccarat.setPlayerThird(new Card(Suit.HEART, CardValues.SEVEN));
        baccarat.setBankerTotal(3);
        int sizeBefore = baccarat.getBankerHand().size();
        baccarat.drawBankerThirdCard();
        assertEquals(sizeBefore+1, baccarat.getBankerHand().size());
    }

    @Test
    void BankerWithThreeStandsWhenPlayerThirdIsEightTest(){
        baccarat.dealInitialCards();
        baccarat.setPlayerThird(new Card(Suit.HEART, CardValues.EIGHT));
        baccarat.setBankerTotal(3);
        int sizeBefore = baccarat.getBankerHand().size();
        baccarat.drawBankerThirdCard();
        assertEquals(sizeBefore, baccarat.getBankerHand().size());
    }

    @Test
    void BankerWithFourDrawsWhenPlayerThirdIsFiveTest(){
        baccarat.dealInitialCards();
        baccarat.setPlayerThird(new Card(Suit.HEART, CardValues.FIVE));
        baccarat.setBankerTotal(4);
        int sizeBefore = baccarat.getBankerHand().size();
        baccarat.drawBankerThirdCard();
        assertEquals(sizeBefore+1, baccarat.getBankerHand().size());
    }

    @Test
    void BankerWithFourStandsWhenPlayerThirdIsNineTest(){
        baccarat.dealInitialCards();
        baccarat.setPlayerThird(new Card(Suit.HEART, CardValues.NINE));
        baccarat.setBankerTotal(4);
        int sizeBefore = baccarat.getBankerTotal();
        baccarat.drawBankerThirdCard();
        assertEquals(sizeBefore, baccarat.getBankerHand().size());
    }

    @Test
    void BankerWithFiveDrawsWhenPlayerThirdIsSixTest(){
        baccarat.dealInitialCards();
        baccarat.setPlayerThird(new Card(Suit.HEART, CardValues.SIX));
        baccarat.setBankerTotal(5);
        int sizeBefore = baccarat.getBankerHand().size();
        baccarat.drawBankerThirdCard();
        assertEquals(sizeBefore+1, baccarat.getBankerHand().size());
    }

    @Test
    void BankerWithFiveStandsWhenPlayerThirdIsThreeTest(){
        baccarat.dealInitialCards();
        baccarat.setPlayerThird(new Card(Suit.HEART, CardValues.THREE));
        baccarat.setBankerTotal(5);
        int sizeBefore = baccarat.getBankerHand().size();
        baccarat.drawBankerThirdCard();
        assertEquals(sizeBefore, baccarat.getBankerHand().size());
    }

    @Test
    void BankerWithSixDrawsWhenPlayerThirdIsSevenTest(){
        baccarat.dealInitialCards();
        baccarat.setPlayerThird(new Card(Suit.HEART, CardValues.SEVEN));
        baccarat.setBankerTotal(6);
        int sizeBefore = baccarat.getBankerHand().size();
        baccarat.drawBankerThirdCard();
        assertEquals(sizeBefore+1, baccarat.getBankerHand().size());
    }

    @Test
    void BankerWithSixStandsWhenPlayerThirdIsFiveTest(){
        baccarat.dealInitialCards();
        baccarat.setPlayerThird(new Card(Suit.HEART, CardValues.FIVE));
        baccarat.setBankerTotal(6);
        int sizeBefore = baccarat.getBankerHand().size();
        baccarat.drawBankerThirdCard();
        assertEquals(sizeBefore, baccarat.getBankerHand().size());
    }

    @Test
    void BankerWithSevenAlwaysStandsWhenPlayerDrewTest(){
        baccarat.dealInitialCards();
        baccarat.setPlayerThird(new Card(Suit.HEART, CardValues.SIX));
        baccarat.setBankerTotal(7);
        int sizeBefore = baccarat.getBankerHand().size();
        baccarat.drawBankerThirdCard();
        assertEquals(sizeBefore, baccarat.getBankerHand().size());
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
    void HandleBetReturnNegativeWhenBetOnDoesNotMatchesWinnerTest(){
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

    @Test
    void UpdateBalanceIncreasesBalanceOnWinTest(){
        Casino.balance = 1000;
        baccarat.updateBalance(200);
        assertEquals(1200, Casino.balance);
    }

    @Test
    void UpdateBalanceDecreasesBalanceOnLossTest(){
        Casino.balance = 1000;
        baccarat.updateBalance(-200);
        assertEquals(800, Casino.balance);
    }
}