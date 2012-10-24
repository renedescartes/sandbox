package com.work.tdd.euler;

import com.work.tdd.euler.util.fraction.RootFraction;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Problem64 {
    private static final Logger logger = Logger.getLogger(Problem64.class.getName());

    public List<Integer> rootFunction(int n) {
        List<Integer> roots = new ArrayList<>();
        RootFraction f = RootFraction.forSquareRoot(n);
        while(!f.isSolved()) {
            logger.info("Root fraction " + f);
            int closest = f.closest();
            roots.add(closest);
            f = f.minus(closest).reciprocal().normalise();
        }
        return roots;
    }

    @Test
    public void testSimple() {
        System.out.println(rootFunction(23));
    }

}
