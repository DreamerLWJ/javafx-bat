package org.example.model;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author 罗文俊
 * 2021/12/8
 */
public class State {
    private double[] angle = new double[16];

    Stack<double[]> angleHistory = new Stack<>();

    public void changeAngle(double[] angle) {
        // 入栈
        angleHistory.push(angle);
        this.angle = Arrays.copyOf(angle, 16);
    }

    public double[] getAngle() {
        return Arrays.copyOf(angle, 16);
    }

    public void revert() {
        // 弹出栈顶
        this.angle = angleHistory.pop();
    }
}
