package com.work.tdd.euler.util;

import com.google.common.collect.Lists;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.work.tdd.euler.util.NumberUtil.primeFactors;
import static org.testng.Assert.*;

public class NumberUtilTest {

    @Test
    public void testSplit() {
        assertEquals(NumberUtil.splitIntoDigits(3, 2), Arrays.asList(3));
        assertEquals(NumberUtil.splitIntoDigits(45, 5), Arrays.asList(45));
        assertEquals(NumberUtil.splitIntoDigits(45, 2), Arrays.asList(45));
        assertEquals(NumberUtil.splitIntoDigits(453123, 2), Arrays.asList(45, 31, 23));
        assertEquals(NumberUtil.splitIntoDigits(53123, 2), Arrays.asList(5, 31, 23));
        assertEquals(new BigDecimal("2.3455467").precision() - 1, 7);
    }

    @Test
    public void testPrimeFactors() {
        List<Long> factors = Lists.newArrayList(primeFactors(20));
        Collections.sort(factors);
        assertEquals(factors, Arrays.asList(2L, 2L, 5L));
    }

    @Test
    public void testSquareRoot() {
        assertTrue(NumberUtil.isPerfectSquare(new BigInteger("256")));
        assertFalse(NumberUtil.isPerfectSquare(new BigInteger("259")));
        assertFalse(NumberUtil.isPerfectSquare(new BigInteger("10081")));
    }
}
