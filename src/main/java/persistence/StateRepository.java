package persistence;

import model.State;

/**
 * @author 罗文俊
 * 2021/12/8
 */
public class StateRepository {
    private final static State state = new State();

    static {
        state.changeAngle(new double[]{
                72.0, 109.5, 69.25,
                204.5, 180, 54.5,
                193, 192, 30.5,
                188.5, 147, 6,
                164, 61, 145.5,
                142.25
        });
    }

    public static void changeAngle(double[] angles) {
        state.changeAngle(angles);
    }

    public static double[] getAngles() {
        return state.getAngle();
    }
}
