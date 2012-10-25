package com.work.tdd.euler;

import com.google.common.base.Function;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import org.testng.annotations.Test;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;


class KeyLogComparator implements Comparator<Integer> {

    private Multimap<Integer, Integer> multimap = HashMultimap.create();

    public KeyLogComparator(List<Integer> codeAttempts) {
        for (Integer codeAttempt : codeAttempts) {
            Integer[] digits = Utility.digits(codeAttempt.longValue());
            multimap.put(digits[0], digits[1]);
            multimap.put(digits[0], digits[2]);
            multimap.put(digits[1], digits[2]);
        }
    }

    @Override
    public int compare(Integer o1, Integer o2) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String toString() {
        return multimap.toString();
    }
}


public class Problem79 {

    private static final Logger logger = Logger.getLogger(Problem79.class.getName());

    private static List<Integer> readFile(String resource) {
        List<String> lines = Utility.readFile(resource);
        return Lists.transform(lines, new Function<String, Integer>() {
            @Override
            public Integer apply(@Nullable String input) {
                return Integer.parseInt(input);
            }
        });
    }

    @Test
    public void testBits() {
        List<Integer> attempts = readFile("keylog.txt");
        logger.info(new KeyLogComparator(attempts).toString());
    }
}
