package com.work.tdd.euler.medium;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.logging.Logger;

public class Problem52 {

    private static final Logger logger = Logger.getLogger(Problem52.class.getName());

    public static long findNumber() {
        long count = 1;
        while (!isAllProductsPermuted(count)) {
            logger.info("count = " + count);
            count++;
        }
        return count;
    }

    private static boolean isAllProductsPermuted(long number) {
        return arePermutations(number, 2 * number, 3 * number, 4 * number, 5 * number, 6 * number);
    }

    private static boolean arePermutations(Long... numbers) {
        Long first = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            Long number = numbers[i];
            if (!isPermutation(first, number)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isPermutation(Long n1, Long n2) {
        Integer[] digits1 = Utility.digits(n1);
        Arrays.sort(digits1);
        Integer[] digits2 = Utility.digits(n2);
        Arrays.sort(digits2);
        return Arrays.equals(digits1, digits2);
    }

    @Test
    public void testSimple() {
        System.out.println(findNumber());
    }
}
