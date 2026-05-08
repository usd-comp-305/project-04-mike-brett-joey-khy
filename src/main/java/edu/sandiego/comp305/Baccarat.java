package edu.sandiego.comp305;

public class Baccarat implements Game{
    private int playerTotal;
    private int bankerTotal;
    private DeckOfCards deck;

    public Baccarat(){}

    public void dealInitialCards(){
        playerTotal = (getCardValue(deck.dealCard()) + getCardValue(deck.dealCard())) % 10;
        bankerTotal = (getCardValue(deck.dealCard()) + getCardValue(deck.dealCard())) % 10;
    }

    public void drawPlayerThirdCard(){}

    public void drawBankerThirdCard(){}

    public String determineWinner(){
        return "";
    }

    public static int getCardValue(Card card){
        String facevalue = card.getFaceValue();
        switch (facevalue){
            case "A": return 1;
            case "K": case "Q": case "J": case "10": return 0;
            default: return Integer.parseInt(facevalue);
        }
    }

    public boolean isNatural(){
        return (playerTotal>=8 || bankerTotal >= 8);
    }

    @Override
    public int handleBet(int amount){ return 0; }

    @Override
    public void playGame(){}

    @Override
    public void updateBalance(int amount){}

    public int getPlayerTotal(){return playerTotal;}
    public int getBankerTotal(){return bankerTotal;}
    public void setPlayerTotal(int playerTotal){this.playerTotal=playerTotal;}
    public void setBankerTotal(int bankerTotal){this.bankerTotal=bankerTotal;}
}
