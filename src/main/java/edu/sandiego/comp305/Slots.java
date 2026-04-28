package edu.sandiego.comp305;

public class Slots implements Game{

    public SlotSymbols[] spin(){
        return new SlotSymbols[5];
    }

    public int calculatePayout(SlotSymbols symbols[], int betAmount){
        return 0;
    }

    @Override
    public int handleBet(int amount){
        return 0;
    }

    @Override
    public int playGame() {
        return 0;
    }

    @Override
    public void updateBalance(int amount){
    }
}
