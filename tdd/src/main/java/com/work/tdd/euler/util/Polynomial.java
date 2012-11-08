package com.work.tdd.euler.util;

import java.text.DecimalFormat;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Lists.reverse;
import static com.work.tdd.euler.util.Matrix.matrixSolution;
import static java.util.Arrays.asList;

public class Polynomial {

    public static List<Double> findPolynomialFunction(List<Double> points) {
        checkArgument(points.size() > 0);
        if (points.size() == 1) {
            return asList((double) points.get(0));
        }
        Double[][] input = new Double[points.size()][points.size()];
        for (int row = 0; row < points.size(); row++) {
            for (int column = 0; column < points.size(); column++) {
                input[row][column] = Math.pow(row + 1, points.size() - 1 - column);
            }
        }
        List<Double> answers = asList(matrixSolution(input, points.toArray(new Double[points.size()])));
        checkState(answers.size() <= points.size());
        return answers;
    }

    public static Double evaluatePolynomial(List<? extends Number> polynomial, Long n) {
        Double sum = 0d;
        List<? extends Number> reverse = reverse(polynomial);
        for (int i = 0; i < reverse.size(); i++) {
            double d = reverse.get(i).doubleValue();
            sum += (d * Math.pow(n, i));
        }
        return sum;
    }

    public static String prettyPrint(List<? extends Number> polynomial) {
        DecimalFormat twoDigit = new DecimalFormat("#,##0.00");
        StringBuilder b = new StringBuilder("");
        for (int i = 0; i < polynomial.size(); i++) {
            int power = polynomial.size() - 1 - i;
            String exponent = power == 0 ? "" : (power == 1 ? " n" : " n^" + power);
            String l = polynomial.get(i).doubleValue() >= 0 ? " +" + twoDigit.format(polynomial.get(i)) : " " + twoDigit.format(polynomial.get(i));
            b.append(l).append(exponent);
        }
        return b.toString();
    }
}
