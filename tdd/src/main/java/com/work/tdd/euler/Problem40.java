package com.work.tdd.euler;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class Problem40 {
    public static int getSequenceDigit(int position) {
        return getSequenceDigit(position, 9, 2);
    }

    public static int getSequenceDigit(int position, int start, int index) {
        if(position <= 9) {
            return position;
        }
        int step = ((9 * (int)Math.pow(10, index - 1)) * index) + start;
        if(position > step) {
            return getSequenceDigit(position, step, index + 1);
        } else {
            int difference = position - start;
            Integer bucketIndex = (int)Math.pow(10, index - 1) + ((difference - 1)/index);
            int finalIndex = (difference - 1)% index;
            return bucketIndex.toString().charAt(finalIndex) - '0';
        }
    }

    @Test
    public void testSimple() {
        assertEquals(getSequenceDigit(14), 1);
        assertEquals(getSequenceDigit(7), 7);
        assertEquals(getSequenceDigit(9), 9);
        assertEquals(getSequenceDigit(189), 9);
        assertEquals(getSequenceDigit(188), 9);
        assertEquals(getSequenceDigit(187), 8);
        assertEquals(getSequenceDigit(190), 1);
        assertEquals(getSequenceDigit(192), 0);
        assertEquals(getSequenceDigit(2789), 6);
        assertEquals(getSequenceDigit(2889), 9);
        assertEquals(getSequenceDigit(2890), 1);
        assertEquals(getSequenceDigit(2893), 0);
        assertEquals(getSequenceDigit(2894), 1);
    }
}
