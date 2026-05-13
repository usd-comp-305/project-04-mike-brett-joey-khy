package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Baccarat implements Game{
    private int playerTotal;

    private int bankerTotal;

    private ArrayList<Card> deck;

    private String betOn;

    private Card playerThird;

    private Card bankerThird;

    private List<Card> playerHand;

    private List<Card> bankerHand;

    private final Scanner scanner;

    public Baccarat(final Scanner scanner){
        this.scanner = scanner;
        this.deck = DeckOfCards.createDeckOfCards();
        this.deck = DeckOfCards.shuffleDeck(deck);
        this.playerTotal = 0;
        this.bankerTotal = 0;
        this.betOn = "";
        this.playerThird = null;
        this.bankerThird = null;
        this.playerHand = new ArrayList<>();
        this.bankerHand = new ArrayList<>();
    }

    private int dealHand(final List<Card> hand){
        final Card card1 = DeckOfCards.dealCard(deck);
        final Card card2 = DeckOfCards.dealCard(deck);
        hand.add(card1);
        hand.add(card2);
        return (getCardValue(card1)+getCardValue(card2)) % 10;
    }

    private Card drawCard(final List<Card> hand,final int currentTotal){
        final Card card = DeckOfCards.dealCard(deck);
        hand.add(card);
        return card;
    }

    private int addCardValue(final int currentTotal,final Card card){
        return (currentTotal + getCardValue(card)) % 10;
    }

    public void dealInitialCards(){
        playerTotal = dealHand(playerHand);
        bankerTotal = dealHand(bankerHand);
    }

    public void drawPlayerThirdCard(){
        if (isNatural()){
            return;
        }
        if (playerTotal <= 5){
            playerThird = drawCard(playerHand, playerTotal);
            playerTotal = addCardValue(playerTotal, playerThird);
        }
    }

    public void drawBankerThirdCard() {
        if (isNatural()) {
            return;
        }
        if (playerThird == null) {
            if (bankerTotal <= 5) {
                bankerThird = drawCard(bankerHand, bankerTotal);
                bankerTotal = addCardValue(bankerTotal, bankerThird);
            }
        } else {
            if (shouldBankerDraw()) {
                bankerThird = drawCard(bankerHand, bankerTotal);
                bankerTotal = addCardValue(bankerTotal, bankerThird);
            }
        }
    }

    private boolean shouldBankerDraw(){
        final int playerThirdValue = getCardValue(playerThird);
        switch (bankerTotal) {
            case 0: case 1: case 2:
                return true;
            case 3:
                return (playerThirdValue!=8);
            case 4:
                return (playerThirdValue >= 2 && playerThirdValue <= 7);
            case 5:
                return (playerThirdValue >= 4 && playerThirdValue <= 7);
            case 6:
                return (playerThirdValue >= 6 && playerThirdValue <= 7);
            default:
                return false;
        }
    }

    public String determineWinner(){
        if (playerTotal > bankerTotal){
            return "player";
        } else if (bankerTotal>playerTotal){
            return "banker";
        } else {
            return "tie";
        }
    }

    public static int getCardValue(final Card card){
        final CardValues cardvalue = card.getFaceValue();
        if (cardvalue == CardValues.ACE) {
            return 1;
        } else if (cardvalue.getCardValue() >= 10) {
            return 0;
        }
        return cardvalue.getCardValue();
    }

    public boolean isNatural(){

        return (playerTotal>=8 || bankerTotal >= 8);
    }

    @Override
    public int handleBet(final int amount){
        final String winner = determineWinner();
        if (winner.equals(betOn)){
            if (betOn.equals("tie")){
                return amount*8;
            }
            return amount;
        }
        return -amount;
    }

    public void printHand(final String option,
                          final List<Card> hand,final int total){
        System.out.print(option + " hand :");
        for (int i = 0; i < hand.size(); i++){
            System.out.print(hand.get(i).getFaceValue()
                    + " of " + hand.get(i).getSuit());
            if (i < hand.size() - 1){
                System.out.print(", ");
            }
        }
        System.out.println("    Total of " + total);
    }

    private void printThirdCardResult(final String label,
                                      final Card third,final int total) {
        if (third != null) {
            System.out.println(label + " draws: " +
                    third.getFaceValue() + " of " + third.getSuit());
            System.out.println(label + " total: " + total);
        } else {
            System.out.println(label + " stands");
        }
    }

    @Override
    public void playGame(){
        System.out.println("Welcome to Baccarat. Enter who you " +
                "would like to bet on (player/banker/tie): ");
        betOn = scanner.next().toLowerCase();
        System.out.println("Enter your bet amount (0 to exit the game) : ");
        final int bet = scanner.nextInt();
        if (bet == 0){
            System.out.println("Exiting Baccarat");
            return;
        }
        if (bet < 0){
            throw new IllegalArgumentException("Bet " +
                    "amount must be at least $1");
        }
        dealInitialCards();
        System.out.println("Initial Cards:");
        printHand("Player", playerHand, playerTotal);
        printHand("Banker", bankerHand, bankerTotal);
        if (isNatural()){
            System.out.println("\n Natural! No third cards ");
        } else {
            drawPlayerThirdCard();
            printThirdCardResult("Player",playerThird, playerTotal);
            drawBankerThirdCard();
            printThirdCardResult("Banker",bankerThird,bankerTotal);
        }
        System.out.println("\n \n Winner: " + determineWinner());
        System.out.println("You bet on: " + betOn);
        final int netWin = handleBet(bet);
        updateBalance(netWin);
    }

    @Override
    public void updateBalance(final int amount){
        Casino.addToBalance(amount);
        if (amount > 0) {
            System.out.println("Game result: +" + amount);
        } else {
            System.out.println("Game result: " + amount);
        }
    }

    public int getPlayerTotal(){
        return playerTotal;
    }

    public int getBankerTotal(){
        return bankerTotal;
    }

    public void setPlayerTotal(final int playerTotal){
        this.playerTotal=playerTotal;
    }

    public void setBankerTotal(final int bankerTotal){
        this.bankerTotal=bankerTotal;
    }

    public void setBetOn(final String betOn){
        this.betOn = betOn;
    }

    public void setPlayerThird(final Card card){
        this.playerThird = card;
    }

    public List<Card> getPlayerHand(){
        return playerHand;
    }

    public List<Card> getBankerHand(){
        return bankerHand;
    }

}
