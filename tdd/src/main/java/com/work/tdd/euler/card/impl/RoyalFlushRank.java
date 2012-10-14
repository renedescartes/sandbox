package com.work.tdd.euler.card.impl;

import com.work.tdd.euler.card.HandRank;

public class RoyalFlushRank extends AbstractRank {

    @Override
    int compareCurrentLevel(HandRank o) {
        return 0;
    }

    @Override
    public Integer getLevel() {
        return 10;
    }

    public String toString() {
        return "RoyalFlushRank";
    }
}

