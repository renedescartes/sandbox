package com.work.tdd.euler.util;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class Matrix {

    public static Double[] matrixSolution(Integer[][] inputs, Integer[] outputs) {
        checkArgument(inputs.length == outputs.length);
        Double[] solution = new Double[outputs.length];
        long originalDeterminant = determinant(inputs);
        for (int i = 0; i < outputs.length; i++) {
            Integer[][] replacedMatrix = replaceColumn(inputs, outputs, i);
            solution[i] = (double) determinant(replacedMatrix) / (double) originalDeterminant;
        }
        return solution;
    }

    public static long determinant(Integer[][] matrix) {
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

    private static Integer[][] subMatrix(Integer[][] matrix, int chosenRow) {
        Integer[][] subMatrix = new Integer[matrix.length - 1][matrix.length - 1];
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

    private static Integer[][] replaceColumn(Integer[][] matrix, Integer[] newColumn, Integer targetColumn) {
        Integer[][] replacedMatrix = new Integer[matrix.length][matrix.length];
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix.length; column++) {
                replacedMatrix[row][column] = (column == targetColumn) ? newColumn[row] : matrix[row][column];
            }
        }
        return replacedMatrix;
    }

}
