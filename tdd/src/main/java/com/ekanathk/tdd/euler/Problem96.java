package com.ekanathk.tdd.euler;

import org.testng.annotations.Test;

import java.util.*;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

/**
 * User: ekanathk
 * Date: 06/10/12
 */
public class Problem96 {

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
//        Problem96 p = new Problem96();
//        p.solve(s, 0, 0);
//        System.out.println(p.answer);
        assertEquals(solveProblem("sudoku.txt"), 24702);
    }

    private Sudoku answer = null;
    private static final Logger logger = Logger.getLogger(Problem96.class.getName());

    public static long solveProblem(String resource) {
        List<Sudoku> sudokus = readPuzzles(resource);
        long sum = 0;
        for(Sudoku s: sudokus) {
            Sudoku solvedSudoku = solve(s);
            logger.info("" + solvedSudoku.sumOfThree());
            sum += solvedSudoku.sumOfThree();
        }
        return sum;
    }

    public static List<Sudoku> readPuzzles(String resource) {
        List<Sudoku> puzzles = new ArrayList<>();
        List<String> inputs = Utility.readFile(resource);

        for (int i = 0; i < inputs.size(); i = i + 10) {
            String input = inputs.get(i);
            if(input.startsWith("Grid")) {
                puzzles.add(readPuzzle(inputs.subList(i+1, i + 10)));
            }
        }
        return puzzles;
    }

    public static Sudoku readPuzzle(List<String> lines) {
        int[][] array = new int[9][9];
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9;j++) {
                array[i][j] = lines.get(i).charAt(j) - '0';
            }
        }
        return new Sudoku(array);
    }

    public static Sudoku solve(Sudoku input) {
        Problem96 p = new Problem96();
        p.solve(input, 0, 0);
        return p.answer;
    }

    public void solve(Sudoku input, int x, int y) {
        logger.fine(input.toString());
        if(answer != null) {
            return;
        }
        if(x == 9) {
            answer = input.copy();
            return;
        }
        if(input.getAt(x, y) != 0){
            solve(input.copy(), y==8 ? x+1: x, y==8? 0 : y+1);
        } else {
            Set<Integer> options = input.findOptions(x, y);
            for (Integer option : options) {
                solve(input.copy().place(x, y, option), y==8 ? x+1: x, y==8? 0 : y+1);
            }
        }
    }
}

class Sudoku {
    private int[][] array;

    private static List<Integer> options = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

    Sudoku(int[][] array) {
        this.array = array;
    }

    public Sudoku copy() {
        int[][] newArray = new int[9][9];
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                newArray[i][j] = array[i][j];
            }
        }
        return new Sudoku(newArray);
    }

    public Set<Integer> findOptions(int i, int j) {
        if(array[i][j] != 0) {
            throw new IllegalArgumentException("Attempting to findOptions an already solved piece");
        }
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

    public long sumOfThree() {
        return Long.parseLong("" + array[0][0] + array[0][1] + array[0][2]);
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

}
