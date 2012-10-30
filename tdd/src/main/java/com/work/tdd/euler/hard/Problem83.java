package com.work.tdd.euler.hard;

import com.work.tdd.euler.medium.Utility;
import com.work.tdd.euler.util.Tuple;
import org.testng.annotations.Test;

import java.util.*;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

class DijkstraExplorer {
    private int[][] array;
    private Map<Tuple<Integer, Integer>, Long> tupleMap;
    private Set<Tuple<Integer, Integer>> visitedNodes = new HashSet<>();

    private DijkstraExplorer(int[][] array) {
        this.array = array;
        this.tupleMap = listOfNodes(array);
    }

    public static long minimalPath(int[][] array) {
        DijkstraExplorer e = new DijkstraExplorer(array);
        e.dijkstraMap();
        return e.tupleMap.get(new Tuple<>(array.length - 1, array.length - 1));
    }

    private Map<Tuple<Integer, Integer>, Long> dijkstraMap() {
        Tuple<Integer, Integer> currentNode;
        while ((currentNode = findCurrentNode()) != null) {
            explore(currentNode);
            visitedNodes.add(currentNode);
        }
        return tupleMap;
    }

    private Tuple<Integer, Integer> findCurrentNode() {
        Tuple<Integer, Integer> tuple = null;
        Long minimalDistance = Long.MAX_VALUE;
        for (Map.Entry<Tuple<Integer, Integer>, Long> tupleEntry : tupleMap.entrySet()) {
            if (!visitedNodes.contains(tupleEntry)) {
                if (tupleEntry.getValue() < minimalDistance) {
                    minimalDistance = tupleEntry.getValue();
                    tuple = tupleEntry.getKey();
                }
            }
        }
        return tuple;
    }

    private void explore(Tuple<Integer, Integer> currentNode) {
        exploreNeighbour(currentNode, left(currentNode));
        exploreNeighbour(currentNode, right(currentNode, array.length));
        exploreNeighbour(currentNode, up(currentNode));
        exploreNeighbour(currentNode, down(currentNode, array.length));
    }

    private void exploreNeighbour(Tuple<Integer, Integer> currentNode, Tuple<Integer, Integer> neighbour) {
        if (neighbour != null && !visitedNodes.contains(neighbour)) {
            Long currentNodeDistance = tupleMap.get(currentNode);
            Long neighbourDistance = tupleMap.get(neighbour);
            tupleMap.put(neighbour, Math.min(neighbourDistance, currentNodeDistance + array[neighbour.getX()][neighbour.getY()]));
        }
    }


    private static Map<Tuple<Integer, Integer>, Long> listOfNodes(int[][] array) {
        Map<Tuple<Integer, Integer>, Long> map = new LinkedHashMap<>();
        for (int row = 0; row < array.length; row++) {
            for (int column = 0; column < array.length; column++) {
                map.put(new Tuple<>(row, column), row == 0 && column == 0 ? array[0][0] : -1L);
            }
        }
        return map;
    }

    private static Tuple<Integer, Integer> left(Tuple<Integer, Integer> tuple) {
        return tuple.getX() == 0 ? null : new Tuple<>(tuple.getX() - 1, tuple.getY());
    }

    private static Tuple<Integer, Integer> right(Tuple<Integer, Integer> tuple, int totalSize) {
        return tuple.getX() == totalSize - 1 ? null : new Tuple<>(tuple.getX() + 1, tuple.getY());
    }

    private static Tuple<Integer, Integer> up(Tuple<Integer, Integer> tuple) {
        return tuple.getY() == 0 ? null : new Tuple<>(tuple.getX(), tuple.getY() - 1);
    }

    private static Tuple<Integer, Integer> down(Tuple<Integer, Integer> tuple, int totalSize) {
        return tuple.getY() == totalSize - 1 ? null : new Tuple<>(tuple.getX(), tuple.getY() + 1);
    }

}

public class Problem83 {

    private static final Logger logger = Logger.getLogger(Problem83.class.getName());

    private static int[][] readArray(String resource, int expectedLength) {
        List<String> strings = Utility.readFile(resource);
        int[][] array = new int[expectedLength][expectedLength];
        for (int i = 0; i < expectedLength; i++) {
            String[] stringArray = strings.get(i).split(",");
            for (int j = 0; j < expectedLength; j++) {
                array[i][j] = Integer.parseInt(stringArray[j]);
            }
        }
        return array;
    }

    public static long answer(int[][] array) {
        return DijkstraExplorer.minimalPath(array);
    }

    @Test
    public void testBits() {
        int[][] array = new int[][]{
                {131, 673, 234, 103, 18},
                {201, 96, 342, 965, 150},
                {630, 803, 746, 422, 111},
                {537, 699, 497, 121, 956},
                {805, 732, 524, 37, 331}
        };
        assertEquals(answer(array), 994);
        array = new int[][]{
                {7, 2, 3, 4, 1, 2, 3, 1},
                {9, 4, 2, 1, 4, 1, 4, 9},
                {8, 7, 9, 1, 0, 1, 1, 2},
                {6, 3, 2, 1, 4, 2, 2, 3},
                {5, 8, 3, 1, 9, 3, 1, 2},
                {8, 5, 4, 2, 1, 6, 9, 1},
                {9, 2, 1, 8, 5, 1, 2, 1},
                {5, 8, 3, 1, 9, 3, 1, 2},
        };
        assertEquals(answer(array), 17);
    }

}
