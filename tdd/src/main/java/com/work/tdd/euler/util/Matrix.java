package com.work.tdd.euler.util;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class Matrix {

    public static Double[] matrixSolution(Double[][] inputs, Double[] outputs) {
        checkArgument(inputs.length == outputs.length);
        Double[] solution = new Double[outputs.length];
        Double originalDeterminant = determinant(inputs);
        for (int i = 0; i < outputs.length; i++) {
            Double[][] replacedMatrix = replaceColumn(inputs, outputs, i);
            solution[i] = determinant(replacedMatrix) / originalDeterminant;
        }
        return solution;
    }

    public static Double determinant(Double[][] matrix) {
        checkNotNull(matrix);
        checkArgument(matrix.length == matrix[0].length);
        if (matrix.length == 1) {
            return matrix[0][0];
        }
        Double determinant = 0d;
        int sign = 1;
        for (int row = 0; row < matrix.length; row++) {
            determinant += matrix[row][0] * sign * determinant(subMatrix(matrix, row));
            sign *= -1;
        }
        return determinant;
    }

    private static Double[][] subMatrix(Double[][] matrix, int chosenRow) {
        Double[][] subMatrix = new Double[matrix.length - 1][matrix.length - 1];
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

    private static Double[][] replaceColumn(Double[][] matrix, Double[] newColumn, Integer targetColumn) {
        Double[][] replacedMatrix = new Double[matrix.length][matrix.length];
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix.length; column++) {
                replacedMatrix[row][column] = (column == targetColumn) ? newColumn[row] : matrix[row][column];
            }
        }
        return replacedMatrix;
    }

}
