package com.work.tdd.euler;

import org.testng.annotations.Test;

import java.math.BigInteger;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public class Problem78 {
    private static final Logger logger = Logger.getLogger(Problem78.class.getName());

    private static BigInteger[] array = new BigInteger[1000000];

    public static BigInteger partition(int n) {
        if(n < 0) {
            return BigInteger.ZERO;
        }
        if(n == 0) {
            return BigInteger.ONE;
        }
        if(array[n] != null) {
            return array[n];
        }
        BigInteger answer = BigInteger.ZERO;
        int i = 1;
        int k = 1;
        BigInteger sign = BigInteger.ONE;
        while(k > 0) {
            k = n - pentagonNumber(i);
            if(k >= 0) {
                answer = answer.add(partition(k).multiply(sign));
                k = n - pentagonNumber(-1 * i);
                if(k >= 0) {
                    answer = answer.add(partition(k).multiply(sign));
                }
            }
            i++;
            sign = sign.negate();
        }
        array[n] = answer;
        return answer;
    }

    public static int pentagonNumber(int n) {
        return ((3 * n * n) - n)/2;
    }

    public int explore() {
        int answer = 2;
        BigInteger partitionCount;
        while(!(partitionCount = partition(answer)).mod(new BigInteger("1000000")).equals(BigInteger.ZERO)) {
            if(answer % 100 == 0) {
                logger.info("Examining [" + answer + "] count [" + partitionCount + "]");
            }
            answer++;
        }
        logger.info("Answer [" + answer + "] and partition [" + partition(answer) + "]");
        return answer;
    }

    @Test
    public void testSimple() {
        assertEquals(explore(), 55374);

    }
}

