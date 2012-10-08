package com.work.tdd.euler;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * User: kannan
 * Date: 04/10/12
 */
public class Problem28 {

    public static int problem(int n) {
        if(n % 2 == 0) {
            throw new IllegalArgumentException("Cannot find solution for even numbers");
        }
        if(n == 1) {
            return 1;
        }
        return problem(n-2) + sum(generateSequence( n * n, (n-1), 4));
    }

    public static int[] generateSequence(int initialValue, int step, int numberOfValues) {
        int[] array = new int[numberOfValues];
        for(int i = 0; i < numberOfValues;i++) {
            array[i] = initialValue - (i * step);
        }
        return array;
    }

    public static int sum(int[] array) {
        int sum = 0;
        for(int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum;
    }

    @DataProvider(name = "square")
    private Object[][] quotientData() {
        return new Object[][] {
                {1, 1},
                {3, 25},
                {5, 101},
                {1001, 669171001}
        };
    }

    @Test(dataProvider = "square")
    public void testSimple(int n, int sum) {
        assertEquals(problem(n), sum);
    }
}
