package com.work.tdd.euler.util;

import org.testng.annotations.Test;

import static com.work.tdd.euler.util.Matrix.determinant;
import static com.work.tdd.euler.util.Matrix.matrixSolution;
import static org.testng.Assert.assertEquals;

public class MatrixTest {
    @Test
    public void testBits() {
        Double[][] matrix = new Double[][]{
                {3D, 0D, 1D},
                {4D, 1D, 2D},
                {3D, 2D, 1D},
        };
        assertEquals(determinant(matrix), -4.0);
        matrix = new Double[][]{
                {3D, 2D, 0D, 1D},
                {4D, 0D, 1D, 2D},
                {3D, 0D, 2D, 1D},
                {9D, 2D, 3D, 1D},
        };
        assertEquals(determinant(matrix), 24.0);
        matrix = new Double[][]{
                {3D, 2D},
                {4D, 0D},
        };
        assertEquals(determinant(matrix), -8.0);
    }

    @Test
    public void testMatrixSolution() {
        Double[][] inputs = new Double[][]{
                {1D, 3D, -2D},
                {3D, 5D, 6D},
                {2D, 4D, 3D},
        };
        Double[] outputs = new Double[]{5D, 7D, 8D};
        Double[] answers = matrixSolution(inputs, outputs);
        assertEquals(answers, new Double[]{-15D, 8D, 2D});
    }
}
