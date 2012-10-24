package com.work.tdd.euler;

import com.work.tdd.euler.util.fraction.Fraction;
import org.testng.annotations.Test;

import static com.work.tdd.euler.Utility.digits;
import static com.work.tdd.euler.Utility.summation;
import static com.work.tdd.euler.util.fraction.Fractions.longFraction;
import static org.testng.Assert.assertEquals;

public class Problem65 {

    public static int sequenceNumber(int index) {
        if(index == 0) {
            return 2;
        }
        if(index % 3 == 2) {
            return 2 * ((index/3) + 1);
        }
        return 1;
    }

    public static Fraction computeFraction(int term) {
        if(term == 1) {
            return longFraction(sequenceNumber(0));
        }
        Fraction f = longFraction(sequenceNumber(term - 1));
        for(int i = term - 2; i >= 0; i--) {
            f = longFraction(sequenceNumber(i)).add(f.reciprocal());
        }
        return f;
    }

    public static int digitSum(int term) {
        return summation(digits("" + computeFraction(term).numerator()));
    }

    @Test
    public void testSimple() {
        assertEquals(digitSum(10), 17);
        assertEquals(digitSum(100), 17);
    }

    @Test
    public void testBits() {
        int count = 1;
        while(count <= 20) {
            System.out.println(computeFraction(count++));
        }
    }
}
