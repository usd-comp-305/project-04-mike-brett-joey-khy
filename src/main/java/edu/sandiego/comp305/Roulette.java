package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Roulette implements Game{
    private RouletteWheel wheel;
    private static final int MAX_SPACES_ON_WHEEL = 37;
    private Random rng;

    public Roulette(Random rng){
        this.rng = rng;
        wheel = new RouletteWheel();
    }

    @Override
    public int handleBet(int amount) {
        // DO NEXT
        return 0;
    }

   @Override
    public int playGame() {
        return 0;
    }

    @Override
    public void updateBalance(int amount) {

    }

    public Color spinWheel() {
        int randomSelection = rng.nextInt(MAX_SPACES_ON_WHEEL);
        List<Color> colors = wheel.getWheel();

        return colors.get(randomSelection);
    }

    public int getUserWager() {
        return 0;
    }

}
