package com.work.tdd.euler;

import org.joda.time.DateMidnight;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * User: EkanathK
 * Date: 29/09/12
 */
public class Problem19 {

    public static int countSundays(DateMidnight d1, DateMidnight d2) {
        int count = 0;
        DateMidnight date = d1;
        while(d2.isAfter(date)) {
            if(date.getDayOfWeek() == 7) {
                count++;
            }
            date = date.plusMonths(1);
        }
        return count;
    }

    @Test
    public void testSimple() {
        assertEquals(5217, countSundays(new DateMidnight(1901, 1, 1), new DateMidnight(2000, 12, 31)));
    }
}
