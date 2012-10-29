package com.work.tdd.euler.medium;

import org.testng.annotations.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.google.common.collect.Lists.reverse;
import static java.util.Arrays.asList;
import static org.apache.commons.lang.StringUtils.join;
import static org.testng.Assert.*;

public class Problem55 {

    private static final Logger logger = Logger.getLogger(Problem55.class.getName());

    private static BigInteger reverseAndAdd(BigInteger n) {
        return n.add(new BigInteger(join(reverse(asList(Utility.digits(n.toString()))), "")));
    }

    private static boolean isPalindrome(BigInteger n) {
        return n.toString().equals(join(reverse(asList(Utility.digits(n.toString()))), ""));
    }

    public static boolean isLychrel(Integer n) {
        BigInteger count = new BigInteger(n.toString());
        for (int i = 0; i < 50; i++) {
            if (isPalindrome(count = reverseAndAdd(count))) {
                return true;
            }
        }
        return false;
    }

    public static List<Integer> answer() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 10000; i++) {
            if (!isLychrel(i)) {
                numbers.add(i);
            }
        }
        return numbers;
    }

    @Test
    public void testSimple() {
        List<Integer> answer = answer();
        assertEquals(answer.size(), 249);
        logger.info("Answer " + answer);
        assertTrue(isLychrel(47));
        assertFalse(isLychrel(10677));
        assertTrue(isPalindrome(new BigInteger("1441")));
        assertFalse(isPalindrome(new BigInteger("746")));
        assertEquals(reverseAndAdd(new BigInteger("1292")), new BigInteger("4213"));
    }
}
