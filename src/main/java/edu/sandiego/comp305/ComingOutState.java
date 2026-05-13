package edu.sandiego.comp305;

public class ComingOutState implements CrapsState {

    @Override
    public String handleRoll(final int roll,final Craps context) {
        switch (roll) {
            case 7:
            case 11:
                return "WIN";
            case 2:
            case 3:
            case 12:
                return "LOSE";
            default:
                context.setPoint(roll);
                context.setState(new PointPhaseState());
                return "POINT_SET";
        }
    }
}
