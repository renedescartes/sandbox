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
            cache.put(n, recursiveNumberOfCuboids(n));
        }
        return cache.get(n);
    }

    private Long recursiveNumberOfCuboids(long length) {
        if (length == 1) {
            return 0L;
        }
        long sum = cachedNumberOfCuboids(length - 1);
        for (long breadth = 1; breadth <= length; breadth++) {
            for (long height = 1; height <= breadth; height++) {
                if (isIntegralCuboidShortestDistance(length, breadth, height)) {
                    logger.fine(length + ", " + breadth + ", " + height);
                    sum++;
                }
            }
        }
        return sum;
    }

    public long answer(long MAX) {
        long answer = 5;
        long cuboids;
        while ((cuboids = cachedNumberOfCuboids(answer)) < MAX) {
            logger.fine("Number [" + answer + "] Cuboids [" + cuboids + "]");
            answer++;
        }
        return answer;
    }

    @Test
    public void testBits() {
        assertTrue(isIntegralCuboidShortestDistance(6, 5, 3));
        assertFalse(isIntegralCuboidShortestDistance(7, 5, 3));
        assertFalse(isIntegralCuboidShortestDistance(3, 3, 2));
        assertEquals(cachedNumberOfCuboids(99), 1975);
        assertEquals(cachedNumberOfCuboids(100), 2060);
        assertEquals(answer(2000), 100);
        assertEquals(answer(1000000), 1818);
    }
}
