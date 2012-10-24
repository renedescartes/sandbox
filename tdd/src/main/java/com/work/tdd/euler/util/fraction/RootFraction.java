package com.work.tdd.euler.util.fraction;

import com.google.common.base.Preconditions;

class Term {
    int realTerm;
    int rootTerm;

    Term(int realTerm) {
        this(realTerm, 0);
    }

    Term(int realTerm, int rootTerm) {
        this.realTerm = realTerm;
        this.rootTerm = rootTerm;
    }

    Term converse() {
        return new Term(rootTerm, -1 * realTerm);
    }

    double closest() {
        return (Math.sqrt(rootTerm) + realTerm);
    }

    boolean isRoot() {
        return rootTerm != 0;
    }

    boolean isOne() {
        return !isRoot() && realTerm == 1;
    }

    @Override
    public String toString() {
        return "Root(" + rootTerm + ") + (" + realTerm + ")";
    }
}

public class RootFraction {
    private Term num, den;

    RootFraction(Term num, Term den) {
        this.num = num;
        this.den = den;
    }

    public RootFraction normalise() {
        Preconditions.checkState(den.isRoot(), "Denominator should be root");
        Preconditions.checkState(!num.isRoot(), "Numerator should not be root");
        int f = (den.rootTerm * den.rootTerm - den.realTerm * den.realTerm) / num.realTerm;
        return new RootFraction(den.converse(), new Term(f));
    }

    public RootFraction minus(int n) {
        Preconditions.checkState(!den.isRoot(), "Denominator should not be root");
        int newRealTerm = num.realTerm - (n * den.realTerm);
        return new RootFraction(new Term(newRealTerm, num.rootTerm), den);
    }

    public int closest() {
        return (int) ((num.closest()/den.closest()));
    }

    public static RootFraction forSquareRoot(int n) {
        return new RootFraction(new Term(0, n), new Term(1));
    }

    public boolean isSolved() {
        return den.isOne();
    }

    public RootFraction reciprocal() {
        return new RootFraction(den, num);
    }

    @Override
    public String toString() {
        return "RootFraction{" +
                "num=" + num +
                ", den=" + den +
                '}';
    }
}
