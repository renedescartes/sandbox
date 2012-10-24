package com.work.tdd.euler.util.fraction;

import com.work.tdd.euler.Utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static com.work.tdd.euler.util.fraction.Fractions.bigIntegerFraction;

public class Continuations {
    private static final Logger logger = Logger.getLogger(Continuations.class.getName());

    public static List<Integer> continuedFractions(int n) {
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
        logger.fine("Number [" + n + "] fractions " + roots);
        return roots;
    }

    public static Integer sequenceNumber(int number, int termNumber) {
        List<Integer> terms = continuedFractions(number);
        if (termNumber <= terms.size()) {
            return terms.get(termNumber - 1);
        } else {
            return terms.get(((termNumber - 2) % (terms.size() - 1)) + 1);
        }
    }

    public static Fraction convergentFractions(int number, int term) {
        Fraction f = bigIntegerFraction(sequenceNumber(number, term));
        for (int i = term - 1; i >= 1; i--) {
            f = bigIntegerFraction(sequenceNumber(number, i)).add(f.reciprocal());
        }
        logger.fine("Term [" + term + "] fraction [" + f + "] decimal value [" + f.decimalValue() + "]");
        return f;
    }

}
