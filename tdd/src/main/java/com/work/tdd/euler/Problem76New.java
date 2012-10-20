package com.work.tdd.euler;

import org.testng.annotations.Test;

import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public class Problem76New {

    private static final Logger logger = Logger.getLogger(Problem76New.class.getName());

    public static long explore(final int start, final int currentTotal, final int currentItems, final int requiredItems, final int requiredTotal) {
        logger.info("Start " + start + " currentTotal " + currentTotal + " currentItems " + currentItems);
        if (currentTotal == requiredTotal && currentItems == requiredItems) {
            return 1;
        }
        if (currentTotal >= requiredTotal || currentItems == requiredItems) {
            return 0;
        }
        long sum = 0;
        for (int i = start; i <= requiredTotal / requiredItems; i++) {
            for (int j = 0; j <= requiredItems - currentItems; j++) {
                int newCurrentTotal = currentTotal + (j * i);
                if (newCurrentTotal > requiredTotal) {
                    break;
                }
                if (i + 1 > requiredTotal / requiredItems) {
                    break;
                }
                sum += explore(i + 1, newCurrentTotal, currentItems + j, requiredItems, requiredTotal);
            }
        }
        return sum;
    }

    public static long explore(final int requiredItems, final int requiredTotal) {
        return explore(1, 0, 0, requiredItems, requiredTotal);
    }

    @Test
    public void testSimple() {
        assertEquals(explore(6, 12), 11);
    }
}

