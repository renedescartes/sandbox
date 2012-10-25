package com.work.tdd.euler.util.fraction;

import com.work.tdd.euler.Utility;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static com.work.tdd.euler.util.NumberUtil.splitIntoDigits;
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

    /**
     * This does not use convergent fractions it just uses plain computations
     * Refer http://en.wikipedia.org/wiki/Methods_of_computing_square_roots#Digit-by-digit_calculation
     */
    public static BigDecimal squareRoot(int number, int numberOfDigits) {
        List<Integer> splits = splitIntoDigits(number, 2);
        StringBuilder b = new StringBuilder("0");
        BigInteger remainder = BigInteger.ZERO;
        int iteration = 0;
        while (new BigDecimal(b.toString()).precision() < numberOfDigits + 1) {
            BigInteger c = remainder.multiply(BigInteger.valueOf(100)).add(
                    (iteration < splits.size() ? BigInteger.valueOf(splits.get(iteration)) : BigInteger.ZERO));
            BigInteger p = new BigInteger(b.toString().replaceAll("\\.", ""));
            BigInteger x = BigInteger.ONE;
            while (x.multiply(BigInteger.valueOf(20).multiply(p).add(x)).compareTo(c) <= 0) {
                x = x.add(BigInteger.ONE);
            }
            x = x.subtract(BigInteger.ONE);
            b.append(x);
            remainder = c.subtract(x.multiply(BigInteger.valueOf(20).multiply(p).add(x)));
            if (iteration == splits.size() - 1) {
                b.append(".");
            }
            iteration++;
        }
        return new BigDecimal(b.toString());
    }
}
