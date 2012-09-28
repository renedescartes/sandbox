package com.ekanathk.tdd.algos;

import java.util.logging.Logger;

/**
 * User: kannan
 * Date: 28/09/12
 */
public class Root {

    public static double ERROR = 0.00001;
    private static final Logger logger = Logger.getLogger(Root.class.getName());

    public static double squareRoot(double n) {
        if(n < 0) {
            throw new IllegalArgumentException("Please pass a non-negative number");
        }
        return squareRoot(0, Math.max(2, n/2), n);
    }

    public static double squareRoot(double start, double end, double n) {
        logger.info("Start " + start + " end " + end  + " n " + n);
        double d1 = Math.abs(n - (start * start));
        double d2 = Math.abs((end * end) - n);
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
