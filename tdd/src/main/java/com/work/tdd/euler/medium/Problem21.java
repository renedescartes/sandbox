package com.work.tdd.euler.medium;

import org.testng.annotations.Test;

import java.util.logging.Logger;

/**
 * User: renedescartes
 * Date: 29/09/12
 */
public class Problem21 {

    private static final Logger logger = Logger.getLogger(Problem21.class.getName());

    public static int countAmicable(Integer[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            if (Utility.isValidIndex(array, array[i]) && array[array[i]] == i) {
                if (i != array[i]) {
                    logger.info("Element " + (i) + " is amicable with " + array[i]);
                    sum += i;
                }
            }
        }
        return sum;
    }

    @Test
    public void testSimple() {
        Integer[] array = new Integer[10001];
        for (int i = 0; i < array.length; i++) {
            array[i] = Utility.summation(Utility.properDivisors(i));
        }
        System.out.println(countAmicable(array));
    }
}
