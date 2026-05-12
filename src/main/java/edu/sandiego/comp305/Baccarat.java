package edu.sandiego.comp305;

public class Baccarat implements Game{
    private int playerTotal;
    private int bankerTotal;
    private DeckOfCards deck;

    public Baccarat(){}

    public void dealInitialCards(){}

    public void drawPlayerThirdCard(){}

    public void drawBankerThirdCard(){}

    public String determineWinner(){
        return "";
    }

    public int getCardValue(){
        return 0;
    }

    @Override
    public int handleBet(int amount)
    {return 0;}

    @Override
    public void playGame(){}

    @Override
    public void updateBalance(int amount){}
}
