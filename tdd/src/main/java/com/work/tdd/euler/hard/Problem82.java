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
                sumArray[row][column] = sumArray[row][column - 1] + array[row][column];
            }
            int minimalIndex = minimalIndex(sumArray, column);
            for (int rowIndex = minimalIndex + 1; rowIndex < array.length; rowIndex++) {
                sumArray[rowIndex][column] = array[rowIndex][column] + Math.min(sumArray[rowIndex - 1][column], sumArray[rowIndex][column - 1]);
            }
            for (int rowIndex = minimalIndex - 1; rowIndex >= 0; rowIndex--) {
                sumArray[rowIndex][column] = array[rowIndex][column] + Math.min(sumArray[rowIndex + 1][column], sumArray[rowIndex][column - 1]);
            }
        }
        return sumArray;
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
        assertEquals(answer(array), 264485);
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
    }
}
