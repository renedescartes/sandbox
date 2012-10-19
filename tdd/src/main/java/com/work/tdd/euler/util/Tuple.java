package com.work.tdd.euler.util;

import java.util.Comparator;

public class Tuple<X extends Comparable, Y extends Comparable> {

    X x;

    Y y;

    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    public X getX() {
        return x;
    }

    public Y getY() {
        return y;
    }

    public Comparator<Tuple<X, Y>> yComparator() {
        return new Comparator<Tuple<X, Y>>() {
            @Override
            public int compare(Tuple<X, Y> o1, Tuple<X, Y> o2) {
                return o1.getY().compareTo(o2.getY());
            }
        };
    }


    @Override
    public String toString() {
        return "Tuple{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tuple tuple = (Tuple) o;

        if (x != null ? !x.equals(tuple.x) : tuple.x != null) return false;
        if (y != null ? !y.equals(tuple.y) : tuple.y != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x != null ? x.hashCode() : 0;
        result = 31 * result + (y != null ? y.hashCode() : 0);
        return result;
    }
}
