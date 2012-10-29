package com.work.tdd.euler.medium;

import com.google.common.base.Predicate;
import org.apache.commons.lang.StringUtils;
import org.testng.annotations.Test;

import javax.annotation.Nullable;
import java.util.*;
import java.util.logging.Logger;

import static com.google.common.collect.Collections2.filter;
import static com.google.common.collect.Lists.newArrayList;

/**
 * User: renedescartes
 * Date: 13/10/12
 */
public class Problem49 {

    private static final Logger logger = Logger.getLogger(Problem49.class.getName());

    public static Collection<String> answer() {
        Collection<String> answers = new TreeSet<>();
        for (long i = 1000; i < 9999; i++) {
            if (Utility.isPrime(i)) {
                Set<Long> permutes = Utility.permutes(i);
                List<Long> primePermutes = newArrayList(filter(permutes, new Predicate<Long>() {
                    @Override
                    public boolean apply(@Nullable Long input) {
                        return Utility.isPrime(input) && input.toString().length() == 4;
                    }
                }));
                if (primePermutes.size() >= 3) {
                    logger.fine(primePermutes.toString());
                    List<List<Long>> combinations = Utility.combinations(primePermutes, 3);
                    for (List<Long> combination : combinations) {
                        logger.fine("Checking " + combination);
                        Collections.sort(combination);
                        if (isProgression(combination)) {
                            logger.fine("Found progression");
                            answers.add(StringUtils.join(combination, ""));
                        }
                    }
                }
            }
        }
        return answers;
    }

    private static boolean isProgression(List<Long> numbers) {
        long diff = numbers.get(1) - numbers.get(0);
        for (int i = 1; i < numbers.size() - 1; i++) {
            if (diff != numbers.get(i + 1) - numbers.get(i)) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testSimple() {
        System.out.println(answer());
    }
}
