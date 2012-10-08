package com.work.tdd.euler;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.work.tdd.euler.Utility.summation;
import static org.testng.Assert.assertEquals;

/**
 * User: renedescartes
 * Date: 07/10/12
 */
public class Problem30 {

    private static final Logger logger = Logger.getLogger(Problem30.class.getName());

    public static long sumOfNumbers(int power) {
        return summation(findNumbers(power));
    }

    private static Long[] findNumbers(int power) {
        List<Long> numbers = new ArrayList<>();
        long max = maximumNumber(power);
        for(long i = 2; i < max; i++) {
            if(equalSum(i, power)) {
                logger.info(pettyPrint(i, power));
                numbers.add(i);
            }
        }
        return numbers.toArray(new Long[numbers.size()]);
    }

    private static long maximumNumber(int power) {
        return (long) Math.pow(10, power+1);
    }

    private static boolean equalSum(Long number, int power) {
        String s = number.toString();
        long sum = 0;
        Integer[] digits = digits(number);
        for (int i = 0; i < digits.length; i++) {
            sum += Math.pow(digits[i], power);
        }
        return number.equals(sum);
    }

    private static Integer[] digits(Long number) {
        String s = number.toString();
        List<Integer> digits = new ArrayList<>();
        for(int i = 0; i < s.length(); i++) {
            digits.add(s.charAt(i) - '0');
        }
        return digits.toArray(new Integer[digits.size()]);
    }

    private static String pettyPrint(Long number, int power) {
        StringBuffer b = new StringBuffer();
        Integer[] digits = digits(number);
        for(Integer digit : digits) {
            b.append(digit).append(" ^ ").append(power).append(" + ");
        }
        return b.toString();
    }

    @Test
    public void testSimple() {
        //assertEquals(findNumbers(4), new Long[]{1634L, 8208L, 9474L});
        //assertEquals(sumOfNumbers(4), 19316L);
        //Long[] numbers = findNumbers(5);
        //System.out.println(Arrays.toString(numbers));
        assertEquals(sumOfNumbers(5), 443839L);
    }
}
