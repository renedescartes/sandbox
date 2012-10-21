package com.work.tdd.euler;

import com.google.common.base.Preconditions;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static java.lang.Math.min;
import static org.testng.Assert.assertEquals;

public class Problem82 {

    private static final Logger logger = Logger.getLogger(Problem82.class.getName());

    public static long[][] minimalSum(int[][] array) {
        long[][] sumArray = new long[array.length][array[0].length];
        for (int column = 0; column < array.length; column++) {
            for (int row = 0; row < array[column].length; row++) {
                if (column == 0) {
                    sumArray[row][column] = array[row][column];
                } else {
                    long leftSum = sumArray[row][column - 1];
                    long upSum = (row == 0) ? Long.MAX_VALUE : sumArray[row - 1][column];
                    sumArray[row][column] = min(leftSum, upSum) + array[row][column];
                }
            }
        }
        /** Second scan to check from bottom*/
        for (int row = 0; row < array.length - 1; row++) {
            for (int column = 1; column < array[row].length; column++) {
                long downSum = sumArray[row + 1][column] + array[row][column];
                long leftSum = sumArray[row][column - 1] + array[row][column];
                sumArray[row][column] = min(leftSum, min(downSum, sumArray[row][column]));
            }
        }
        return sumArray;
    }

    public static long answer(int[][] question) {
        long[][] array = minimalSum(question);
        long answer = Long.MAX_VALUE;
        for (long[] line : array) {
            answer = Math.min(answer, line[line.length - 1]);
        }
        return answer;
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
        printArray(minimalSum(array));
        assertEquals(answer(array), 994);
    }
}
