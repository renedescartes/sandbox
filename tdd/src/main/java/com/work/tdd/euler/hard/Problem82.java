package com.work.tdd.euler.hard;

import com.work.tdd.euler.medium.Utility;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public class Problem82 {

    private static final Logger logger = Logger.getLogger(Problem82.class.getName());

    public static long[][] minimalSum(int[][] array) {
        long[][] sumArray = new long[array.length][array.length];
        for (int row = 0; row < array.length; row++) {
            sumArray[row][0] = array[row][0];
        }
        for (int column = 1; column < array.length; column++) {
            for (int row = 0; row < array.length; row++) {
                long minimalSum = Long.MAX_VALUE;
                for (int rowIndex = 0; rowIndex < array.length; rowIndex++) {
                    minimalSum = Math.min(minimalSum, sumArray[rowIndex][column - 1] + sumForPath(array, rowIndex, row, column));
                }
                sumArray[row][column] = minimalSum;
            }
        }
        return sumArray;
    }

    private static long sumForPath(int[][] array, int fromRow, int toRow, int column) {
        long sum = 0;
        for (int row = Math.min(fromRow, toRow); row <= Math.max(fromRow, toRow); row++) {
            sum += array[row][column];
        }
        return sum;
    }

    public static long answer(int[][] array) {
        long[][] minimalSum = minimalSum(array);
        return minimalSum[minimalIndex(minimalSum, array.length - 1)][array.length - 1];
    }

    private static int minimalIndex(long[][] array, int column) {
        int minimalIndex = 0;
        for (int row = 1; row < array.length; row++) {
            if (array[row][column] < array[minimalIndex][column]) {
                minimalIndex = row;
            }
        }
        return minimalIndex;
    }

    private static void printArray(long[][] array) {
        for (long[] line : array) {
            logger.info(Arrays.toString(line));
        }
    }

    private static int[][] readArray(String resource, int expectedLength) {
        List<String> strings = Utility.readFile(resource);
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
        assertEquals(answer(array), 260324);
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
        printArray(minimalSum(array));
        assertEquals(answer(array), 994);
        array = new int[][]{
                {7, 2, 3, 4, 1, 2, 3, 1},
                {9, 4, 2, 1, 4, 1, 4, 9},
                {8, 7, 9, 1, 0, 1, 1, 2},
                {6, 3, 2, 1, 4, 2, 2, 3},
                {5, 8, 3, 1, 9, 3, 1, 2},
                {8, 5, 4, 2, 1, 6, 9, 1},
                {9, 2, 1, 8, 5, 1, 2, 1},
                {5, 8, 3, 1, 9, 3, 1, 2},
        };
        printArray(minimalSum(array));
        assertEquals(answer(array), 17);
    }
}
