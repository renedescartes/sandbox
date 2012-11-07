package com.work.tdd.euler.util;

import org.testng.annotations.Test;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static com.work.tdd.euler.util.Matrix.matrixSolution;
import static java.util.Arrays.asList;
import static org.testng.Assert.assertEquals;

public class Polynomial {

    public static List<Double> findPolynomialFunction(List<Long> points) {
        checkArgument(points.size() > 0);
        if (points.size() == 1) {
            return asList((double) points.get(0));
        }
        Long[][] input = new Long[points.size()][points.size()];
        for (int row = 0; row < points.size(); row++) {
            for (int column = 0; column < points.size(); column++) {
                input[row][column] = (long) Math.pow(row + 1, points.size() - 1 - column);
            }
        }
        List<Double> answers = asList(matrixSolution(input, points.toArray(new Long[points.size()])));
        checkState(answers.size() <= points.size());
        return answers.subList(1, answers.size());
    }

    @Test
    public void testBits() {
        assertEquals(findPolynomialFunction(asList(1L, 8L, 15L)), asList(7.0, -6.0));
        assertEquals(findPolynomialFunction(asList(1L, 8L, 27L, 58L)), asList(6.0, -11.0, 6.0));
    }
}
