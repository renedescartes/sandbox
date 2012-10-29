package com.work.tdd.euler.medium;

import com.google.common.base.Function;
import org.apache.commons.lang.StringUtils;
import org.testng.annotations.Test;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.google.common.collect.Collections2.transform;
import static com.google.common.collect.Lists.newArrayList;
import static java.util.Arrays.asList;

public class Problem59 {

    private static final Logger logger = Logger.getLogger(Problem59.class.getName());

    private static long sumOfASCIIMessage(String resourceName) {
        List<Integer> chars = readChars(resourceName);
        logger.info(StringUtils.join(chars, " "));
        return 0;
    }

    private static List<Integer> readChars(String resourceName) {
        List<String> strings = Utility.readFile(resourceName);
        return newArrayList(transform(asList(strings.get(0).split(",")), new Function<String, Integer>() {
            @Override
            public Integer apply(@Nullable String input) {
                return Integer.parseInt(input);
            }
        }));
    }

    private static List<Character> decryptMessage(int[] encryptedMessage) {
        List<Character> possibleKeys = new ArrayList<>();
        for (int i = 'a'; i <= 'z'; i++) {
            boolean possibleKey = true;
            for (int j = 0; j < encryptedMessage.length && possibleKey; j++) {
                int decrypt = i ^ encryptedMessage[j];
                if (decrypt < 32 || decrypt > 122) {
                    possibleKey = false;
                }
            }
            if (possibleKey) {
                possibleKeys.add((char) i);
            }
        }
        return possibleKeys;
    }

    @Test
    public void testSimple() {
        List<Integer> chars = readChars("cipher1.txt");
        int[] cipher = new int[]{'g', 'o', 'd'};
        long sum = 0;
        for (int i = 0; i < chars.size(); i++) {
            int ascii = chars.get(i) ^ cipher[i % 3];
            System.out.print((char) ascii);
            sum += ascii;
        }
        System.out.println(sum);
    }
}
