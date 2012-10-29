package com.work.tdd.euler.medium;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class Problem76New {

    private static long[][] array = new long[1000][1000];

    public static long perm(int k, int n) {
        if (array[k][n] != 0) {
            return array[k][n];
        }
        if (k > n) {
            return 0;
        }
        if (k == n) {
            return 1;
        }
        long perm = perm(k, n - k) + perm(k + 1, n);
        array[k][n] = perm;
        return perm;
    }

    public static long answer(int n) {
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
        assertEquals(answer(100), 190569291);
    }
}

