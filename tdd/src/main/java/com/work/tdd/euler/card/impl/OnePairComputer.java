package com.work.tdd.euler.card.impl;

import com.work.tdd.euler.card.Hand;
import com.work.tdd.euler.card.Rank;
import com.work.tdd.euler.card.RankComputer;

import java.util.List;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Collections2.filter;
import static com.google.common.collect.Collections2.transform;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.reverse;
import static com.work.tdd.euler.card.impl.RankFunction.rankPredicate;
import static com.work.tdd.euler.card.impl.RankFunction.rankTransform;

public class OnePairComputer implements RankComputer<OnePairRank> {
    @Override
    public OnePairRank handRank(Hand h) {
        List<Rank> rank = RankFunction.pairRanks(h);
        if (rank.size() != 1) {
            return null;
        }
        Rank pairRank = rank.get(0);
        return new OnePairRank(pairRank, otherRanks(h, pairRank));
    }

    private static List<Rank> otherRanks(Hand h, Rank pairRank) {
        List<Rank> otherRanks = reverse(newArrayList(transform(filter(h.getCards(), rankPredicate(pairRank)), rankTransform())));
        checkState(otherRanks.size() == 3);
        return otherRanks;
    }
}
