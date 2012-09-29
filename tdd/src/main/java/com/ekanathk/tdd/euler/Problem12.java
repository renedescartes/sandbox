package com.ekanathk.tdd.euler;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: EkanathK
 * Date: 29/09/12
 * Time: 14:36
 * To change this template use File | Settings | File Templates.
 */
public class Problem12 {

    public static int numOfDivisors(int n) {
        int count = 0;
        if(n < 1) {
            throw new IllegalArgumentException("Please enter a number greater than or equal to 1");
        }
        if( n == 1) {
            return 1;
        }
        for(int i = 1; i <= Math.sqrt(n); i++) {
            if(n % i == 0) {
                count += 2;
            }
        }
        return count;
    }

    public static int triangleNumber(int index) {
        return index * (index + 1) / 2;
    }

    public static int triangleNumWithDivisors(int numOfDivisors) {
        int index = 1;
        while(numOfDivisors(triangleNumber(index)) <= numOfDivisors) {
            index++;
        }
        return triangleNumber(index);
    }

    @DataProvider(name = "problem12")
    private Object[][] data() {
        return new Object[][] {{1, 1}, {3, 2}, {6, 4}, {21, 4}, {28, 6} };
    }

    @Test(dataProvider = "problem12")
    public void testSimple(int n, int d) {
        assertEquals(d, numOfDivisors(n));
    }

    @Test
    public void testAnswer() {
        assertEquals(28, triangleNumWithDivisors(5));
        assertEquals(76576500, triangleNumWithDivisors(500));
    }
}
