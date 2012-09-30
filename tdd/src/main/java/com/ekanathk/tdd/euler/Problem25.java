package com.ekanathk.tdd.euler;

import org.testng.annotations.Test;

import java.math.BigInteger;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

/**
 * User: EkanathK
 * Date: 30/09/12
 */
public class Problem25 {

    private static final Logger logger = Logger.getLogger(Problem25.class.getName());

    public static long fibanocciWithDigitsCount(int numOfDigits) {
        BigInteger number1 = new BigInteger("1"), number2 = new BigInteger("2");
        long index = 2;
        while(number2.toString().length() < numOfDigits) {
            index = index + 1;
            logger.info("Number of digits [" + number2.toString().length() + "] number [" + number2 + "] index [" + index + "]");
            BigInteger oldNumber1 = number1;
            number1 = number2;
            number2 = oldNumber1.add(number2);
        }
        return index + 1;
    }

    @Test
    public void testSimple() {
        assertEquals(7, fibanocciWithDigitsCount(2));
        assertEquals(12, fibanocciWithDigitsCount(3));
        assertEquals(4782, fibanocciWithDigitsCount(1000));
    }
}
