package com.work.tdd.euler;

import org.testng.annotations.Test;

import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public class Problem76New {

    private static final Logger logger = Logger.getLogger(Problem76New.class.getName());

    public static long perm(long k, long n) {
        if (k > n) {
            return 0;
        }
        if (k == n) {
            return 1;
        }
        return perm(k, n - k) + perm(k + 1, n);
    }

    public static long answer(long n) {
        return perm(1, n) - 1;
    }

    @Test
    public void testSimple() {
        assertEquals(answer(5), 6);
        assertEquals(answer(6), 10);
        assertEquals(answer(7), 14);
        assertEquals(answer(8), 21);
        assertEquals(answer(10), 41);
        assertEquals(answer(12), 76);
        assertEquals(answer(100), 76);
    }
}

