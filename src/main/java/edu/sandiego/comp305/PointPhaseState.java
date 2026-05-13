package edu.sandiego.comp305;

public class PointPhaseState implements CrapsState {

    @Override
    public String handleRoll(final int roll,final Craps context) {
        if (roll == context.getPoint()) {
            context.setState(new ComingOutState());
            return "WIN";
        } else if (roll == 7) {
            context.setState(new ComingOutState());
            return "LOSE";
        } else {
            return "CONTINUE";
        }
    }
}
