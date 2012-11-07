package com.work.tdd.euler.util;

import java.util.Arrays;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static com.work.tdd.euler.util.Matrix.matrixSolution;

public class Polynomial {

    public static List<Double> findPolynomialFunction(List<Long> points) {
        checkArgument(points.size() > 0);
        Long[][] input = new Long[points.size()][points.size()];
        for (int row = 0; row < points.size(); row++) {
            for (int column = 0; column < points.size(); column++) {
                input[row][column] = (long) Math.pow(row + 1, points.size() - 1 - column);
            }
        }
        Double[] answers = matrixSolution(input, points.toArray(new Long[points.size()]));
        checkState(answers.length <= points.size() - 1);
        return Arrays.asList(answers);
    }
}
