package com.work.tdd.euler.util.fraction;

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

    int closest() {
        return (int) (Math.sqrt(rootTerm) + realTerm);
    }
}

public class RootFraction {
    private Term num, den;

    public RootFraction(Term num, Term den) {
        this.num = num;
        this.den = den;
    }
}
