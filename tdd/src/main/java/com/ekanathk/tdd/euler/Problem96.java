package com.ekanathk.tdd.euler;

import java.util.*;

/**
 * User: ekanathk
 * Date: 06/10/12
 */
public class Problem96 {


}

class Sudoku {
    private int[][] array;

    private static List<Integer> options = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

    Sudoku() {
        this(new int[9][9]);
    }

    Sudoku(int[][] array) {
        this.array = array;
    }

    public Sudoku copy() {
        return new Sudoku(array);
    }

    public int solve(int i, int j) {
        if(array[i][j] != 0) {throw new IllegalArgumentException("Attempting to solve an already solved piece");}
        Set<Integer> placements = new HashSet<>(options);
        return 1;
    }

    private static void removeElements(int[] array, Collection<Integer> options) {
        for (Integer integer : array) {
            options.remove(integer);
        }
    }

    public Sudoku place(int i, int j, int number) {
        array[i][j] = number;
        return this;
    }

    @Override
    public String toString() {
        StringBuffer b = new StringBuffer();
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                b.append(array[i][j]).append(" ");
            }
            b.append("\n");
        }
        b.append("\n");
        return b.toString();
    }
}
