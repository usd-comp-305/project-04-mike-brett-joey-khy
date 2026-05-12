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
    private Scanner scanner;

    public Baccarat(Scanner scanner){
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

    public void dealInitialCards(){
        Card playerCard1 = DeckOfCards.dealCard(deck);
        Card bankerCard1 = DeckOfCards.dealCard(deck);
        Card playerCard2 = DeckOfCards.dealCard(deck);
        Card bankerCard2 = DeckOfCards.dealCard(deck);

        playerHand.add(playerCard1);
        bankerHand.add(bankerCard1);
        playerHand.add(playerCard2);
        bankerHand.add(bankerCard2);

        playerTotal = (getCardValue(playerCard1) + getCardValue(playerCard2)) % 10;
        bankerTotal = (getCardValue(bankerCard1) + getCardValue(bankerCard2)) % 10;

    }

    public void drawPlayerThirdCard(){
        if (isNatural()){
            return;
        }
        if (playerTotal <= 5){
            playerThird = DeckOfCards.dealCard(deck);
            playerHand.add(playerThird);
            playerTotal = (playerTotal + getCardValue(playerThird)) % 10;
        }
    }

    public void drawBankerThirdCard(){
        if (isNatural()){
            return;
        }
        if (playerThird == null) {
            if (bankerTotal <= 5 ) {
                bankerThird = DeckOfCards.dealCard(deck);
                bankerHand.add(bankerThird);
                bankerTotal = (bankerTotal + getCardValue(bankerThird)) % 10;
            }
        } else {
            int playerThirdCard = getCardValue(playerThird);
            boolean bankerDraw = false;
            switch (bankerTotal) {
                case 0: case 1: case 2:
                    bankerDraw = true;
                    break;
                case 3:
                    bankerDraw = (playerThirdCard != 8);
                    break;
                case 4:
                    bankerDraw = (playerThirdCard >= 2 && playerThirdCard <= 7);
                    break;
                case 5:
                    bankerDraw = (playerThirdCard >= 4 && playerThirdCard <= 7);
                    break;
                case 6:
                    bankerDraw = (playerThirdCard >= 6 && playerThirdCard <= 7);
                    break;
                case 7:
                    bankerDraw = false;
                    break;
                default:
                    bankerDraw = false;
                    break;
            }
            if (bankerDraw){
                bankerThird = DeckOfCards.dealCard(deck);
                bankerHand.add(bankerThird);
                bankerTotal = (bankerTotal + getCardValue(bankerThird)) % 10;
            }
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

    public static int getCardValue(Card card){
        CardValues cardvalue = card.getFaceValue();
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
    public int handleBet(int amount){
        String winner = determineWinner();
        if (winner.equals(betOn)){
            if (betOn.equals("tie")){
                return amount*8;
            }
            return amount;
        }
        return -amount;
    }

    public void printHand(String option, List<Card> hand, int total){
        System.out.print(option + " hand :");
        for (int i = 0; i < hand.size(); i++){
            System.out.print(hand.get(i).getFaceValue() + " of " + hand.get(i).getSuit());
            if (i < hand.size() - 1){
                System.out.print(", ");
            }
        }
        System.out.println("    Total of " + total);
    }

    @Override
    public void playGame(){
        System.out.println("Welcome to Baccarat. Enter who you would like to bet on (player/banker/tie): ");
        betOn = scanner.next().toLowerCase();
        System.out.println("Enter your bet amount (0 to exit the game) : ");
        int bet = scanner.nextInt();
        if (bet == 0){
            System.out.println("Exiting Baccarat");
            return;
        }
        if (bet < 0){
            throw new IllegalArgumentException("Bet amount must be at least $1");
        }
        dealInitialCards();
        System.out.println("Initial Cards:");
        printHand("Player", playerHand, playerTotal);
        printHand("Banker", bankerHand, bankerTotal);
        if (isNatural()){
            System.out.println("\n Natural! No third cards ");
        } else {
            drawPlayerThirdCard();
            if (playerThird != null){
                System.out.println("Player draws: " + playerThird.getFaceValue() + " of " + playerThird.getSuit());
                System.out.println("Player total: " + playerTotal);
            } else {
                System.out.println("Player stands ");
            }

            drawBankerThirdCard();
            if (bankerThird != null){
                System.out.println("Banker draws: " + bankerThird.getFaceValue() + " of " + bankerThird.getSuit());
                System.out.println("Banker total: " + bankerTotal);
            } else {
                System.out.println("Banker stands ");
            }
        }
        System.out.println("\n \n Winner: " + determineWinner());
        System.out.println("You bet on: " + betOn);
        int netWin = handleBet(bet);
        updateBalance(netWin);
    }

    @Override
    public void updateBalance(int amount){
        Casino.balance += amount;
        if (amount > 0) {
            System.out.println("Game result: +" + amount);
        } else {
            System.out.println("Game result: " + amount);
        }
    }

    public int getPlayerTotal(){return playerTotal;}
    public int getBankerTotal(){return bankerTotal;}
    public void setPlayerTotal(int playerTotal){this.playerTotal=playerTotal;}
    public void setBankerTotal(int bankerTotal){this.bankerTotal=bankerTotal;}
    public void setBetOn(String betOn){ this.betOn = betOn;}
    public void setPlayerThird(Card card){this.playerThird = card;}
    public List<Card> getPlayerHand(){return playerHand;}
    public List<Card> getBankerHand(){return bankerHand;}

}
