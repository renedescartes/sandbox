package com.ekanathk.tdd.euler;

import org.testng.annotations.Test;

import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * User: ekanathk
 * Date: 06/10/12
 */
public class Problem36 {

    private static final Logger logger = Logger.getLogger(Problem36.class.getName());

    private static String convertBinary(long number) {
        if(number == 0) { return "0";}
        long n = number;
        StringBuffer b = new StringBuffer();
        while (n != 0) {
            b.append(n % 2);
            n = n/2;
        }
        return reverse(b);
    }
    private static boolean isPalindrome(String s) {
        return s.equals(reverse(s));
    }

    private static String reverse(CharSequence s) {
        StringBuffer b = new StringBuffer();
        for(int i = s.length() - 1; i >= 0; i--) {
            b.append(s.charAt(i));
        }
        return b.toString();
    }

    private static boolean isBothBasePalindrome(Long n) {
        return isPalindrome(n.toString()) && isPalindrome(convertBinary(n));
    }

    public static long sumOfPalindromeNumbers(Long n) {
        long sum = 0;
        for(long i = 1; i < n; i++) {
            if(isBothBasePalindrome(i)) {
                logger.info("Palindrome number " + i);
                sum += i;
            }
        }
        return sum;
    }

    @Test
    public void testSimple() {
        assertEquals("111", convertBinary(7));
        assertEquals("1001001001", convertBinary(585));
        assertEquals("1", convertBinary(1));
        assertEquals("0", convertBinary(0));
        assertEquals("1010", convertBinary(10));
        assertTrue(isBothBasePalindrome(585L));
    }

    @Test
    public void testComplex() {
        assertEquals(sumOfPalindromeNumbers(1000000L), 872187);
    }
}
