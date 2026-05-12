package edu.sandiego.comp305;
import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack implements Game {
    private int playerTotal;
    private int dealerTotal;
    private int betAmount;
    private ArrayList<Card> playerHand = new ArrayList<>();;
    private ArrayList<Card> dealerHand = new ArrayList<>();;
    private ArrayList<Card> splitHand = new ArrayList<>();
    private Card dealerFaceUpCard;
    private ArrayList<Card> deck;
    private Scanner scanner;
    private boolean playerStands;
    boolean hasSplit;


    public Blackjack(Scanner scanner){
        this.scanner = scanner;
    }


    private void hit() {
        playerHand.add(DeckOfCards.dealCard(deck));
        newPlayerTotal();
    }

    private void split() {
        splitHand.add(playerHand.removeLast());
        hit();


    }

    private void doubleDown() {
        betAmount *= 2;
        playerHand.add(DeckOfCards.dealCard(deck));
        playerStands = true;
        newPlayerTotal();
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
                "4. Enter 1 to hit, 2 to stand, 3 to double, and 4 to split" +
                "How much would you like to bet:");

        betAmount = scanner.nextInt();

        dealStartingHand();
        newPlayerTotal();


        hasSplit = false;
        playerDecisions(hasSplit);
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

    void playerDecisions(boolean hasSplit){
        System.out.println("You have a " + playerTotal + "with a" + playerHand.getFirst().cardValue + "and a " +
                playerHand.getLast().getFaceValue() + "The dealer is showing a " + dealerFaceUpCard.getFaceValue() +
                "what would you like to do?");


        while(playerTotal < 21 && !playerStands){
            int userDecision = scanner.nextInt();
            if(userDecision == 1){
                hit();
            }
            else if(userDecision == 2){
                playerStands = true;
            }
            else if(userDecision == 3){
                doubleDown();
            }
            else if(userDecision == 4){
                if(playerHand.getFirst().getFaceValue() == playerHand.getLast().getFaceValue() && !hasSplit){
                    hasSplit = true;
                    split();
                }
                else{
                    System.out.println("Cards must be the same to split, try again");
                }
            }
            else{
                System.out.println("Invalid input, Enter 1 to hit, 2 to stand, 3 to double, and 4 to split\"");
            }
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

    public int getBetAmount(){
        return betAmount;
    }

    public boolean getPlayerStand(){
        return playerStands;
    }
    public ArrayList<Card> getSplitHand(){
        return splitHand;
    }
    void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }
}
