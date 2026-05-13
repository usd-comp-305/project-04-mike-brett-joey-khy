package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.List;

public class RouletteWheel {
    private static final int MAX_SPACES_ON_WHEEL = 37;

    private List<Color> wheel;

    public RouletteWheel(){
        wheel = new ArrayList<>();
        createWheel();
    }

    public List<Color> getWheel() {

        return wheel;
    }

    public void createWheel(){
        wheel.add(Color.GREEN);
        for(int i = 0; i < MAX_SPACES_ON_WHEEL/2; i++){
            wheel.add(Color.RED);
            wheel.add(Color.BLACK);
        }
    }
}
