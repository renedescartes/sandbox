package com.work.tdd.euler.util;

import java.util.List;

public class Polygonal {

    public static boolean isTriangular(long n) {
        return true;
    }

    public static boolean isSquare(long n) {
        return true;
    }

    public static boolean isPentagonal(long n) {
        return true;
    }

    public static boolean isHexagonal(long n) {
        return true;
    }

    public static boolean isHeptagonal(long n) {
        return true;
    }

    public static boolean isOctagonal(long n) {
        return true;
    }

    public static boolean isPolygonal(long n, int dimension) {
        if(dimension == 3) {
            return isTriangular(n);
        }
        if(dimension == 4) {
            return isSquare(n);
        }
        if(dimension == 5) {
            return isPentagonal(n);
        }
        if(dimension == 6) {
            return isHexagonal(n);
        }
        if(dimension == 7) {
            return isHeptagonal(n);
        }
        if(dimension == 8) {
            return isOctagonal(n);
        }
        throw new IllegalArgumentException("Dimension is not valid [" + dimension + "]");
    }

    public static int dimension(long n, List<Integer> dimensions) {
        for(Integer d : dimensions) {
            if(isPolygonal(n, d)) {
                return d;
            }
        }
        return -1;
    }
}
