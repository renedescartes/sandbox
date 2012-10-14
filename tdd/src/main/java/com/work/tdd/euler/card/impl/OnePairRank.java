package com.work.tdd.euler.card.impl;

import com.work.tdd.euler.card.HandRank;
import com.work.tdd.euler.card.Rank;

import java.util.List;

import static com.google.common.collect.Iterables.concat;
import static com.google.common.collect.Lists.newArrayList;
import static com.work.tdd.euler.card.RankFunction.rankComparison;
import static java.util.Arrays.asList;

public class OnePairRank extends AbstractRank {

    private Rank pairRank;
    private List<Rank> otherRanks;

    public OnePairRank(Rank pairRank, List<Rank> otherRanks) {
        this.pairRank = pairRank;
        this.otherRanks = otherRanks;
    }

    @Override
    public Integer getLevel() {
        return 2;
    }

    @Override
    int compareCurrentLevel(HandRank o) {
        OnePairRank next = (OnePairRank) o;
        return rankComparison(
                newArrayList(concat(asList(pairRank), otherRanks)),
                newArrayList(concat(asList(next.pairRank), next.otherRanks))
        );
    }
}
