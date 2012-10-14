package com.work.tdd.euler.card.impl;

import com.work.tdd.euler.card.HandRank;
import com.work.tdd.euler.card.Rank;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;
import static com.work.tdd.euler.card.RankFunction.rankComparison;
import static java.util.Arrays.asList;

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
        return 3;
    }

    @Override
    int compareCurrentLevel(HandRank o) {
        TwoPairRank next = (TwoPairRank) o;
        return rankComparison(
                newArrayList(asList(pair1Rank, pair2Rank, otherRank)),
                newArrayList(asList(next.pair1Rank, next.pair2Rank, next.otherRank))
        );
    }
}
