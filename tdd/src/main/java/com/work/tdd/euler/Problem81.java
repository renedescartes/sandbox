package com.work.tdd.euler;

import com.google.common.base.Preconditions;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public class Problem81 {

    private static final Logger logger = Logger.getLogger(Problem81.class.getName());

    public static long[][] maximalSum(int[][] array) {
        long[][] sumArray = new long[array.length][array[0].length];
        for (int i = 0; i < sumArray.length; i++) {
            for (int j = 0; j < sumArray[i].length; j++) {
                long leftSum = (j == 0) ? Long.MAX_VALUE : sumArray[i][j - 1];
                long upSum = (i == 0) ? Long.MAX_VALUE : sumArray[i - 1][j];
                if (i == 0 && j == 0) {
                    sumArray[0][0] = array[0][0];
                } else {
                    sumArray[i][j] = Math.min(leftSum, upSum) + array[i][j];
                }

            }
        }
        return sumArray;
    }

    public static long answer(int[][] question) {
        long[][] array = maximalSum(question);
        return array[array.length - 1][array[0].length - 1];
    }

    private static void printArray(long[][] array) {
        for (long[] line : array) {
            logger.info(Arrays.toString(line));
        }
    }

    private static int[][] readArray(String resource, int expectedLength) {
        List<String> strings = Utility.readFile(resource);
        Preconditions.checkArgument(strings.size() == expectedLength);
        int[][] array = new int[expectedLength][expectedLength];
        for (int i = 0; i < expectedLength; i++) {
            String[] stringArray = strings.get(i).split(",");
            for (int j = 0; j < expectedLength; j++) {
                array[i][j] = Integer.parseInt(stringArray[j]);
            }
        }
        return array;
    }

    @Test
    public void testSimple() {
        int[][] array = readArray("matrix.txt", 80);
        assertEquals(answer(array), 427337);
    }

    @Test
    public void testBits() {
        int[][] array = new int[][]{
                {131, 673, 234, 103, 18},
                {201, 96, 342, 965, 150},
                {630, 803, 746, 422, 111},
                {537, 699, 497, 121, 956},
                {805, 732, 524, 37, 331}
        };
        printArray(maximalSum(array));
        assertEquals(answer(array), 2427);
    }
}
