package com.work.tdd.algos;

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
        return squareRoot(0, upperLimit(n), n);
    }


    protected static double upperLimit(double n) {
        if(n <=1) {
            return 1;
        }
        if(n <=4) {
            return 2;
        }
        return n/2;
    }

    protected static double squareRoot(double start, double end, double n) {
        double d1 = Math.abs(n - (start * start));
        double d2 = Math.abs((end * end) - n);
        logger.info("Start " + start + " end " + end  + " d1 " + d1 + " d2 " + d2 + " n " + n);
        if(d1 < ERROR) {
            return start;
        }
        if(d2 < ERROR) {
            return end;
        }
        double middle = (start + end) / 2;
        return ((d1 < d2)) ?
                squareRoot(start, middle, n) :
                squareRoot(middle, end, n);
    }
}
