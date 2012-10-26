package com.work.tdd.euler.util.fraction;

import org.testng.annotations.Test;

import static com.work.tdd.euler.util.fraction.Fractions.longFraction;
import static org.testng.Assert.*;

public class LongFractionTest {

    private Fraction<Long> f3by8 = longFraction(3, 8);
    private Fraction<Long> f4by7 = longFraction(4, 7);
    private Fraction<Long> f1by3 = longFraction(1, 3);
    private Fraction<Long> f1by2 = longFraction(1, 2);

    @Test
    public void testEquals() {
        assertNotEquals(f3by8, f4by7);
        assertEquals(f3by8, longFraction(3, 8));
        assertNotEquals(f3by8, longFraction(6, 16));
    }

    @Test
    public void testReduce() {
        assertTrue(f3by8.equals(longFraction(6, 16).reduce()));
        assertEquals(longFraction(13, 21), (longFraction(13, 21).reduce()));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testCompareAndEquivalent() {
        assertTrue(f1by3.compareTo(f1by2) < 0);
        assertTrue(f1by2.compareTo(f1by3) > 0);
        assertTrue(f1by2.compareTo(f1by2) == 0);
        Fraction f3by6 = f1by2.equivalent(6L);
        assertTrue(f1by2.compareTo(f3by6) == 0);
    }

    @Test
    public void testArithmetic() {
        assertEquals(f1by3.add(f1by2), longFraction(5, 6));
        assertEquals(f1by2.add(f1by2), longFraction(1, 1));
    }
}
