package com.work.tdd.euler;

import com.work.tdd.euler.util.fraction.Fraction;
import org.testng.annotations.Test;

import java.util.logging.Logger;

import static com.work.tdd.euler.Utility.digits;
import static com.work.tdd.euler.Utility.summation;
import static com.work.tdd.euler.util.fraction.Fractions.bigIntegerFraction;
import static org.testng.Assert.assertEquals;

public class Problem65 {
    private static final Logger logger = Logger.getLogger(Problem65.class.getName());

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
            return bigIntegerFraction(sequenceNumber(0));
        }
        Fraction f = bigIntegerFraction(sequenceNumber(term - 1));
        for(int i = term - 2; i >= 0; i--) {
            f = bigIntegerFraction(sequenceNumber(i)).add(f.reciprocal());
        }
        logger.info("Term [" + term  +"] fraction [" + f  + "] decimal value [" + f.decimalValue() + "]");
        return f;
    }

    public static int digitSum(int term) {
        return summation(digits("" + computeFraction(term).numerator()));
    }

    @Test
    public void testSimple() {
        assertEquals(digitSum(10), 17);
        assertEquals(digitSum(100), 272);
    }

    @Test
    public void testBits() {
        int count = 1;
        while(count <= 20) {
            computeFraction(count++);
        }
    }
}
