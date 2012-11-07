package com.work.tdd.euler.util;

import org.testng.annotations.Test;

import static com.work.tdd.euler.util.Matrix.determinant;
import static com.work.tdd.euler.util.Matrix.matrixSolution;
import static org.testng.Assert.assertEquals;

public class MatrixTest {
    @Test
    public void testBits() {
        Integer[][] matrix = new Integer[][]{
                {3, 0, 1},
                {4, 1, 2},
                {3, 2, 1},
        };
        assertEquals(determinant(matrix), -4);
        matrix = new Integer[][]{
                {3, 2, 0, 1},
                {4, 0, 1, 2},
                {3, 0, 2, 1},
                {9, 2, 3, 1},
        };
        assertEquals(determinant(matrix), 24);
        matrix = new Integer[][]{
                {3, 2},
                {4, 0},
        };
        assertEquals(determinant(matrix), -8);
    }

    @Test
    public void testMatrixSolution() {
        Integer[][] inputs = new Integer[][]{
                {1, 3, -2},
                {3, 5, 6},
                {2, 4, 3},
        };
        Integer[] outputs = new Integer[]{5, 7, 8};
        Double[] answers = matrixSolution(inputs, outputs);
        assertEquals(answers, new Double[]{-15.0, 8.0, 2.0});
    }
}
