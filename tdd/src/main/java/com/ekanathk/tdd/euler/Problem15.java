package com.ekanathk.tdd.euler;

import org.junit.Test;

import java.math.BigInteger;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

/**
 * User: EkanathK
 * Date: 29/09/12
 */

public class Problem15 {

    int solutions = 0;
    static final Logger logger = Logger.getLogger(Problem15.class.getName());

    protected void walk(Point p) {
        logger.info("Walking " + p);
        if(p.isComplete()) {
            solutions++;
            return;
        }
        if(p.right() != null) {
            walk(p.right());
        }
        if(p.down() != null) {
            walk(p.down());
        }
    }

    public static int routes(int gridSize) {
        Problem15 p = new Problem15();
        p.walk(new Point(0, 0, gridSize));
        return p.solutions;
    }

    public static long routesSimple(int gridSize) {
        BigInteger f = factorial(gridSize);
        return factorial(2 * gridSize).divide(f).divide(f).longValue();
    }

    public static BigInteger factorial(int n) {
        BigInteger f = new BigInteger("1");
        for(int i = 2 ; i <= n; i++) {
            f = f.multiply(new BigInteger(""+ i));
        }
        return f;
    }



    @Test
    public void testSimple() {
        BigInteger b = new BigInteger("2");
        String answer = b.pow(1000).toString();
        System.out.println(answer);
        assertEquals(24L, factorial(4).longValue());
        assertEquals(2L, factorial(2).longValue());
        assertEquals(6L, factorial(3).longValue());
        assertEquals(120L, factorial(5).longValue());
        assertEquals(2432902008176640000L, factorial(20).longValue());



        assertEquals(6, routesSimple(2));
        assertEquals(20, routesSimple(3));
        assertEquals(70, routesSimple(4));
        assertEquals(252, routesSimple(5));
        assertEquals(137846528820L, routesSimple(20));
    }
}

class Point {
    int x; int y; int grid;

    Point(int x, int y, int grid) {
        this.x = x;
        this.y = y;
        this.grid = grid;
    }

    boolean isComplete() {
        return x == y && y == grid;
    }

    Point right() {
        return y == grid ? null : new Point(x, y+1, grid);
    }

    Point down() {
        return x == grid ? null : new Point(x+1, y, grid);
    }

    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                ", grid=" + grid +
                '}';
    }
}