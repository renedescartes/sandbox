package com.work.tdd.permute;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: renedescartes
 * Date: 14/09/12
 * Time: 11:47
 * To change this template use File | Settings | File Templates.
 */
public class Heapify {

    private List<Integer> heap = new ArrayList<Integer>();

    public Heapify add(Integer... array) {
        for (Integer i : array) {
            heap.add(i);
            rebalanceAddition();
        }
        return this;
    }

    public Integer takeHeadAndRebalance() {
        Integer output = heap.get(0);
        swap(heap, 0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        rebalanceAfterHeadRemoval();
        return output;
    }

    private void rebalanceAfterHeadRemoval() {
        int current = 0, childIndex = -1;
        while ((childIndex = identifySuitableChildIndexForSwap(current)) != -1) {
            swap(heap, current, childIndex);
            current = childIndex;
        }
    }

    private int identifySuitableChildIndexForSwap(int index) {
        int childIndex = -1;
        boolean left = isLeftChildPresent(index), right = isRightChildPresent(index);
        if (left && right) {
            if (heap.get(leftChildIndex(index)) > heap.get(rightChildIndex(index))) {
                childIndex = leftChildIndex(index);
            } else {
                childIndex = rightChildIndex(index);
            }
        } else {
            if (left && !right) {
                childIndex = leftChildIndex(index);
            }
            if (!left && right) {
                childIndex = rightChildIndex(index);
            }
        }
        if (childIndex == -1) {
            return -1;
        } else {
            if (heap.get(childIndex) > heap.get(index)) {
                return childIndex;
            } else {
                return -1;
            }
        }
    }

    private void rebalanceAddition() {
        int lastPosition = heap.size() - 1;
        int parentPosition = parentIndex(lastPosition);
        while ((isValidIndex(parentPosition)) && (heap.get(lastPosition) > heap.get(parentPosition))) {
            swap(heap, lastPosition, parentPosition);
            lastPosition = parentPosition;
            parentPosition = parentIndex(parentPosition);
        }

    }

    public void printTree(PrintStream out) {
        printTree(0, out);
    }

    private void printTree(int index, PrintStream out) {
        printTreeItem(index, out);
        if (isValidIndex(leftChildIndex(index))) {
            printTree(leftChildIndex(index), out);
        }
        if (isValidIndex(rightChildIndex(index))) {
            printTree(rightChildIndex(index), out);
        }
    }

    private void printTreeItem(int index, PrintStream out) {
        for (int j = 0; j < level(index); j++) {
            out.print("\t");
        }
        out.println(heap.get(index));
    }

    private boolean isLeftChildPresent(int index) {
        return isValidIndex(leftChildIndex(index));
    }

    private boolean isRightChildPresent(int index) {
        return isValidIndex(rightChildIndex(index));
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < heap.size();
    }

    public static int level(int n) {
        int level = 0;
        int start = n + 1;
        while ((start = start / 2) > 0) {
            level++;
        }
        return level + 1;
    }

    public static int parentIndex(int n) {
        return n == 0 ? -1 : (n - 1) / 2;
    }

    public static int leftChildIndex(int n) {
        return (2 * n) + 1;
    }

    public static int rightChildIndex(int n) {
        return (2 * n) + 2;
    }

    public static <T> void swap(List<T> list, int i, int j) {
        T buffer = list.get(i);
        list.set(i, list.get(j));
        list.set(j, buffer);
    }
}
