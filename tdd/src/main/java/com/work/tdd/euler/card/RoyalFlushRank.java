package com.work.tdd.euler.card;

public class RoyalFlushRank extends AbstractRank {

    @Override
    int compareCurrentLevel(HandRank o) {
        return 0;
    }

    @Override
    public Integer getLevel() {
        return 10;
    }
}
