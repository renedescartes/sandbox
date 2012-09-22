package com.ekanathk.tdd.queens;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: kannan
 * Date: 22/09/12
 * Time: 22:33
 * To change this template use File | Settings | File Templates.
 */

public class QueenPlacement {

    private List<List<Integer>> solutions = new ArrayList<List<Integer>>();
    private static final Logger logger = Logger.getLogger(QueenPlacement.class.getName());

    public static List<List<Integer>> placements(int n) {
        QueenPlacement p = new QueenPlacement();
        p.placements(new ArrayList<Integer>(), n);
        return p.solutions;
    }

    private void placements(List<Integer> positions, int n) {
        logger.fine("Placements " + positions);
        if(positions.size() == n ) {
            solutions.add(new ArrayList<Integer>(positions));
            logger.info("Positions " + positions.toString());
            return;
        }
        Integer slot = -1;
        while((slot = getNextAvailableSlot(positions, slot+1, n)) != null) {
            placements(append(positions, slot), n);
        }
    }

    private static Integer getNextAvailableSlot(List<Integer> positions, int from, int n) {
        for(int i = from ; i < n; i++)  {
            if(!isAttacking(append(positions, i), n)) {
                return i;
            }
        }
        return null;
    }

    private static <T> List<T> append(List<T> list, T element) {
        List<T> appended = new ArrayList<T>(list);
        appended.add(element);
        return appended;
    }

    private static boolean isAttacking(List<Integer> positions, int n) {
        Set<Integer> set = new HashSet<Integer>(positions);
        if(set.size() != positions.size()) {
            return true;
        }
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i == j) {
                    continue;
                }
                if(positions.size() <= i || positions.size() <= j) {
                    continue;
                }
                if(Math.abs(j - i) == Math.abs(positions.get(j) - positions.get(i))) {
                    return true;
                }
            }
        }
        return false;
    }
}
