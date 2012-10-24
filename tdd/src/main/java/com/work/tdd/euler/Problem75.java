package com.work.tdd.euler;

import org.testng.annotations.Test;

import java.util.logging.Logger;

public class Problem75 {
    private static final Logger logger = Logger.getLogger(Problem75.class.getName());

    public static void explorePrimitiveTriples(long MAX) {
        for (long n = 1; n < MAX; n++) {
            for (long m = n + 1; m < MAX; m++) {
                if (Utility.gcd(m, n) == 1 && ((m - n) % 2 == 1)) {
                    long a = m * m - n * n;
                    long b = 2 * m * n;
                    long c = m * m + n * n;
                    if (a + b + c > MAX) {
                        break;
                    }
                    logger.info("Primitive (" + a + "," + b + "," + c + ")");
                }
            }
        }
    }

    @Test
    public void testBits() {
        explorePrimitiveTriples(300);
    }
}
