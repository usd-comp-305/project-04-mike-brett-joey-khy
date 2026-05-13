package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack implements Game {

    private static final int HIT = 1;
    private static final int STAND = 2;
    private static final int DOUBLE = 3;
    private static final int SPLIT = 4;

    private static final int BLACKJACK = 21;
    private static final int DEALER_STAND_THRESHOLD = 17;
    private static final int BLACKJACK_HAND_SIZE = 2;
    private static final double BLACKJACK_PAYOUT_MULTIPLIER = 1.5;
    private static final int ACE_HIGH_VALUE = 10;
    private static final int ACE_LOW_VALUE = 1;
    private static final int MIN_BET = 1;

    private final Scanner scanner;

    private ArrayList<Card> deck;
    private ArrayList<Card> playerHand = new ArrayList<>();
    private ArrayList<Card> dealerHand = new ArrayList<>();
    private ArrayList<Card> splitHand = new ArrayList<>();
    private Card dealerFaceUpCard;

    private int playerTotal;
    private int dealerTotal;
    private int splitTotal;
    private int betAmount;
    private boolean playerStands;
    private boolean hasSplit;



    public Blackjack(Scanner scanner) {
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


    public void playGame() {
        System.out.print(
                "Welcome to Blackjack!!! Below are the table specific rules:\n" +
                        "1. Dealer stands on soft 17\n" +
                        "2. You can only split once per deal\n" +
                        "3. Blackjack pays out 3:2\n" +
                        "4. No doubling down on a split\n" +
                        "5. Enter 1 to hit, 2 to stand, 3 to double, and 4 to split\n");

        System.out.print("How much would you like to bet: ");
        betAmount = scanner.nextInt();

        if (betAmount < MIN_BET) {
            throw new IllegalArgumentException("Bet amount must be at least $" + MIN_BET);
        }
        if (betAmount > Casino.balance) {
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

    void dealStartingHand() {
        playerHand.add(DeckOfCards.dealCard(deck));
        dealerFaceUpCard = DeckOfCards.dealCard(deck);
        dealerHand.add(dealerFaceUpCard);
        playerHand.add(DeckOfCards.dealCard(deck));
        dealerHand.add(DeckOfCards.dealCard(deck));
    }


    void playerDecisions(ArrayList<Card> hand, int handTotal) {
        while (handTotal < BLACKJACK && !playerStands) {
            System.out.println("You have a " + handTotal + " with a " + hand.getFirst().cardValue +
                    " and a " + hand.getLast().getFaceValue() +
                    " The dealer is showing a " + dealerFaceUpCard.getFaceValue() +
                    " what would you like to do?");

            int userDecision = scanner.nextInt();

            if (userDecision == HIT) {
                hit(hand);
                handTotal = calculateHandTotal(hand);
            }
            else if (userDecision == STAND) {
                playerStands = true;
            }
            else if (userDecision == DOUBLE && hand.size() == BLACKJACK_HAND_SIZE && !hasSplit) {
                doubleDown(hand);
                handTotal = calculateHandTotal(hand);
            }
            else if (userDecision == SPLIT) {
                if (hand.getFirst().getFaceValue() == hand.getLast().getFaceValue() && !hasSplit) {
                    hasSplit = true;
                    split();
                    handTotal = calculateHandTotal(hand);
                }
                else {
                    System.out.println("Cards must be the same to split, try again");
                }
            }
            else {
                System.out.println("Invalid input, Enter 1 to hit, 2 to stand, 3 to double, and 4 to split");
            }
        }
    }

    void dealerDecisions() {
        dealerTotal = calculateHandTotal(dealerHand);
        System.out.println("Dealer shows a " + dealerHand.getLast().getFaceValue() +
                " his new total is " + dealerTotal);

        while (dealerTotal < DEALER_STAND_THRESHOLD) {
            System.out.println("Dealer hits");
            hit(dealerHand);
            dealerTotal = calculateHandTotal(dealerHand);
            System.out.println("Dealer now has a " + dealerTotal);
        }

        if (dealerTotal > BLACKJACK) {
            System.out.println("Dealer busts with " + dealerTotal);
        }
    }


    int calculateHandTotal(ArrayList<Card> hand) {
        int total = 0;
        int numAces = 0;

        for (Card card : hand) {
            int cardValue = card.cardValue.getCardValue();
            if (cardValue == ACE_LOW_VALUE) {
                total += ACE_HIGH_VALUE;
                numAces++;
            }
            total += cardValue;
        }

        while (total > BLACKJACK && numAces > 0) {
            total -= ACE_HIGH_VALUE;
            numAces--;
        }

        return total;
    }

    public int handleBet(int bet) {
        int winAmount = 0;

        if (hasSplit) {
            if (splitTotal > BLACKJACK) {
                winAmount -= bet;
            }
            else if (splitTotal == BLACKJACK && splitHand.size() == BLACKJACK_HAND_SIZE) {
                winAmount += (int) (bet * BLACKJACK_PAYOUT_MULTIPLIER);
            }
            else if (dealerTotal > BLACKJACK) {
                winAmount += bet;
            }
            else if (dealerTotal > splitTotal) {
                winAmount -= bet;
            }
            else if (splitTotal > dealerTotal) {
                winAmount += bet;
            }
        }

        if (playerTotal > BLACKJACK) {
            winAmount -= bet;
        }
        else if (playerTotal == BLACKJACK && playerHand.size() == BLACKJACK_HAND_SIZE) {
            winAmount += (int) (bet * BLACKJACK_PAYOUT_MULTIPLIER);
        }
        else if (dealerTotal > BLACKJACK) {
            winAmount += bet;
        }
        else if (dealerTotal > playerTotal) {
            winAmount -= bet;
        }
        else if (playerTotal > dealerTotal) {
            winAmount += bet;
        }

        return winAmount;
    }

    @Override
    public void updateBalance(int amountWonOrLost) {
        Casino.balance += amountWonOrLost;
    }


    public ArrayList<Card> getPlayerHand() { return playerHand; }
    public ArrayList<Card> getDealerHand() { return dealerHand; }
    public ArrayList<Card> getSplitHand() { return splitHand; }
    public int getBetAmount() { return betAmount; }
    public boolean getPlayerStand() { return playerStands; }

    void setDeck(ArrayList<Card> deck) { this.deck = deck; }
    void setPlayerTotal(int total) { this.playerTotal = total; }
    void setDealerTotal(int total) { this.dealerTotal = total; }
    void setSplitTotal(int total) { this.splitTotal = total; }
    void setHasSplit(boolean hasSplit) { this.hasSplit = hasSplit; }
}