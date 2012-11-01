package com.work.tdd.euler.hard;

import com.work.tdd.euler.medium.Utility;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class Problem87 {

    private static Long[] generatePrimes(long MAX) {
        List<Long> primes = new ArrayList<>();
        for (long i = 2; i < MAX; i++) {
            if (Utility.isPrime(i)) {
                primes.add(i);
            }
        }
        return primes.toArray(new Long[primes.size()]);
    }

    private static Long[][] generatePowers(Long[] primes) {
        Long[][] powers = new Long[primes.length][3];
        for (int i = 0; i < primes.length; i++) {
            Long prime = primes[i];
            powers[i][0] = prime * prime;
            powers[i][1] = prime * prime * prime;
            powers[i][2] = prime * prime * prime * prime;
        }
        return powers;
    }

    public static long answer(long MAX) {
        Long[] primes = generatePrimes((long) Math.ceil(Math.sqrt(MAX)));
        Long[][] powers = generatePowers(primes);
        Set<Long> output = new HashSet<>();
        int size = primes.length;
        for (int a = 0; a < size; a++) {
            for (int b = 0; b < size; b++) {
                for (int c = 0; c < size; c++) {
                    long option = powers[a][2] + powers[b][1] + powers[c][0];
                    if (option < MAX) {
                        output.add(option);
                    } else {
                        break;
                    }
                }
            }
        }
        return output.size();
    }

    @Test
    public void testSimple() {
        assertEquals(answer(50_000_000), 1097343);
    }


}
