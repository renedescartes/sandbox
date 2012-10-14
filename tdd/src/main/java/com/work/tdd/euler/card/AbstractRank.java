package com.work.tdd.euler.card;

public abstract class AbstractRank implements HandRank {
    @Override
    public final int compareTo(HandRank o) {
        int levelCompare = getLevel().compareTo(o.getLevel());
        return levelCompare == 0 ? compareCurrentLevel(o) : levelCompare;
    }

    abstract int compareCurrentLevel(HandRank o);
}
