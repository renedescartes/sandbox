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
}
