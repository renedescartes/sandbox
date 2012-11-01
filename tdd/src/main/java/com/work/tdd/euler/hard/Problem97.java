package com.work.tdd.euler.hard;

import org.testng.annotations.Test;

import java.math.BigInteger;

import static org.testng.Assert.assertEquals;

public class Problem97 {

    private static BigInteger trimmed(BigInteger n, int length) {
        return n.toString().length() <= length ? n : new BigInteger(lastNChars(n.toString(), length));
    }

    private static String lastNChars(String n, int length) {
        return n.length() <= length ? n : n.substring(n.length() - length, n.length());
    }

    public static BigInteger findLastDigits(int a, int power, int length) {
        BigInteger powerOfTwo = BigInteger.valueOf(2);
        for (int i = 2; i <= power; i++) {
            powerOfTwo = trimmed(powerOfTwo.multiply(BigInteger.valueOf(2)), length);
        }
        return trimmed(powerOfTwo.multiply(BigInteger.valueOf(a)).add(BigInteger.ONE), length);
    }

    @Test
    public void testSimple() {
        assertEquals(findLastDigits(28433, 7830457, 10).toString(), "8739992577");
    }

    @Test
    public void testParts() {
        assertEquals(trimmed(new BigInteger("925"), 4).intValue(), 925);
        assertEquals(trimmed(new BigInteger("925632"), 4).intValue(), 5632);
    }
}
