package com.work.tdd.euler;

import com.google.common.base.Function;
import org.apache.commons.lang.StringUtils;
import org.testng.annotations.Test;

import javax.annotation.Nullable;
import java.util.Arrays;
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

    private static int[] decryptMessage(int[] encryptedMessage) {
        int[] xorBuffers = new int[encryptedMessage.length];
        int xor = 0;
        for(int i = 0 ; i < encryptedMessage.length; i++) {
            xor = xor ^ encryptedMessage[i];
            xorBuffers[i] = xor;
        }
        int[] decryptedMessages = new int[encryptedMessage.length];
        for(int i = encryptedMessage.length - 1; i > 0; i--) {
            decryptedMessages[i] = xorBuffers[i] ^ xorBuffers[i-1];
        }
        decryptedMessages[0] = xorBuffers[1] ^ decryptedMessages[1];
        return decryptedMessages;
    }

    @Test
    public void testSimple() {
        int[] encrypt = new int[] {74, 7, 13, 6};
        System.out.println(Arrays.toString(encrypt));
        System.out.println(Arrays.toString(new int[]{74, 74 ^ 7, 74 ^ 7 ^ 13, 74 ^ 7 ^ 13 ^ 6}));
        System.out.println(Arrays.toString(new int[]{74, 77 ^ 74, 64 ^ 77, 70^ 64}));

        System.out.println(Arrays.toString(decryptMessage(encrypt)));
        //sumOfASCIIMessage("cipher1.txt");
    }
}
