package com.work.tdd.euler;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static java.util.Arrays.asList;

/**
 * User: renedescartes
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
        if (n == 0) {
            return new Integer[0];
        }
        Collection<Integer> divisors = new TreeSet<>();
        divisors.add(1);
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                divisors.add(i);
                divisors.add(n / i);
            }
        }
        return divisors.toArray(new Integer[divisors.size()]);
    }

    public static <T> boolean isValidIndex(T[] array, int index) {
        return index >= 0 && index < array.length;
    }

    public static Long summation(List<Long> array) {
        Long sum = 0L;
        for (int i = 0; i < array.size(); i++) {
            sum = sum + array.get(i);
        }
        return sum;
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
        for (int i = 0; i < s.length(); i++) {
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
        if (n < 2) return false;
        for (long i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
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

    public static Set<Long> permutes(Long n) {
        Integer[] digits = digits(n);
        List<List<Integer>> permutes = permutes(Arrays.asList(digits));
        Set<Long> numbers = new TreeSet<>();
        for (List<Integer> permute : permutes) {
            numbers.add(Long.parseLong(StringUtils.join(permute, "")));
        }
        return numbers;
    }

    public static <T> void permutes(List<T> array, int n, List<List<T>> permutations) {
        if (n == 1) {
            permutations.add(new ArrayList<>(array));
            return;
        }
        for (int i = 0; i < n; i++) {
            swap(array, i, n - 1);
            permutes(array, n - 1, permutations);
            swap(array, i, n - 1);
        }
    }

    public static <T> void swap(List<T> array, int i, int j) {
        T buffer = array.get(i);
        array.set(i, array.get(j));
        array.set(j, buffer);
    }

    public static long factorial(long n) {
        long product = 1;
        for (long i = 2; i <= n; i++) {
            product *= i;
        }
        return product;
    }

    public static <T> List<List<T>> rotations(List<T> array) {
        List<List<T>> rotations = new ArrayList<>();
        LinkedList<T> rotateContext = new LinkedList<>(array);
        for (int i = 0; i < array.size(); i++) {
            rotateContext.add(rotateContext.remove());
            rotations.add(new ArrayList<>(rotateContext));
        }
        return rotations;
    }

    public static <T> List<List<T>> truncationOptions(List<T> array) {
        List<List<T>> rotations = new ArrayList<>();
        rotations.add(new ArrayList<>(array));
        for (int i = 1; i < array.size(); i++) {
            rotations.add(array.subList(i, array.size()));
            rotations.add(array.subList(0, array.size() - i));
        }
        return rotations;
    }

    public static <T> List<List<T>> combinations(List<T> elements, int c) {
        if (c > elements.size()) {
            throw new IllegalArgumentException("Cant choose more elements than in the list");
        }
        if (c == elements.size()) {
            return Arrays.asList(elements);
        }
        List<List<T>> allCombinations = new ArrayList<>();
        combinationInternal(elements, new ArrayList<T>(), 0, c, allCombinations);
        return allCombinations;
    }

    private static <T> void combinationInternal(List<T> elements, List<T> chosen, int level, int required, List<List<T>> allCombinations) {
        if (chosen.size() == required) {
            allCombinations.add(new ArrayList<>(chosen));
            return;
        }
        if (level == elements.size()) {
            return;
        }
        combinationInternal(elements, combineLists(chosen, Arrays.asList(elements.get(level))), level + 1, required, allCombinations);
        combinationInternal(elements, new ArrayList<>(chosen), level + 1, required, allCombinations);
    }

    public static List<Long> primeFactors(Long n) {
        if (n % 2 == 0) {
            return combineLists(Arrays.asList(2L), primeFactors(n / 2));
        }
        long a;
        for (a = (long) Math.sqrt(n); a <= n; a++) {
            if (isPerfectSquare(a * a - n)) {
                long b = (long) Math.sqrt(a * a - n);
                if (a + b < n) {
                    return combineLists(primeFactors(a + b), primeFactors(a - b));
                }
            }
        }
        return n == 1 ? Collections.<Long>emptyList() : asList(n);
    }

    private static boolean isPerfectSquare(long number) {
        long sqrt = (long) Math.sqrt(number);
        return sqrt * sqrt == number;
    }

    private static <T> List<T> combineLists(List<T> l1, List<T> l2) {
        List<T> list = new ArrayList<>();
        list.addAll(l1);
        list.addAll(l2);
        return list;
    }
}
