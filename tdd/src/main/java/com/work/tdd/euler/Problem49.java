package com.work.tdd.euler;

import com.google.common.base.Predicate;
import org.testng.annotations.Test;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Set;
import java.util.logging.Logger;

import static com.google.common.collect.Collections2.filter;

/**
 * User: EkanathK
 * Date: 13/10/12
 */
public class Problem49 {

    private static final Logger logger = Logger.getLogger(Problem49.class.getName());

    public static String answer() {
        for (long i = 1000; i < 9999; i++) {
            if (Utility.isPrime(i)) {
                Set<Long> permutes = Utility.permutes(i);
                Collection<Long> primePermutes = filter(permutes, new Predicate<Long>() {
                    @Override
                    public boolean apply(@Nullable Long input) {
                        return Utility.isPrime(input) && input.toString().length() == 4;
                    }
                });
                if (primePermutes.size() >= 3) {
                    logger.info(primePermutes.toString());
                }
            }
        }
        return "";
    }

    @Test
    public void testSimple() {
        System.out.println(answer());
    }
}
