package edu.sandiego.comp305;
import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack implements Game {
    private int playerTotal;
    private int dealerTotal;
    private int betAmount;
    private ArrayList<Card> playerHand = new ArrayList<>();
    private ArrayList<Card> dealerHand = new ArrayList<>();
    private Card dealerFaceUpCard;
    private ArrayList<Card> deck;
    private Scanner scanner;

    public Blackjack(Scanner scanner){
        this.scanner = scanner;
    }


    private void hit() {
    }

    private void split() {
    }

    private void doubleDown() {
    }

    public int handleBet(int bet) {
        return 1;
    }

    public void playGame() {
        deck = DeckOfCards.createDeckOfCards();
        DeckOfCards.shuffleDeck(deck);
        System.out.println("Welcome to Blackjack!!! Below are the table specific rules:\n " +
                "1. Dealer stands on soft 17. \n" +
                "2. You can only split once per deal. \n" +
                "3. Blackjack pays out 3:2 \n" +
                "How much would you like to bet:");

        betAmount = scanner.nextInt();

        dealStartingHand();
        newPlayerTotal();


    }

    @Override
    public void updateBalance(int amountWonOrLost) {
    }

    void dealStartingHand(){
        playerHand.add(DeckOfCards.dealCard(deck));

        dealerFaceUpCard = DeckOfCards.dealCard(deck);
        dealerHand.add(dealerFaceUpCard);

        playerHand.add(DeckOfCards.dealCard(deck));

        dealerHand.add(DeckOfCards.dealCard(deck));

    }

    void newPlayerTotal(){
        playerTotal = 0;
        int numAces = 0;
        for(Card card : playerHand){
            int cardValue = card.cardValue.getCardValue();
            if(cardValue == 1){
                playerTotal += 10;
                numAces++;
            }
            playerTotal += cardValue;
        }
        while(playerTotal > 21 && numAces > 0){
            playerTotal -= 10;
            numAces--;
        }


    }

    public ArrayList<Card> getPlayerHand(){
        return playerHand;
    }

    public ArrayList<Card> getDealerHand() {
        return dealerHand;
    }

    public int getPlayerTotal(){
        return playerTotal;
    }
}
