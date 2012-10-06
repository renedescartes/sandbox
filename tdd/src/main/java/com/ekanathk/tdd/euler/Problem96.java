package com.ekanathk.tdd.euler;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;
import java.util.logging.Logger;

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

    @Test
    public void testSolve() {
        Sudoku s = new Sudoku(new int[][] {
                {0,0,3,0,2,0,6,0,0},
                {9,0,0,3,0,5,0,0,1},
                {0,0,1,8,0,6,4,0,0},
                {0,0,8,1,0,2,9,0,0},
                {7,0,0,0,0,0,0,0,8},
                {0,0,6,7,0,8,2,0,0},
                {0,0,2,6,0,9,5,0,0},
                {8,0,0,2,0,3,0,0,9},
                {0,0,5,0,1,0,3,0,0},
        });
        Problem96 p = new Problem96();
        s = p.solve(s);
        System.out.println(s);
    }

    private Sudoku answer = null;
    private static final Logger logger = Logger.getLogger(Problem96.class.getName());
    public Sudoku solve(Sudoku input) {
        logger.fine(input.toString());
        if(answer != null) {
            return answer;
        }
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
        if(input.isSolved()) {
            answer = input;
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
        removeRowElements(array[i], placements);
        removeColumnElements(array, j, placements);
        removePoolElements(array, poolNumber(i, j), placements);
        return placements;
    }

    private void removePoolElements(int[][] array, int poolNumber, Collection<Integer> options) {
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
        for (int[] anArray : array) {
            options.remove(anArray[columnNumber]);
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
        StringBuffer b = new StringBuffer("\n");
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                b.append(array[i][j]).append(" ");
            }
            b.append("\n");
        }
        b.append("\n");
        return b.toString();
    }

    public boolean isSolved() {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(array[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
