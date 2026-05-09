package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Roulette {
    private RouletteWheel wheel;
    private static final int MAX_SPACES_ON_WHEEL = 37;

    public Roulette(){
        wheel = new RouletteWheel();
    }

    public int handleBet(int amount) {
        return 0;
    }

    public int playGame() {
        return 0;
    }

    public void updateBalance(int amount) {

    }

    public Color spinWheel() {
        Random rng = new Random();
        int randomSelection = rng.nextInt(MAX_SPACES_ON_WHEEL);
        List<Color> colors = wheel.getWheel();

        return colors.get(randomSelection);
    }

}
