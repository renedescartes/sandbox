package com.ekanathk.tdd.algos;

/**
 * User: kannan
 * Date: 28/09/12
 */
public class Root {

    public static double ERROR = 0.00001;

    public static double squareRoot(double n) {
        if(n < 0) {
            throw new IllegalArgumentException("Please pass a non-negative number");
        }
        return squareRoot(0, n/2, n);
    }

    public static double squareRoot(double start, double end, double n) {
        double d1 = n - (start * start);
        double d2 = (end * end) - n;
        if(d1 < ERROR) {
            return start;
        }
        if(d2 < ERROR) {
            return end;
        }
        double middle = (start + end) / 2;
        return ((d1 < d2) && (n > 1)) ?
                squareRoot(start, middle, n) :
                squareRoot(middle, end, n);
    }
}
