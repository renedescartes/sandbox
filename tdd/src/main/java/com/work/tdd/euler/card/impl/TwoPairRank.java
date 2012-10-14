package com.work.tdd.euler.card.impl;

import com.work.tdd.euler.card.HandRank;
import com.work.tdd.euler.card.Rank;
import org.apache.commons.lang.builder.CompareToBuilder;

import static com.google.common.base.Preconditions.checkNotNull;

public class TwoPairRank extends AbstractRank {

    private Rank pair1Rank, pair2Rank, otherRank;

    public TwoPairRank(Rank pair1Rank, Rank pair2Rank, Rank otherRank) {
        checkNotNull(pair1Rank);
        checkNotNull(pair2Rank);
        checkNotNull(otherRank);
        this.pair1Rank = pair1Rank;
        this.pair2Rank = pair2Rank;
        this.otherRank = otherRank;
    }

    @Override
    public Integer getLevel() {
        return 4;
    }

    @Override
    int compareCurrentLevel(HandRank o) {
        TwoPairRank next = (TwoPairRank) o;
        return new CompareToBuilder().append(pair1Rank, next.pair1Rank).
                append(pair2Rank, next.pair2Rank).
                append(otherRank, next.otherRank).toComparison();
    }
}
