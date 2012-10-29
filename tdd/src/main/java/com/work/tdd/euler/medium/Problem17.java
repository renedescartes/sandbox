package com.work.tdd.euler.medium;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * User: renedescartes
 * Date: 29/09/12
 */
public class Problem17 {

    public static String numberToString(int n) {
        if (n > 1000 || n < 0) {
            throw new IllegalArgumentException("Number cannot be more than 1000 or less than 0");
        }
        if (map.containsKey(n) && n < 100) {
            return map.get(n);
        }
        if (n == 100 || n == 1000) {
            return "one " + map.get(n);
        }
        if (n < 100) {
            return numberLessThan100(n);
        }
        return numberBetween100And1000(n);
    }

    public static int stringLength(String n) {
        int count = 0;
        for (int i = 0; i < n.length(); i++) {
            if (Character.isAlphabetic(n.charAt(i))) {
                count++;
            }
        }
        return count;
    }

    private static String numberLessThan100(int n) {
        /** between 21 and 99*/
        int tens = (n / 10) * 10, ones = n % 10;
        return map.get(tens) + " " + map.get(ones);
    }

    private static String numberBetween100And1000(int n) {
        int hundreds = (n / 100), nonHundred = n - (hundreds * 100);
        String output = map.get(hundreds) + " hundred";
        if (nonHundred != 0) {
            output = output + " and " + numberToString(nonHundred);
        }
        return output;
    }

    @Test
    public void testSimple() {
        int count = 0;
        for (int i = 1; i <= 1000; i++) {
            count += stringLength(numberToString(i));
            System.out.println(numberToString(i) + " length " + stringLength(numberToString(i)));
        }
        System.out.println(count);
    }

    private static Map<Integer, String> map = new HashMap<>();

    static {
        map.put(0, "zero");
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");
        map.put(5, "five");
        map.put(6, "six");
        map.put(7, "seven");
        map.put(8, "eight");
        map.put(9, "nine");
        map.put(10, "ten");
        map.put(11, "eleven");
        map.put(12, "twelve");
        map.put(13, "thirteen");
        map.put(14, "fourteen");
        map.put(15, "fifteen");
        map.put(16, "sixteen");
        map.put(17, "seventeen");
        map.put(18, "eighteen");
        map.put(19, "nineteen");
        map.put(20, "twenty");
        map.put(30, "thirty");
        map.put(40, "forty");
        map.put(50, "fifty");
        map.put(60, "sixty");
        map.put(70, "seventy");
        map.put(80, "eighty");
        map.put(90, "ninety");
        map.put(100, "hundred");
        map.put(1000, "thousand");
    }
}
