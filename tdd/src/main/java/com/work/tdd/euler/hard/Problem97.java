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

    @Test
    public void testParts() {
        assertEquals(trimmed(new BigInteger("925"), 4).intValue(), 925);
        assertEquals(trimmed(new BigInteger("925632"), 4).intValue(), 5632);
    }
}
