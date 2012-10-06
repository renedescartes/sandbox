package com.ekanathk.tdd.euler;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.assertEquals;

/**
 * User: ekanathk
 * Date: 06/10/12
 */
public class Problem96 {

    @DataProvider(name = "pools")
    private Object[][] pools() {
        return new Object[][] {
                {0, 0, 0},
                {0, 2, 0},
                {0, 7, 2},
                {1, 7, 2},
                {2, 0, 0},
                {3, 0, 3},
                {6, 5, 7}
        };
    }

    @Test(dataProvider = "pools")
    public void testSimple(int i, int j, int pool) {
        assertEquals(Sudoku.poolNumber(i, j), pool);
    }

    private Sudoku answer = null;
    public Sudoku solve(Sudoku input) {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(input.getAt(i, j) == 0) {
                    Set<Integer> options = input.findOptions(i, j);
                    for(Integer option : options) {
                        solve(input.copy().place(i, j, option));
                    }
                }
            }
        }
        return answer;
    }
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

    public Set<Integer> findOptions(int i, int j) {
        if(array[i][j] != 0) {throw new IllegalArgumentException("Attempting to findOptions an already solved piece");}
        Set<Integer> placements = new HashSet<>(options);
        //remove all elements in the first row
        removeRowElements(array[i], options);
        removeColumnElements(array, j, options);
        removePoolElements(array, poolNumber(i, j), options);
        return placements;
    }

    private void removePoolElements(int[][] array, int poolNumber, List<Integer> options) {
        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array[i].length; j++) {
                if(poolNumber == poolNumber(i, j)) {
                    options.remove(array[i][j]);
                }
            }
        }
    }

    protected static int poolNumber(int i, int j) {
        return (i/3)*3 + j/3;
    }
    private static void removeRowElements(int[] array, Collection<Integer> options) {
        for (Integer integer : array) {
            options.remove(integer);
        }
    }

    private static void removeColumnElements(int[][] array, int columnNumber, Collection<Integer> options) {
        for (int i = 0; i < array.length; i++) {
            options.remove(array[i][columnNumber]);
        }
    }

    public int getAt(int i, int j) {
        return array[i][j];
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
