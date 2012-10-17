package com.work.tdd.euler.util;

import java.util.List;

public class Polygonal {

    private static boolean isPerfectSquare(long number) {
        long sqrt = (long) Math.sqrt(number);
        return sqrt * sqrt == number;
    }

    public static boolean isTriangular(long x) {
        return isPerfectSquare((8 * x) + 1);
    }

    public static boolean isSquare(long x) {
        return isPerfectSquare(x);
    }

    public static boolean isPentagonal(long x) {
        long e = (24 * x) + 1;
        return isPerfectSquare(e) && (Math.sqrt(e) % 6 == 5);
    }

    public static boolean isHexagonal(long x) {
        long e = (8 * x) + 1;
        return isPerfectSquare(e) && (Math.sqrt(e) % 4 == 3);
    }

    public static boolean isHeptagonal(long x) {
        return isQuadraticSolvable(3, -2, -1 * x);
    }

    public static boolean isOctagonal(long x) {
        return isQuadraticSolvable(3, -2, -1 * x);
    }

    public static boolean isPolygonal(long n, int dimension) {
        if (n <= 0) {
            return false;
        }
        if (dimension == 3) {
            return isTriangular(n);
        }
        if (dimension == 4) {
            return isSquare(n);
        }
        if (dimension == 5) {
            return isPentagonal(n);
        }
        if (dimension == 6) {
            return isHexagonal(n);
        }
        if (dimension == 7) {
            return isHeptagonal(n);
        }
        if (dimension == 8) {
            return isOctagonal(n);
        }
        throw new IllegalArgumentException("Dimension is not valid [" + dimension + "]");
    }

    public static int dimension(long n, List<Integer> dimensions) {
        for (Integer d : dimensions) {
            if (isPolygonal(n, d)) {
                return d;
            }
        }
        return -1;
    }

    public static boolean isQuadraticSolvable(long a, long b, long c) {
        long coeff = (b * b) - (4 * a * c);
        if (!isPerfectSquare(coeff)) {
            return false;
        }
        long p1 = ((-1) * b) - (long) Math.sqrt(coeff);
        long p2 = ((-1) * b) + (long) Math.sqrt(coeff);
        return p1 % (2 * a) == 0 || p2 % (2 * a) == 0;
    }
}
