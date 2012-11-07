package com.work.tdd.euler.util;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Lists.reverse;
import static com.work.tdd.euler.util.Matrix.matrixSolution;
import static java.util.Arrays.asList;

public class Polynomial {

    public static List<Long> findPolynomialFunction(List<Long> points) {
        checkArgument(points.size() > 0);
        if (points.size() == 1) {
            return asList((long) points.get(0));
        }
        Long[][] input = new Long[points.size()][points.size()];
        for (int row = 0; row < points.size(); row++) {
            for (int column = 0; column < points.size(); column++) {
                input[row][column] = (long) Math.pow(row + 1, points.size() - 1 - column);
            }
        }
        List<Long> answers = asList(matrixSolution(input, points.toArray(new Long[points.size()])));
        checkState(answers.size() <= points.size());
        return answers;
    }

    public static Long evaluatePolynomial(List<Double> polynomial, Long n) {
        Long sum = 0L;
        List<Double> reverse = reverse(polynomial);
        for (int i = 0; i < reverse.size(); i++) {
            Double d = reverse.get(i);
            sum += (long) (d * Math.pow(n, i));
        }
        return sum;
    }

}
