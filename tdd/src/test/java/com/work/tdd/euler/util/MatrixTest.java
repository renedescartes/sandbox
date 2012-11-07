package com.work.tdd.euler.util;

import org.testng.annotations.Test;

import static com.work.tdd.euler.util.Matrix.determinant;
import static org.testng.Assert.assertEquals;

public class MatrixTest {
    @Test
    public void testBits() {
        int[][] matrix = new int[][]{
                {3, 0, 1},
                {4, 1, 2},
                {3, 2, 1},
        };
        assertEquals(determinant(matrix), -4);
        matrix = new int[][]{
                {3, 2, 0, 1},
                {4, 0, 1, 2},
                {3, 0, 2, 1},
                {9, 2, 3, 1},
        };
        assertEquals(determinant(matrix), 24);
    }
}
