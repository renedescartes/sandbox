package com.work.tdd.euler.util;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class Matrix {
    public static long determinant(int[][] matrix) {
        checkNotNull(matrix);
        checkArgument(matrix.length == matrix[0].length);
        if (matrix.length == 1) {
            return matrix[0][0];
        }
        long determinant = 0;
        int sign = 1;
        for (int row = 0; row < matrix.length; row++) {
            determinant += matrix[row][0] * sign * determinant(subMatrix(matrix, row));
            sign *= -1;
        }
        return determinant;
    }

    private static int[][] subMatrix(int[][] matrix, int chosenRow) {
        int[][] subMatrix = new int[matrix.length - 1][matrix.length - 1];
        for (int row = 0; row < matrix.length; row++) {
            if (row != chosenRow) {
                for (int column = 1; column < matrix.length; column++) {
                    int subMatrixRow = row > chosenRow ? row - 1 : row;
                    subMatrix[subMatrixRow][column - 1] = matrix[row][column];
                }
            }
        }
        return subMatrix;
    }

}
