package com.work.tdd.euler.medium;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class Problem40 {
    public static int digit(int position) {
        return digit(position, 9, 2);
    }

    public static int digit(int position, int start, int index) {
        if (position <= 9) {
            return position;
        }
        int powerOfTen = (int) Math.pow(10, index - 1);
        int nextStep = ((9 * powerOfTen) * index) + start;
        if (position > nextStep) {
            return digit(position, nextStep, index + 1);
        } else {
            int difference = position - start;
            Integer bucketIndex = powerOfTen + ((difference - 1) / index);
            int finalIndex = (difference - 1) % index;
            return bucketIndex.toString().charAt(finalIndex) - '0';
        }
    }

    @Test
    public void testSimple() {
        assertEquals(digit(10), 1);
        assertEquals(digit(100), 5);
        assertEquals(digit(110), 6);
        assertEquals(digit(230), 1);
        assertEquals(digit(1000), 3);
        assertEquals(digit(10000), 7);
        assertEquals(digit(100000), 2);
        assertEquals(digit(1000000), 1);
        assertEquals(digit(14), 1);
        assertEquals(digit(7), 7);
        assertEquals(digit(9), 9);
        assertEquals(digit(189), 9);
        assertEquals(digit(188), 9);
        assertEquals(digit(187), 8);
        assertEquals(digit(190), 1);
        assertEquals(digit(192), 0);
        assertEquals(digit(2789), 6);
        assertEquals(digit(2889), 9);
        assertEquals(digit(2890), 1);
        assertEquals(digit(2893), 0);
        assertEquals(digit(2894), 1);
        System.out.println(digit(1) * digit(10) * digit(100) * digit(1000) * digit(10000) * digit(100000) * digit(1000000));
    }
}
