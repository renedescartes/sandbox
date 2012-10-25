package com.work.tdd.euler.util;

import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;

public class NumberUtilTest {

    @Test
    public void testSplit() {
        assertEquals(NumberUtil.splitIntoDigits(3, 2), Arrays.asList(3));
        assertEquals(NumberUtil.splitIntoDigits(45, 5), Arrays.asList(45));
        assertEquals(NumberUtil.splitIntoDigits(45, 2), Arrays.asList(45));
        assertEquals(NumberUtil.splitIntoDigits(453123, 2), Arrays.asList(45, 31, 23));
        assertEquals(NumberUtil.splitIntoDigits(53123, 2), Arrays.asList(5, 31, 23));
    }
}
