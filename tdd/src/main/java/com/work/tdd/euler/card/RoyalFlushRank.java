package com.work.tdd.euler.card;

public class RoyalFlushRank implements HandRank {
    @Override
    public int compareTo(HandRank o) {
        return getLevel().compareTo(o.getLevel());
    }

    @Override
    public Integer getLevel() {
        return 10;
    }
}
