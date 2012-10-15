package com.work.tdd.euler;

import com.google.common.base.Function;
import org.apache.commons.lang.StringUtils;
import org.testng.annotations.Test;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import static com.google.common.collect.Collections2.transform;
import static java.util.Arrays.asList;

public class Problem59 {

    private static final Logger logger = Logger.getLogger(Problem59.class.getName());

    private static long sumOfASCIIMessage(String resourceName) {
        Collection<Integer> chars = readChars(resourceName);
        logger.info(StringUtils.join(chars, " "));
        return 0;
    }

    private static Collection<Integer> readChars(String resourceName) {
        List<String> strings = Utility.readFile(resourceName);
        return transform(asList(strings.get(0).split(",")), new Function<String, Integer>() {
            @Override
            public Integer apply(@Nullable String input) {
                return Integer.parseInt(input);
            }
        });
    }

    @Test
    public void testSimple() {
        sumOfASCIIMessage("cipher1.txt");
    }
}
