package com.work.tdd.euler.medium;

import org.testng.annotations.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public class Problem63 {

    private static final Logger logger = Logger.getLogger(Problem63.class.getName());

    public static Map<String, String> nDigitNPower(int digits) {
        Map<String, String> numbers = new TreeMap<>();
        BigInteger end = end(digits);
        BigInteger root = new BigInteger("1");
        BigInteger power = root.pow(digits);
        while (power.compareTo(end) <= 0) {
            if (power.toString().length() == digits) {
                numbers.put(root.toString(), power.toString());
            }
            root = root.add(new BigInteger("1"));
            power = root.pow(digits);
        }
        return numbers;
    }

    private static BigInteger start(int digits) {
        return new BigInteger("10").pow(digits - 1);
    }

    private static BigInteger end(int digits) {
        return start(digits + 1).subtract(new BigInteger("1"));
    }

    public static long answer() {
        long count = 0;
        int digits = 1;
        while (digits <= breakingPoint()) {
            Map<String, String> output = nDigitNPower(digits);
            count += output.size();
            logger.info("Digit [" + digits + "] output " + output + " totalCount [" + count + "]");
            digits++;
        }
        return count;
    }

    private static int breakingPoint() {
        int count = 1;
        BigInteger nine = new BigInteger("9");
        while (nine.pow(count).toString().length() == count) {
            count++;
        }
        return count;
    }

    @Test
    public void testSimple() {
        assertEquals(answer(), 49);
    }

    @Test
    public void testBits() {
        assertEquals(22, breakingPoint());
        assertEquals(nDigitNPower(1).size(), 9);
        assertEquals(nDigitNPower(2).size(), 6);
        assertEquals(nDigitNPower(5).size(), 3);
        assertEquals(nDigitNPower(5).values(), Arrays.asList("16807", "32768", "59049"));
    }
}
