package com.ekanathk.tdd.euler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

/**
 * User: EkanathK
 * Date: 29/09/12
 */
public class Utility {

    public static List<String> readFile(String classpathResourceName) {
        try {
            URL resource = Thread.currentThread().getContextClassLoader().getResource(classpathResourceName);
            return Files.readAllLines(Paths.get(resource.toURI()), Charset.defaultCharset());
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Integer[] properDivisors(int n) {
        if(n == 0) {
            return new Integer[0];
        }
        Collection<Integer> divisors = new TreeSet<>();
        divisors.add(1);
        for(int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0) {
                divisors.add(i);
                divisors.add(n/i);
            }
        }
        return divisors.toArray(new Integer[divisors.size()]);
    }

    public static <T> boolean isValidIndex(T[] array, int index) {
        return index >=0 && index < array.length;
    }

    public static Integer summation(Integer[] array) {
        Integer sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum = sum + array[i];
        }
        return sum;
    }

    public static Long summation(Long[] array) {
        Long sum = 0L;
        for (int i = 0; i < array.length; i++) {
            sum = sum + array[i];
        }
        return sum;
    }

    public static boolean isPrime(long n) {
        if(n < 0) return false;
        for(long i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0) {
                return false;
            }
        }
        return true;
    }


}
