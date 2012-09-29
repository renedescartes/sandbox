package com.ekanathk.tdd.euler;

import org.testng.annotations.Test;

import java.util.logging.Logger;

import static com.ekanathk.tdd.euler.Utility.isValidIndex;
import static com.ekanathk.tdd.euler.Utility.properDivisors;
import static com.ekanathk.tdd.euler.Utility.summation;

/**
 * User: EkanathK
 * Date: 29/09/12
 */
public class Problem21 {

    private static final Logger logger = Logger.getLogger(Problem21.class.getName());

    public static int countAmicable(Integer[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            if(isValidIndex(array, array[i]) && array[array[i]] == i) {
                if(i != array[i]) {
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
        for(int i = 0; i < array.length; i++) {
            array[i] = summation(properDivisors(i));
        }
        System.out.println(countAmicable(array));
    }
}
