package com.work.tdd.euler.hard;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

import static com.work.tdd.euler.medium.Utility.properDivisors;
import static com.work.tdd.euler.medium.Utility.summation;

public class Problem95 {
    private static final Logger logger = Logger.getLogger(Problem95.class.getName());

    private static Integer[] amicableNumbers(int n, int MAX) {
        Set<Integer> set = new TreeSet<>();
        boolean complete = false;
        set.add(n);
        int summationOfDivisors = n;
        while (!complete) {
            summationOfDivisors = summation(properDivisors(summationOfDivisors));
            complete = (summationOfDivisors == n) || !set.add(summationOfDivisors) || summationOfDivisors >= MAX;
        }
        return (summationOfDivisors == n) ? set.toArray(new Integer[set.size()]) : new Integer[0];
    }

    public static int explore(int start, int MAX) {
        int[] chainLengths = new int[MAX + 1];
        int[] smallestInChain = new int[MAX + 1];
        chainLengths[1] = -1;
        for (int i = start; i < MAX; i++) {
            if (chainLengths[i] == 0) {
                Integer[] amicableNumbers = amicableNumbers(i, MAX);
                if (amicableNumbers.length == 0) {
                    chainLengths[i] = -1;
                    smallestInChain[i] = -1;
                } else {
                    logger.info("Inspecting [" + i + "] amicable numbers " + Arrays.toString(amicableNumbers));
                    for (Integer amicableNumber : amicableNumbers) {
                        chainLengths[amicableNumber] = amicableNumbers.length;
                        smallestInChain[amicableNumber] = amicableNumbers[0];
                    }
                }
            }
        }
        return 0;
    }

    @Test
    public void testSimple() {
        explore(1, 1_000_000);
    }
}
