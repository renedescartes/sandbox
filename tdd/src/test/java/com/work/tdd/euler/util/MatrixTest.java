package com.work.tdd.euler.util;

import org.testng.annotations.Test;

import static com.work.tdd.euler.util.Matrix.determinant;
import static com.work.tdd.euler.util.Matrix.matrixSolution;
import static org.testng.Assert.assertEquals;

public class MatrixTest {
    @Test
    public void testBits() {
        Long[][] matrix = new Long[][]{
                {3L, 0L, 1L},
                {4L, 1L, 2L},
                {3L, 2L, 1L},
        };
        assertEquals(determinant(matrix), -4);
        matrix = new Long[][]{
                {3L, 2L, 0L, 1L},
                {4L, 0L, 1L, 2L},
                {3L, 0L, 2L, 1L},
                {9L, 2L, 3L, 1L},
        };
        assertEquals(determinant(matrix), 24);
        matrix = new Long[][]{
                {3L, 2L},
                {4L, 0L},
        };
        assertEquals(determinant(matrix), -8);
    }

    @Test
    public void testMatrixSolution() {
        Long[][] inputs = new Long[][]{
                {1L, 3L, -2L},
                {3L, 5L, 6L},
                {2L, 4L, 3L},
        };
        Long[] outputs = new Long[]{5L, 7L, 8L};
        Double[] answers = matrixSolution(inputs, outputs);
        assertEquals(answers, new Double[]{-15.0, 8.0, 2.0});
    }
}
