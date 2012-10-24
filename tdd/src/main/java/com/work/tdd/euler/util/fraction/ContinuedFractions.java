package com.work.tdd.euler.util.fraction;

import com.work.tdd.euler.Utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class ContinuedFractions {
    private static final Logger logger = Logger.getLogger(ContinuedFractions.class.getName());

    public static List<Integer> rootFunction(int n) {
        List<Integer> roots = new ArrayList<>();
        RootFraction f = RootFraction.forSquareRoot(n);
        if (Utility.isPerfectSquare(n)) {
            return Arrays.asList((int) Math.sqrt(n));
        }
        do {
            logger.fine("Root fraction " + f);
            int closest = f.closest();
            roots.add(closest);
            f = f.minus(closest).reciprocal().normalise();
        } while (!f.isSolved());
        roots.add(roots.get(0) + f.getRealTermOfNum());
        logger.info("Number [" + n + "] fractions " + roots);
        return roots;
    }


}
