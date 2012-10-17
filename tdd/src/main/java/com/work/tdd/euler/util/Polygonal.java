package com.work.tdd.euler.util;

public class Polygonal {

    private static boolean isPerfectSquare(long number) {
        long sqrt = (long) Math.sqrt(number);
        return sqrt * sqrt == number;
    }

    public static boolean isTriangular(long x) {
        return triangleRoot(x) >= 0;
    }

    public static long triangleRoot(long x) {
        return isQuadraticSolvable(1, 1, -2 * x);
    }

    public static boolean isSquare(long x) {
        return squareRoot(x) >= 0;
    }

    public static long squareRoot(long x) {
        return isQuadraticSolvable(1, 0, -1 * x);
    }

    public static boolean isPentagonal(long x) {
        return pentagonalRoot(x) >= 0;
    }

    public static long pentagonalRoot(long x) {
        return isQuadraticSolvable(3, -1, -2 * x);
    }

    public static boolean isHexagonal(long x) {
        return hexagonalRoot(x) >= 0;
    }

    public static long hexagonalRoot(long x) {
        return isQuadraticSolvable(2, -1, -1 * x);
    }

    public static boolean isHeptagonal(long x) {
        return heptagonalRoot(x) >= 0;
    }

    public static long heptagonalRoot(long x) {
        return isQuadraticSolvable(5, -3, -2 * x);
    }

    public static boolean isOctagonal(long x) {
        return octagonalRoot(x) >= 0;
    }

    public static long octagonalRoot(long x) {
        return isQuadraticSolvable(3, -2, -1 * x);
    }

    public static long polygonalRoot(long n, int dimension) {
        if (n <= 0) {
            return -1;
        }
        if (dimension == 3) {
            return triangleRoot(n);
        }
        if (dimension == 4) {
            return squareRoot(n);
        }
        if (dimension == 5) {
            return pentagonalRoot(n);
        }
        if (dimension == 6) {
            return hexagonalRoot(n);
        }
        if (dimension == 7) {
            return heptagonalRoot(n);
        }
        if (dimension == 8) {
            return octagonalRoot(n);
        }
        throw new IllegalArgumentException("Dimension is not valid [" + dimension + "]");
    }

    public static boolean isPolygonal(long n, int dimension) {
        return polygonalRoot(n, dimension) >= 0;
    }

    /**
     * This will check if the equation a * n^2 + b * n + c = 0
     * has atleast one root that is a positive Natual number (1, 2, 3.... etc)
     * and return that root
     */
    public static long isQuadraticSolvable(long a, long b, long c) {
        long coeff = (b * b) - (4 * a * c);
        long den = 2 * a;
        if (!isPerfectSquare(coeff)) {
            return -1;
        }
        long p1 = ((-1) * b) - (long) Math.sqrt(coeff);
        long p2 = ((-1) * b) + (long) Math.sqrt(coeff);
        if(p1 % den == 0 && p1/ den > 0) {
            return p1/ den;
        }
        if(p2 % den == 0 && p2/ den > 0) {
            return p2/ den;
        }
        return -1;
    }
}
