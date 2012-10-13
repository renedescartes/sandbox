package com.work.tdd.euler;

import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertTrue;

public class Problem38Java {
    private static final Logger logger = Logger.getLogger(Problem38Java.class.getName());

    public static int largestPanDigital() {
        List<List<Integer>> panDigitalList = Utility.permutes(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        Utility.sortListOfLists(panDigitalList);
        panDigitalList = Lists.reverse(panDigitalList);
        for (List<Integer> integers : panDigitalList) {
            int number = Integer.parseInt(StringUtils.join(integers, ""));
            if(isConcatenatedProduct(number)) {
                return number;
            }
        }
        return 0;
    }

    private static boolean isConcatenatedProduct(Integer n) {
        String s = n.toString();
        for(int i = 1; i < s.length(); i++) {
            int c = Integer.parseInt(s.substring(0, i));
            logger.info("Examining " + c + " with " + n);
            if(isConcatenatedProduct(c, n)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isConcatenatedProduct(Integer c, Integer n) {
        String s = c.toString() + "" + (c *2);
        int count = 2;
        while(count <= 9 && n.toString().startsWith(s) && !s.equals(n.toString())) {
            count++;
            s += (count * c);
        }
        return s.equals(n.toString());
    }

    @Test
    public void testSimple() {
        assertTrue(isConcatenatedProduct(192, 192384576));
        assertTrue(isConcatenatedProduct(932718654));
        //System.out.println(largestPanDigital());
    }
}
