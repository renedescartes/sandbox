package com.work.tdd.euler.util;

import java.util.Arrays;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static com.work.tdd.euler.util.Matrix.matrixSolution;
import static java.util.Arrays.fill;

public class Polynomial {

    public static List<Double> findPolynomialFunction(List<Integer> points) {
        checkArgument(points.size() > 0);
        Integer[][] input = new Integer[points.size()][points.size()];
        for (int i = 0; i < points.size(); i++) {
            fill(input[i], 1);
        }
        Double[] answers = matrixSolution(input, points.toArray(new Integer[points.size()]));
        checkState(answers.length <= points.size() - 1);
        return Arrays.asList(answers);
    }
}
