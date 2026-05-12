package edu.sandiego.comp305;
import java.util.ArrayList;
import java.util.Scanner;


public class Blackjack implements Game {
    private int playerTotal;
    private int dealerTotal;
    private int betAmount;
    private ArrayList<Card> playerHand = new ArrayList<>();
    private ArrayList<Card> dealerHand = new ArrayList<>();
    private ArrayList<Card> splitHand = new ArrayList<>();
    private Card dealerFaceUpCard;
    private ArrayList<Card> deck;
    private Scanner scanner;
    private boolean playerStands;
    private boolean hasSplit;
    private int splitTotal;


    private final int HIT = 1;
    private final int STAND = 2;
    private final int DOUBLE = 3;
    private final int SPLIT = 4;






    public Blackjack(Scanner scanner){
        this.scanner = scanner;
    }




    private void hit(ArrayList<Card> hand) {
        hand.add(DeckOfCards.dealCard(deck));
    }


    private void split() {
        splitHand.add(playerHand.getLast());
        playerHand.removeLast();
        hit(playerHand);
        hit(splitHand);
    }


    private void doubleDown(ArrayList<Card> hand) {
        betAmount *= 2;
        hand.add(DeckOfCards.dealCard(deck));
        playerStands = true;
    }


    public int handleBet(int bet){
        int winAmount = 0;
        if(hasSplit){
            if(splitTotal > 21){
                winAmount -= bet;
            }
            else if (splitTotal == 21 && splitHand.size() == 2) {
                winAmount += (int) (bet*1.5);
            }
            else if(dealerTotal > 21){
                winAmount += bet;
            }
            else if(dealerTotal > splitTotal){
                winAmount -= bet;
            }
            else if(splitTotal > dealerTotal){
                winAmount += bet;
            }


        }
        if(playerTotal > 21){
            winAmount -= bet;
        }
        else if (playerTotal == 21 && playerHand.size() == 2) {
            winAmount += (int) (bet*1.5);
        }
        else if(dealerTotal > 21){
            winAmount += bet;
        }
        else if(dealerTotal > playerTotal){
            winAmount -= bet;
        }
        else if(playerTotal > dealerTotal){
            winAmount += bet;
        }
        return winAmount;
    }


    public void playGame() {
        System.out.print("Welcome to Blackjack!!! Below are the table specific rules:\n" +
                "1. Dealer stands on soft 17 \n" +
                "2. You can only split once per deal \n" +
                "3. Blackjack pays out 3:2 \n" +
                "4. No doubling down on a split \n" +
                "5. Enter 1 to hit, 2 to stand, 3 to double, and 4 to split \n");

            System.out.print("How much would you like to bet: ");
            betAmount = scanner.nextInt();

            if (betAmount < 1) {
                throw new IllegalArgumentException("Bet amount must be at least $1");
            }
            if (betAmount > Casino.balance){
                throw new IllegalArgumentException("Can not bet more then balance");
            }

            deck = DeckOfCards.createDeckOfCards();
            DeckOfCards.shuffleDeck(deck);

            dealStartingHand();
            playerTotal = calculateHandTotal(playerHand);


            hasSplit = false;
            playerDecisions(playerHand, playerTotal);
            playerTotal = calculateHandTotal(playerHand);


            if (hasSplit) {
                System.out.println("You are now playing your split hand");
                playerStands = false;
                splitTotal = calculateHandTotal(splitHand);
                playerDecisions(splitHand, splitTotal);
            }
            splitTotal = calculateHandTotal(splitHand);


            dealerDecisions();


            int result = handleBet(betAmount);
            System.out.println("The result was " + result);
            updateBalance(result);
            System.out.println("Your new balance is " + Casino.balance);

    }






    @Override
    public void updateBalance(int amountWonOrLost) {
        Casino.balance += amountWonOrLost;
    }


    void dealStartingHand(){
        playerHand.add(DeckOfCards.dealCard(deck));


        dealerFaceUpCard = DeckOfCards.dealCard(deck);
        dealerHand.add(dealerFaceUpCard);


        playerHand.add(DeckOfCards.dealCard(deck));


        dealerHand.add(DeckOfCards.dealCard(deck));


    }


    int calculateHandTotal(ArrayList<Card> hand) {
        int total = 0;
        int numAces = 0;
        for (Card card : hand) {
            int cardValue = card.cardValue.getCardValue();
            if (cardValue == 1) {
                total += 10;
                numAces++;
            }
            total += cardValue;
        }
        while (total > 21 && numAces > 0) {
            total -= 10;
            numAces--;
        }
        return total;
    }


    void playerDecisions(ArrayList<Card> hand, int handTotal){
        while(handTotal < 21 && !playerStands){
            System.out.println("You have a " + handTotal + " with a " + hand.getFirst().cardValue + " and a " +
                    hand.getLast().getFaceValue() + " The dealer is showing a " + dealerFaceUpCard.getFaceValue() +
                    " what would you like to do?");
            int userDecision = scanner.nextInt();
            if(userDecision == HIT){
                hit(hand);
                handTotal = calculateHandTotal(hand);
            }
            else if(userDecision == STAND){
                playerStands = true;
            }
            else if(userDecision == DOUBLE && hand.size() == 2 && !hasSplit){
                doubleDown(hand);
                handTotal = calculateHandTotal(hand);
            }
            else if(userDecision == SPLIT){
                if(hand.getFirst().getFaceValue() == hand.getLast().getFaceValue() && !hasSplit){
                    hasSplit = true;
                    split();
                    handTotal = calculateHandTotal(hand);
                }
                else{
                    System.out.println("Cards must be the same to split, try again");
                }
            }
            else{
                System.out.println("Invalid input, Enter 1 to hit, 2 to stand, 3 to double, and 4 to split");
            }
        }


    }


    void dealerDecisions(){
        dealerTotal = calculateHandTotal(dealerHand);
        System.out.println("Dealer shows a " + dealerHand.getLast().getFaceValue() +
                " his new total is " + dealerTotal);


        while(dealerTotal < 17){
            System.out.println("Dealer hits");
            hit(dealerHand);
            dealerTotal = calculateHandTotal(dealerHand);
            System.out.println("Dealer now has a " + dealerTotal);


        }
        if(dealerTotal > 21){
            System.out.println("Dealer busts with " + dealerTotal);
        }
    }


    public ArrayList<Card> getPlayerHand(){return playerHand;}
    public ArrayList<Card> getDealerHand() {return dealerHand;}
    public int getBetAmount(){return betAmount;}
    public boolean getPlayerStand(){return playerStands;}
    public ArrayList<Card> getSplitHand(){return splitHand;}
    void setDeck(ArrayList<Card> deck) {this.deck = deck;}
    void setPlayerTotal(int total) { this.playerTotal = total; }
    void setDealerTotal(int total) { this.dealerTotal = total; }
    void setSplitTotal(int total) { this.splitTotal = total; }
    void setHasSplit(boolean hasSplit) { this.hasSplit = hasSplit; }
}
