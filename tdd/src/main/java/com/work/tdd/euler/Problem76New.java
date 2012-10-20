package com.work.tdd.euler;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class Problem76New {

    public static long explore(final int start, final int currentTotal, final int currentItems, final int requiredItems, final int requiredTotal) {
        if (currentTotal == requiredTotal && currentItems == requiredItems) {
            return 1;
        }
        if (currentTotal >= requiredTotal || currentItems == requiredItems) {
            return 0;
        }
        long sum = 0;
        for (int i = start; i < requiredTotal; i++) {
            if (i * (requiredItems - currentItems) > requiredTotal) {
                break;
            }
            for (int j = 0; j <= requiredItems - currentItems; j++) {
                sum += explore(i + 1, currentTotal + (j * i), currentItems + j, requiredItems, requiredTotal);
            }
        }
        return sum;
    }

    public static long explore(final int requiredItems, final int requiredTotal) {
        return explore((int) Math.ceil((double) requiredTotal / (double) requiredItems), 0, 0, requiredItems, requiredTotal);
    }

    @Test
    public void testSimple() {
        assertEquals(explore(6, 12), 11);
    }
}

