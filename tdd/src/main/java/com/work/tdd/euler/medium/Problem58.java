package com.work.tdd.euler.medium;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.google.common.collect.Collections2.filter;
import static org.testng.Assert.assertEquals;

public class Problem58 {

    private static final Logger logger = Logger.getLogger(Problem58.class.getName());

    public static long lengthOfSpiral(double percent) {
        long numberOfPrimes = 0, totalNumber = 1, length = 1;
        double ratio = 1;
        while (ratio >= percent) {
            List<Long> newNumbers = sequences(length * length, (length + 2) * (length + 2), length + 1);
            numberOfPrimes += filter(newNumbers, Utility.primePredicate()).size();
            length = length + 2;
            totalNumber += 4;
            ratio = (double) numberOfPrimes / (double) totalNumber;
            logger.info("length = " + length + " newNumbers = " + newNumbers + " numberOfPrimes = " +
                    numberOfPrimes + " totalNumber = " + totalNumber + " ration = " + ratio);
        }
        return length;
    }

    private static List<Long> sequences(long start, long end, long step) {
        List<Long> sequences = new ArrayList<>();
        for (long i = start + step; i <= end; i += step) {
            sequences.add(i);
        }
        return sequences;
    }

    @Test
    public void testSimple() {
        assertEquals(lengthOfSpiral(0.10), 26241);
    }
}
