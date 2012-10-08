package com.work.tdd.euler;

import org.junit.Test;

import static org.testng.Assert.assertEquals;

/**
 * User: EkanathK
 * Date: 29/09/12
 */
public class Problem18 {

    public static int maximalSum(int[][] array) {
        int[][] solutionArray = new int[array.length][];
        solutionArray[0] = new int[1];
        solutionArray[0][0] = array[0][0];
        for(int i = 1; i < array.length; i++) {
            solutionArray[i] = new int[array[i].length];
            for(int j = 0; j < array[i].length; j++) {
                solutionArray[i][j] = previousRankBestNumber(solutionArray, i, j) + array[i][j];
            }
        }
        return maximum(solutionArray[solutionArray.length - 1]);
    }

    private static int previousRankBestNumber(int[][] solutionArray, int i, int j) {
        if(j == 0) {
            return solutionArray[i-1][0];
        }
        if(j == solutionArray[i].length -1) {
            return solutionArray[i-1][j-1];
        }
        return Math.max(solutionArray[i-1][j], solutionArray[i-1][j-1]);
    }
    private static int maximum(int[] array) {
        int maximum = array[0];
        for (int i = 0; i < array.length; i++) {
            maximum = Math.max(maximum, array[i]);
        }
        return maximum;
    }

    @Test
    public void testSimple() {
        int[][] array = new int[][]{
                {3},
                {7, 4},
                {2, 4, 6},
                {8, 5, 9, 3}
        };
        assertEquals(23, maximalSum(array));
        array = new int[][]{
                {75},
                {95, 64},
                {17, 47, 82},
                {18, 35, 87, 10},
                {20, 04, 82, 47, 65},
                {19, 01, 23, 75, 03, 34},
                {88, 02, 77, 73, 07, 63, 67},
                {99, 65, 04, 28, 06, 16, 70, 92},
                {41, 41, 26, 56, 83, 40, 80, 70, 33},
                {41, 48, 72, 33, 47, 32, 37, 16, 94, 29},
                {53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14},
                {70, 11, 33, 28, 77, 73, 17, 78, 39, 68, 17, 57},
                {91, 71, 52, 38, 17, 14, 91, 43, 58, 50, 27, 29, 48},
                {63, 66, 04, 68, 89, 53, 67, 30, 73, 16, 69, 87, 40, 31},
                {04, 62, 98, 27, 23, 9, 70, 98, 73, 93, 38, 53, 60, 04, 23},
        };
        assertEquals(1074, maximalSum(array));
    }
}
