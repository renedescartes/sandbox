package com.ekanathk.tdd.euler;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * User: ekanathk
 * Date: 06/10/12
 */
public class Problem36 {

    private static String convertBinary(int number) {
        int n = number;
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

    @Test
    public void testSimple() {
        assertEquals("111", convertBinary(7));
        assertEquals("1001001001", convertBinary(585));
    }
}
