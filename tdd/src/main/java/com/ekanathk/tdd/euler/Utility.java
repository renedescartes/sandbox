package com.ekanathk.tdd.euler;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

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

    public static Integer[] digits(Long number) {
        String s = number.toString();
        List<Integer> digits = new ArrayList<>();
        for(int i = 0; i < s.length(); i++) {
            digits.add(s.charAt(i) - '0');
        }
        return digits.toArray(new Integer[digits.size()]);
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


    public static <T> void sortListOfLists(List<List<T>> listOfLists) {
        Collections.sort(listOfLists, new Comparator<List<T>>() {
            @Override
            public int compare(List<T> o1, List<T> o2) {
                return StringUtils.join(o1, "").compareTo(StringUtils.join(o2, ""));
            }
        });
    }

    public static <T> List<List<T>> permutes(List<T> array) {
        List<List<T>> permutations = new ArrayList<>();
        permutes(array, array.size(), permutations);
        return permutations;
    }

    public static <T> void permutes(List<T> array, int n, List<List<T>> permutations) {
        if(n == 1) {
            permutations.add(new ArrayList<>(array));
            return;
        }
        for(int i = 0; i < n; i++) {
            swap(array, i, n-1);
            permutes(array, n - 1, permutations);
            swap(array, i, n-1);
        }
    }

    private static <T> void swap(List<T> array, int i, int j) {
        T buffer = array.get(i);
        array.set(i, array.get(j));
        array.set(j, buffer);
    }

}
