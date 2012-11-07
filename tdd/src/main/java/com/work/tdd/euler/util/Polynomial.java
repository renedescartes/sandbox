package com.work.tdd.euler.util;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

public class Polynomial {

    public static List<Integer> findPolynomialFunction(List<Tuple<Integer, Integer>> points) {
        checkArgument(points.size() > 0);
        List<Integer> powers = new ArrayList<>();

        checkState(powers.size() <= points.size() - 1);
        return powers;
    }
}
