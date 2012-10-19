package com.work.tdd.euler.util;

import com.google.common.base.Function;

import javax.annotation.Nullable;
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

    public <X extends Comparable, Y extends Comparable> Comparator<Tuple<X, Y>> yComparator() {
        return new Comparator<Tuple<X, Y>>() {
            @Override
            public int compare(Tuple<X, Y> o1, Tuple<X, Y> o2) {
                return o1.getY().compareTo(o2.getY());
            }
        };
    }

    public static <X extends Comparable, Y extends Comparable> Function<Tuple<X, Y>, Y> yFunction() {
        return new Function<Tuple<X, Y>, Y>() {
            @Override
            public Y apply(@Nullable Tuple<X, Y> input) {
                return input.getY();
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
