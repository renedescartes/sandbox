package com.work.tdd.euler.hard;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static com.work.tdd.euler.util.NumberUtil.isPerfectSquare;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

public class Problem86 {
    private static final Logger logger = Logger.getLogger(Problem86.class.getName());

    private static Map<Long, Long> cache = new HashMap<>();

    private boolean isIntegralCuboidShortestDistance(long l, long b, long w) {
        return isPerfectSquare(Math.pow(w + b, 2) + Math.pow(l, 2));
    }

    public long cachedNumberOfCuboids(long n) {
        if (!cache.containsKey(n)) {
            cache.put(n, numberOfCuboids(n));
        }
        return cache.get(n);
    }

    private Long numberOfCuboids(long n) {
        if (n == 1) {
            return 0L;
        }
        long sum = 0;
        for (long length = 1; length <= n; length++) {
            for (long breadth = 1; breadth <= length; breadth++) {
                for (long height = 1; height <= breadth; height++) {
                    if (isIntegralCuboidShortestDistance(length, breadth, height)) {
                        logger.info(length + ", " + breadth + ", " + height);
                        sum++;
                    }
                }
            }
        }
        return sum;
    }

    @Test
    public void testBits() {
        assertTrue(isIntegralCuboidShortestDistance(6, 5, 3));
        assertFalse(isIntegralCuboidShortestDistance(7, 5, 3));
        assertFalse(isIntegralCuboidShortestDistance(3, 3, 2));
        assertEquals(cachedNumberOfCuboids(99), 1975);

    }
}
