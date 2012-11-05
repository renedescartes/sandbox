package com.work.tdd.euler.hard;

import com.work.tdd.euler.util.Tuple;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public class Problem91 {

    private static final Logger logger = Logger.getLogger(Problem98.class.getName());

    public static final Tuple<Integer, Integer> ORIGIN = new Tuple<>(0, 0);

    private static boolean isRightTriangle(int aSquare, int bSquare, int cSquare) {
        return aSquare == bSquare + cSquare || bSquare == aSquare + cSquare || cSquare == aSquare + bSquare;
    }

    private static int lengthSquare(Tuple<Integer, Integer> p1, Tuple<Integer, Integer> p2) {
        return (int) (Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }

    private static int lengthSquare(Tuple<Integer, Integer> p) {
        return lengthSquare(ORIGIN, p);
    }

    public static int answer(int dimension) {
        int count = 0;
        for (int x1 = 0; x1 <= dimension; x1++) {
            for (int y1 = 0; y1 <= dimension; y1++) {
                Tuple<Integer, Integer> p1 = new Tuple<>(x1, y1);
                for (int x2 = 0; x2 <= dimension; x2++) {
                    for (int y2 = 0; y2 <= dimension; y2++) {
                        Tuple<Integer, Integer> p2 = new Tuple<>(x2, y2);
                        if (!(p1.equals(ORIGIN) || p2.equals(ORIGIN) || p1.equals(p2))) {
                            if (xyComparator.compare(p1, p2) <= 0 && isRightTriangle(lengthSquare(p1), lengthSquare(p2), lengthSquare(p1, p2))) {
                                logger.fine("Points " + ORIGIN + p1 + p2);
                                count++;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    private static Comparator<? super Tuple<Integer, Integer>> xyComparator = Tuple.xyComparator();

    @Test
    public void testBits() {
        assertEquals(answer(50), 14234);
    }
}
